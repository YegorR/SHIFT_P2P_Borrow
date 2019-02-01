package ru.cft.starterkit.service;

import ru.cft.starterkit.entity.*;
import ru.cft.starterkit.exception.BorrowerNotFoundException;
import ru.cft.starterkit.exception.IncorrectSumException;
import ru.cft.starterkit.exception.InvestorNotFoundException;
import ru.cft.starterkit.exception.ServerOfferNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public interface LogicService {
    Investor createInvestor(String login, String password) throws InvestorNotFoundException;
    Borrower createBorrower(String login, String password) throws BorrowerNotFoundException;

    ArrayList<Investor> showAllInvestors();
    Investor getInvestor(String login) throws InvestorNotFoundException;

    ServerOffer createServerOffer(double sum, int term) ;
    Deal createDeal(UUID id, Borrower borrower) throws ServerOfferNotFoundException;

    void payByBorrower(double sum, Borrower borrower);
    void payByInvestor(double sum, Investor investor);
    double getBalanceBorrower(Borrower borrower);
    //int getTermBorrower(Borrower borrower);

    Offer createOffer(double sum, Investor investor) throws IOException, IncorrectSumException, InvestorNotFoundException;

    void timeOn(Borrower borrower);

    //double getBalanceInvestor(Investor investor);
    //double getLoanInvestor(Investor investor);

}
