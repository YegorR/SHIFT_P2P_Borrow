package ru.cft.starterkit.timer;

import ru.cft.starterkit.entity.Borrower;

import java.util.*;

public class TestTimer implements Timer{
    private class Task implements ru.cft.starterkit.timer.Task {
        private final Borrower borrower;
        private final UUID uuid;

        Task(Borrower borrower){
            this.borrower = borrower;
            uuid = UUID.randomUUID();
        }

        public Borrower getBorrower() {
            return borrower;
        }

        public UUID getUuid() {
            return uuid;
        }

        public void run(){
            //Отправить сигнал LogicSercive о том, что пора тратить долги
        }
    }

    private Map<UUID, Task> tasks = new HashMap<>();
    @Override
    public UUID createTask(Borrower borrower) {
        Task task = new Task(borrower);
        tasks.put(task.getUuid(), task);
        return task.getUuid();
    }

    @Override
    public void removeTask(UUID task_id) {
        tasks.remove(task_id);
    }
}
