package ru.cft.starterkit.entity;

import java.util.ArrayList;
import java.util.UUID;

public class ServerOffer {
    public class ServerOfferForUser{
        private final double percent;
        private final int term;
        private final double sum;
        private final UUID id;

        ServerOfferForUser(double percent, int term, double sum, UUID id){
            this.percent = percent;
            this.term = term;
            this.sum = sum;
            this.id = id;
        }

        public double getPercent() {
            return percent;
        }

        public int getTerm() {
            return term;
        }

        public double getSum() {
            return sum;
        }

        public UUID getId() {
            return id;
        }
    }
    private final ArrayList<Investor> investors;
    private final double part;
    private final ServerOfferForUser serverOfferForUser;



    public ServerOffer(ArrayList<Investor> investors, double part, double percent, int term, double sum){
        this.investors = investors;
        this.part = part;
        this.serverOfferForUser = new ServerOfferForUser(percent, term, sum, UUID.randomUUID());

    }

    public ArrayList<Investor> getInvestors() {
        return investors;
    }

    public double getPart() {
        return part;
    }

    public ServerOfferForUser getServerOfferForUser(){
        return serverOfferForUser;
    }

    public double getSum(){
        return serverOfferForUser.getSum();
    }

    public double getPercent(){
        return serverOfferForUser.getPercent();
    }

    public UUID getId(){
        return serverOfferForUser.getId();
    }

    public int getTerm(){
        return serverOfferForUser.getTerm();
    }
}
