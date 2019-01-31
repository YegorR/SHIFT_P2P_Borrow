package ru.cft.starterkit.entity;

public class Borrower extends User {

    private Deal deal = null;

    public Borrower(String login, String password){
        super(login, password);
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }


}
