package ru.cft.starterkit.service;

import ru.cft.starterkit.entity.Borrower;
import ru.cft.starterkit.entity.Investor;
import ru.cft.starterkit.exception.BorrowerNotFoundException;
import ru.cft.starterkit.exception.InvestorNotFoundException;

import java.util.UUID;

public interface AuthenticationService {
    UUID login(String login, String password, boolean isInvestor) throws InvestorNotFoundException,
            BorrowerNotFoundException;

    Borrower getBorrower(UUID uuid) throws BorrowerNotFoundException;
    Investor getInvestor(UUID uuid) throws InvestorNotFoundException;

}
