package fr.mrlaikz.spartaguild.objects;

import java.util.UUID;

public class Economy {

    private UUID guilde;
    private double balance;

    public Economy(UUID guilde) {
        this.guilde = guilde;
        this.balance = 0;
    }

    public Economy(UUID guilde, double amount) {
        this.guilde = guilde;
        this.balance = amount;
    }

    //GETTERS
    public UUID getGuilde() {
        return guilde;
    }

    public double getBalance() {
        return balance;
    }

    //SETTERS
    public void deposit(double amount) {
        balance = balance + amount;
    }

    public void withdraw(double amount) {
        balance = balance - amount;
    }

    public void clearBalance() {
        balance = 0;
    }

    public void setBalance(double amount) {
        balance = amount;
    }

}
