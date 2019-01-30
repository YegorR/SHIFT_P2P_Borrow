package ru.cft.starterkit.repository;

import ru.cft.starterkit.entity.Investor;
import ru.cft.starterkit.entity.ServerOffer;
import ru.cft.starterkit.exception.InvestorNotFoundException;

import java.util.ArrayList;

public interface InvestorRepository {
    boolean add(Investor investor);
    Investor get(String login) throws InvestorNotFoundException;
    ArrayList<Investor> showAll();
    void toGroup(String login) throws InvestorNotFoundException;
    ServerOffer createServerOffer(double sum, int term);
}
