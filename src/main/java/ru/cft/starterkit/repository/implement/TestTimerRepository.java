package ru.cft.starterkit.repository.implement;

import org.springframework.stereotype.Repository;
import ru.cft.starterkit.entity.Borrower;
import ru.cft.starterkit.repository.TimerRepository;

import java.util.*;

@Repository
public class TestTimerRepository implements TimerRepository {
    private class TaskImpl implements ru.cft.starterkit.timer.Task {
        private final Borrower borrower;

        TaskImpl(Borrower borrower){
            this.borrower = borrower;
        }

        public Borrower getBorrower() {
            return borrower;
        }

        @Override
        public void run(boolean expired){
            //Поставить флаг в deal
            //Отправить сигнал LogicSercive о том, что пора тратить долги
        }
    }

    private Set<TaskImpl> tasks = new HashSet<>();
    @Override
    public void createTask(Borrower borrower) {
        TaskImpl task = new TaskImpl(borrower);
        tasks.add(task);
    }

    @Override
    public void removeTask(Borrower borrower) {
        Iterator<TaskImpl> iter = tasks.iterator();
        while(iter.hasNext()){
            TaskImpl task = iter.next();
            if (task.getBorrower().equals(borrower)){
                tasks.remove(task);
                return;
            }
        }
    }
}
