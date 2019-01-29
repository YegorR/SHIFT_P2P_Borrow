package ru.cft.starterkit.entity;

public class Investor extends User{
    private Offer offer;
    private double loan;

    public Investor(String login, String password){
        super(login, password);
        loan = 0.0;
        offer = null;
    }

    public double getLoan(){
        return loan;
    }

    public void setLoan(double loan){
        this.loan = loan;
    }

    public Offer getOffer(){
        return offer;
    }

    public void setOffer(){
        this.offer = offer;
    }
}
