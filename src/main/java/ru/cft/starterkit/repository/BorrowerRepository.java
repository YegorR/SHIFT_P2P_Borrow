package ru.cft.starterkit.repository;

import ru.cft.starterkit.entity.Borrower;
import ru.cft.starterkit.entity.Deal;
import ru.cft.starterkit.exception.BorrowerNotFoundException;
import ru.cft.starterkit.exception.DealNotFoundException;

import java.util.ArrayList;

public interface BorrowerRepository {
    void addBorrower(Borrower borrower);

    Borrower getBorrower(String login) throws BorrowerNotFoundException;

    void addDeal(String login, Deal deal) throws BorrowerNotFoundException;

    Deal getDeal(String login) throws BorrowerNotFoundException, DealNotFoundException;

}
