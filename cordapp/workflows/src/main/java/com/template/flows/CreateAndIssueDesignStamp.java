package com.template.flows;

import co.paralleluniverse.fibers.Suspendable;
import com.template.contracts.DesignStampContract;
import com.template.states.DesignStamp;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.flows.*;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;
import nonapi.io.github.classgraph.json.JSONUtils;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author: Isabella
 * @create: 2024-03-20 00:37
 **/
public class CreateAndIssueDesignStamp {
    @InitiatingFlow
    @StartableByRPC
    public static class CreateAndIssueDesignStampInitiator extends FlowLogic<SignedTransaction> {

        private String stampDescription;
        private Party holder;

        public CreateAndIssueDesignStampInitiator(String stampDescription, Party holder) {
            this.stampDescription = stampDescription;
            this.holder = holder;
        }

        @Override
        @Suspendable
        public SignedTransaction call() throws FlowException {
            /* Obtain a reference to a notary we wish to use.
             * METHOD 1: Take first notary on network, WARNING: use for test, non-prod environments, and single-notary networks only!*
             *  METHOD 2: Explicit selection of notary by CordaX500Name - argument can by coded in flows or parsed from config (Preferred)
             *  * - For production you always want to1 use Method 2 as it guarantees the expected notary is returned.
             */
//            final Party notary = getServiceHub().getNetworkMapCache().getNotaryIdentities().get(0); // METHOD 1
            final Party notary = getServiceHub().getNetworkMapCache().getNotary(CordaX500Name.parse("O=Notary,L=London,C=GB")); // METHOD 2

            //Building the output AppleStamp state
            UniqueIdentifier uniqueID = new UniqueIdentifier();

            DesignStamp newStamp = new DesignStamp(this.stampDescription,this.getOurIdentity(),this.holder,uniqueID);

            //Compositing the transaction
            TransactionBuilder txBuilder = new TransactionBuilder(notary)
                    .addOutputState(newStamp)
                    .addCommand(new DesignStampContract.Commands.Issue(),
                            Arrays.asList(getOurIdentity().getOwningKey(),holder.getOwningKey()));

            // Verify that the transaction is valid.
            txBuilder.verify(getServiceHub());
            System.out.println("veriation finished");
            // Sign the transaction.
            final SignedTransaction partSignedTx = getServiceHub().signInitialTransaction(txBuilder);
            System.out.println(partSignedTx);
            // Send the state to the counterparty, and receive it back with their signature.
            FlowSession otherPartySession = initiateFlow(holder);
            System.out.println(otherPartySession);
            System.out.println("sign3");
            final SignedTransaction fullySignedTx = subFlow(
                    new CollectSignaturesFlow(partSignedTx, Arrays.asList(otherPartySession)));
            System.out.println("sign2");
            // Notarise and record the transaction in both parties' vaults.
            return subFlow(new FinalityFlow(fullySignedTx, Arrays.asList(otherPartySession)));
        }

    }
    @InitiatedBy(CreateAndIssueDesignStampInitiator.class)
    public static class CreateAndIssueDesignStampResponder extends FlowLogic<Void>{

        //private variable
        private FlowSession counterpartySession;

        public CreateAndIssueDesignStampResponder(FlowSession counterpartySession) {
            this.counterpartySession = counterpartySession;
        }

        @Override
        @Suspendable
        public Void call() throws FlowException {
            SignedTransaction signedTransaction = subFlow(new SignTransactionFlow(counterpartySession) {

                @Override
                @Suspendable
                protected void checkTransaction(SignedTransaction stx) throws FlowException {
                    System.out.println("sign4");
                    /*
                     * SignTransactionFlow will automatically verify the transaction and its signatures before signing it.
                     * However, just because a transaction is contractually valid doesn’t mean we necessarily want to sign.
                     * What if we don’t want to deal with the counterparty in question, or the value is too high,
                     * or we’re not happy with the transaction’s structure? checkTransaction
                     * allows us to define these additional checks. If any of these conditions are not met,
                     * we will not sign the transaction - even if the transaction and its signatures are contractually valid.
                     * ----------
                     * For this hello-world cordapp, we will not implement any additional checks.
                     * */
                }
            });

            //Stored the transaction into data base.
            subFlow(new ReceiveFinalityFlow(counterpartySession, signedTransaction.getId()));
            return null;
        }
    }
}