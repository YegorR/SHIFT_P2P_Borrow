package ru.cft.starterkit.entity;

import java.util.ArrayList;
import java.util.UUID;

public class ServerOffer {
    private final ArrayList<Investor> investors;
    private final double part;
    private final double percent;
    private final int term;

    private final UUID id;

    public ServerOffer(ArrayList<Investor> investors, double part, double percent, int term){
        this.investors = investors;
        this.part = part;
        this.percent = percent;
        this.term = term;

        id = UUID.randomUUID();
    }

    public ArrayList<Investor> getInvestors() {
        return investors;
    }

    public double getPart() {
        return part;
    }

    public double getPercent() {
        return percent;
    }

    public UUID getId() {
        return id;
    }

    public int getTerm(){
        return term;
    }
}
