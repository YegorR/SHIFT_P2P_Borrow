package ru.cft.starterkit.repository;

import ru.cft.starterkit.entity.Borrower;
import ru.cft.starterkit.entity.Investor;
import ru.cft.starterkit.entity.User;

import java.util.UUID;

public interface UserRepositiry {
    UUID addUser(User user);
    Borrower getBorrower(UUID uuid);
    Investor getInvestor(UUID uuid);
    UUID getUUID(String login, String password, boolean isInvestor);
}
