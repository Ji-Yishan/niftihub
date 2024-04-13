package com.template.flows;

import co.paralleluniverse.fibers.Suspendable;
import com.template.contracts.DesignProductStampContract;
import com.template.states.DesignProductStamp;
import net.corda.core.flows.*;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;

import java.util.Collections;

/**
 * @author: Isabella
 * @create: 2024-03-20 21:37
 **/
public class PackageDesign {
    @InitiatingFlow
    @StartableByRPC
    public static class PackDesignInitiator extends FlowLogic<SignedTransaction> {

        private String designDescription;
        private String id;
        private int amount;

        public PackDesignInitiator(String designDescription, String id,int amount) {
            this.designDescription = designDescription;
            this.id = id;
            this.amount=amount;
        }

        @Override
        @Suspendable
        public SignedTransaction call() throws FlowException {

            /* Obtain a reference to a notary we wish to use.
             * METHOD 1: Take first notary on network, WARNING: use for test, non-prod environments, and single-notary networks only!*
             *  METHOD 2: Explicit selection of notary by CordaX500Name - argument can by coded in flows or parsed from config (Preferred)
             *  * - For production you always want to use Method 2 as it guarantees the expected notary is returned.
             */
//            final Party notary = getServiceHub().getNetworkMapCache().getNotaryIdentities().get(0); // METHOD 1
            final Party notary = getServiceHub().getNetworkMapCache().getNotary(CordaX500Name.parse("O=Notary,L=London,C=GB")); // METHOD 2

            //Create the output object
            DesignProductStamp basket = new DesignProductStamp(this.designDescription,this.getOurIdentity(),this.id,this.amount);

            //Building transaction
            TransactionBuilder txBuilder = new TransactionBuilder(notary)
                    .addOutputState(basket)
                    .addCommand(new DesignProductStampContract.Commands.packBasket(), this.getOurIdentity().getOwningKey());
            System.out.println("before verify");
            // Verify the transaction

            txBuilder.verify(getServiceHub());
            System.out.println("after verify");
            // Sign the transaction
            SignedTransaction signedTransaction = getServiceHub().signInitialTransaction(txBuilder);

            // Notarise the transaction and record the states in the ledger.
            return subFlow(new FinalityFlow(signedTransaction, Collections.emptyList()));
        }
    }
}
