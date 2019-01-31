package ru.cft.starterkit.timer;

public interface Task {
    //void run();   //Для полноценной версии
    void run(boolean expired);  //Для тестовой версии
}
