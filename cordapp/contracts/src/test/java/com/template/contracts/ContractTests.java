package com.template.contracts;

import com.template.states.DesignProductStamp;
import com.template.states.DesignStamp;
import com.template.states.TemplateState;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.CordaX500Name;
import net.corda.testing.core.TestIdentity;
import net.corda.testing.node.MockServices;
import org.junit.Test;

import java.util.Arrays;

import static net.corda.testing.node.NodeTestUtils.ledger;


public class ContractTests {
    private final MockServices ledgerServices = new MockServices(Arrays.asList("com.template"));
    TestIdentity alice = new TestIdentity(new CordaX500Name("Alice",  "TestLand",  "US"));
    TestIdentity bob = new TestIdentity(new CordaX500Name("Alice",  "TestLand",  "US"));

    @Test
    public void issuerAndRecipientCannotHaveSameEmail() {
        TemplateState state = new TemplateState("Hello-World",alice.getParty(),bob.getParty());
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
                tx.input(TemplateContract.ID, state);
                tx.output(TemplateContract.ID, state);
                tx.command(alice.getPublicKey(), new TemplateContract.Commands.Send());
                return tx.fails(); //fails because of having inputs
            });
            l.transaction(tx -> {
                tx.output(TemplateContract.ID, state);
                tx.command(alice.getPublicKey(), new TemplateContract.Commands.Send());
                return tx.verifies();
            });
            return null;
        });
    }
    @Test
    public void issuer() {
        UniqueIdentifier uniqueIdentifier=new UniqueIdentifier();
        DesignStamp state = new DesignStamp("",alice.getParty(),bob.getParty(),uniqueIdentifier);
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
// can not have input because no input use in contract code
                tx.output(DesignStampContract.ID, state);
                tx.command(alice.getPublicKey(), new DesignStampContract.Commands.Issue());
                return tx.verifies(); //fails because of having inputs
            });
            return null;
        });
    }
    @Test
    public void redeem(){
        UniqueIdentifier uniqueIdentifier=new UniqueIdentifier("a");
        DesignStamp state = new DesignStamp("",alice.getParty(),bob.getParty(),uniqueIdentifier);
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
                tx.input(DesignStampContract.ID, state);
                tx.output(DesignStampContract.ID, state);
                tx.command(alice.getPublicKey(), new DesignStampContract.Commands.Redeem());
                return tx.verifies(); //fails because of having inputs
            });
            return null;
        });

    }
    @Test
    public void pack(){
        String uniqueIdentifier="a";
        DesignProductStamp state2 = new DesignProductStamp("3r5",alice.getParty(),"id",1);
        DesignProductStamp state = new DesignProductStamp("aertg",alice.getParty(),"uniqueIdentifier",12);
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
//                tx.input(DesignProductStampContract.ID, state2);
                tx.output(DesignProductStampContract.ID, state2);
                tx.command(alice.getPublicKey(), new DesignProductStampContract.Commands.packBasket());
                return tx.verifies(); //fails because of having inputs
            });
            return null;
        });
    }
    @Test
    public void redeemProduct(){
        String uniqueIdentifier="a";
        DesignStamp state2 = new DesignStamp("  3r5",alice.getParty(),bob.getParty(),new UniqueIdentifier());
        DesignProductStamp state = new DesignProductStamp("new UniqueIdentifier()",alice.getParty(),"bob.getParty()",12);
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
                tx.input(DesignProductStampContract.ID, state2);
//                tx.command(alice.getPublicKey(), new DesignStampContract.Commands.Issue());

                tx.output(DesignProductStampContract.ID, state);
                tx.command(alice.getPublicKey(), new DesignProductStampContract.Commands.packBasket());
                tx.command(alice.getPublicKey(), new DesignProductStampContract.Commands.Redeem());
                return tx.verifies(); //fails because of having inputs
            });
            return null;
        });
    }
}
