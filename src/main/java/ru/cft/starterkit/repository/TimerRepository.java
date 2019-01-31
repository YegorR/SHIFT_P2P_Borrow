package ru.cft.starterkit.repository;

import ru.cft.starterkit.entity.Borrower;

import java.util.UUID;

public interface TimerRepository {
    void createTask(Borrower borrower);
    void removeTask(Borrower borrower);

}
