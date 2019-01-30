package ru.cft.starterkit.timer;

import ru.cft.starterkit.entity.Borrower;

import java.util.UUID;

public interface Timer {
    UUID createTask(Borrower borrower);
    void removeTask(UUID task_id);

}
