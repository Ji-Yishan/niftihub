package com.template.contracts;

import com.template.states.DesignStamp;
import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.transactions.LedgerTransaction;
import org.jetbrains.annotations.NotNull;

import static net.corda.core.contracts.ContractsDSL.requireThat;

/**
 * @author: Isabella
 * @create: 2024-03-18 23:32
 **/
public class DesignStampContract implements Contract {
    // This is used to identify our contract when building a transaction.
    public static final String ID = "com.template.contracts.DesignStampContract";
    // Used to indicate the transaction's intent.
    public interface Commands extends CommandData {
        //In our hello-world app, We will have two commands.
        class Issue implements DesignStampContract.Commands {}
        class Redeem implements DesignStampContract.Commands {}
    }
    @Override
    public void verify( @NotNull LedgerTransaction tx) throws IllegalArgumentException {
//Extract the command from the transaction.
        final CommandData commandData = tx.getCommands().get(0).getValue();
        System.out.println(commandData);
//todo 在数据库里面加一个sold的attribute
        //Verify the transaction according to the intention of the transaction
        if (commandData instanceof DesignStampContract.Commands.Issue){
            DesignStamp output = tx.outputsOfType(DesignStamp.class).get(0);
            requireThat(require -> {
                require.using("This transaction should only have one DesignStamp state as output", tx.getOutputs().size() == 1);
                require.using("The output DesignStamp state should have clear description of the type of redeemable goods", !output.getStampDesc().equals(""));
                return null;
            });

        }else if(commandData instanceof DesignStampContract.Commands.Redeem){

        }
        else{
            //Unrecognized Command type
            throw new IllegalArgumentException("Incorrect type of DesignStamp Commands");
        }
    }
}
