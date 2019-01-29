package ru.cft.starterkit.entity;

public class User {
    private String login;
    private String password;
    private double balance;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        balance = 0;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
