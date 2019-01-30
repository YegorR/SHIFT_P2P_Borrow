package ru.cft.starterkit.service;

import ru.cft.starterkit.entity.Borrower;
import ru.cft.starterkit.entity.Investor;
import ru.cft.starterkit.entity.Offer;
import ru.cft.starterkit.entity.ServerOffer;
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
    void createDeal(UUID id, Borrower borrower) throws ServerOfferNotFoundException;

    //void payByBorrower(double sum);
    //double getBalanceBorrower(Borrower borrower);
    //int getTermBorrower(Borrower borrower);

    Offer createOffer(double sum, Investor investor) throws IOException, IncorrectSumException, InvestorNotFoundException;

    //double getBalanceInvestor(Investor investor);
    //double getLoanInvestor(Investor investor);

}
