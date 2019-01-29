package ru.cft.starterkit.repository;

import ru.cft.starterkit.entity.Investor;
import ru.cft.starterkit.exception.InvestorNotFoundException;

public interface InvestorRepository {
    boolean add(Investor investor);

    Investor get(String login) throws InvestorNotFoundException;
}
