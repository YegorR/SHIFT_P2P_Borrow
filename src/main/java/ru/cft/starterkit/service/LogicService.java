package ru.cft.starterkit.service;

import ru.cft.starterkit.entity.Borrower;
import ru.cft.starterkit.entity.ServerOffer;

public interface LogicService {
    ServerOffer createServerOffer(double sum, int term);
    void createDeal(ServerOffer serverOffer, Borrower borrower);

}
