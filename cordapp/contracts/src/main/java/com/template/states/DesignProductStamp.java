package com.template.states;

import com.template.contracts.DesignProductStampContract;
import com.template.contracts.DesignStampContract;
import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.ContractState;
import net.corda.core.contracts.LinearState;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import net.corda.core.serialization.ConstructorForDeserialization;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Isabella
 * @create: 2024-03-19 20:54
 **/
@BelongsToContract(DesignProductStampContract.class)
public class DesignProductStamp implements ContractState {
    private String description;
    private Party seller; //Origin of the design
    private String id;//藏品id
    private List<AbstractParty> participants;
    private int amount;
    private UniqueIdentifier linearID;
    //Constructors
    @ConstructorForDeserialization
    public DesignProductStamp(String description, Party seller, String id,int amount) {
        this.description = description;
        this.seller = seller;
        this.id = id;
        this.participants = new ArrayList<AbstractParty>();
        this.participants.add(seller);
        this.amount=amount;
        this.linearID=new UniqueIdentifier();
    }

    public DesignProductStamp addDesignProductStamp(Party buyer) {

        DesignProductStamp newStamp=new DesignProductStamp(this.description,this.seller,this.id,this.amount--);
        this.participants.add(buyer);

        return newStamp;
    }

    @NotNull
    @Override
    public List<AbstractParty> getParticipants() {
        return participants;
    }

    public String getDescription() {
        return description;
    }

    public Party getSeller() {
        return seller;
    }


    public String getId() {
        return id;
    }
//    public DesignProductStamp changeOwner(Party buyer){
//        DesignProductStamp newOwnerState = new DesignProductStamp(this.description,);
//        return newOwnerState;
//    }

}
