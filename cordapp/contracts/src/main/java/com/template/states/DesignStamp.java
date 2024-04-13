package com.template.states;

import com.template.contracts.DesignStampContract;
import net.corda.core.contracts.BelongsToContract;
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
 * @create: 2024-03-18 23:10
 **/
@BelongsToContract(DesignStampContract.class)
public class DesignStamp implements LinearState {
//    private variable
private String stampDesc; //For example: "One stamp can be exchanged for a basket of Gala apples."
    private Party issuer; //The person who issued the stamp.
    private Party holder; //The person who currently owns the stamp.
    private UniqueIdentifier linearID;
    private List<AbstractParty> participants;

    public String getStampDesc() {
        return stampDesc;
    }

    public Party getIssuer() {
        return issuer;
    }

    public Party getHolder() {
        return holder;
    }


    @NotNull
    @Override
    public List<AbstractParty> getParticipants() {
        return participants;
    }



    public DesignStamp(String stampDesc, Party issuer, Party holder) {
        this.stampDesc = stampDesc;
        this.issuer = issuer;
        this.holder = holder;
        this.linearID = new UniqueIdentifier();
        this.participants = new ArrayList<AbstractParty>();
        this.participants.add(issuer);
        this.participants.add(holder);
    }
    @ConstructorForDeserialization
    public DesignStamp(String stampDesc, Party issuer, Party holder, UniqueIdentifier linearID) {
        this.stampDesc = stampDesc;
        this.issuer = issuer;
        this.holder = holder;
        this.linearID = linearID;
        this.participants = new ArrayList<AbstractParty>();
        this.participants.add(issuer);
        this.participants.add(holder);
    }

    @NotNull
    @Override
    public UniqueIdentifier getLinearId() {
        return linearID;
    }

}
