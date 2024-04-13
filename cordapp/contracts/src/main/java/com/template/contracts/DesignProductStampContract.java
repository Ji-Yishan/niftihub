package com.template.contracts;

import com.template.states.DesignStamp;
import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.transactions.LedgerTransaction;
import org.jetbrains.annotations.NotNull;
import com.template.states.DesignProductStamp;
import static net.corda.core.contracts.ContractsDSL.requireThat;

/**
 * @author: Isabella
 * @create: 2024-03-19 21:59
 **/
public class DesignProductStampContract implements Contract {
    // This is used to identify our contract when building a transaction.
    public static final String ID = "com.template.contracts.DesignProductStampContract";


    @Override
    public void verify(@NotNull LedgerTransaction tx) throws IllegalArgumentException {
        //Extract the command from the transaction.
        final CommandData commandData = tx.getCommands().get(0).getValue();
        System.out.println("verification");
        if (commandData instanceof DesignProductStampContract.Commands.packBasket){
            DesignProductStamp output = tx.outputsOfType(DesignProductStamp.class).get(0);
            requireThat(require -> {
                require.using("This transaction should only output one DesignProductStamp state", tx.getOutputs().size() == 1);
                require.using("The output DesignProductStamp state should have clear description of Design product", !output.getDescription().equals(""));
                return null;
            });
        }
        else if (commandData instanceof DesignProductStampContract.Commands.Redeem) {
            //Retrieve the output state of the transaction
            DesignStamp input = tx.inputsOfType(DesignStamp.class).get(0);
            DesignProductStamp output = tx.outputsOfType(DesignProductStamp.class).get(0);

            //Using Corda DSL function requireThat to replicate conditions-checks
            requireThat(require -> {
                System.out.println(tx.getInputStates().size());
                System.out.println(tx.getInputStates());
                require.using("This transaction should consume two states", tx.getInputStates().size() == 2);
                require.using("The issuer of the product stamp should be the producing from this seller", input.getIssuer().equals(output.getSeller()));
                return null;
            });
        }
        else{
            //Unrecognized Command type
            throw new IllegalArgumentException("Incorrect type of BasketOfDesign Commands");
        }
    }

    // Used to indicate the transaction's intent.
    public interface Commands extends CommandData {
        class packBasket implements DesignProductStampContract.Commands {}
//
        class Redeem implements DesignProductStampContract.Commands {}

    }
}
