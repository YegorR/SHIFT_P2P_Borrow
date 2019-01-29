package ru.cft.starterkit.service;

import ru.cft.starterkit.entity.Borrower;
import ru.cft.starterkit.entity.Investor;
import ru.cft.starterkit.entity.Offer;
import ru.cft.starterkit.entity.ServerOffer;

public interface LogicService {
    ServerOffer createServerOffer(double sum, int term);
    void createDeal(ServerOffer serverOffer, Borrower borrower);

    void payByBorrower(double sum);
    double getBalanceBorrower(Borrower borrower);
    int getTermBorrower(Borrower borrower);

    Offer createOffer(double sum, double percent, Investor investor);

    double getBalanceInvestor(Investor investor);
    double getLoanInvestor(Investor investor);

}
