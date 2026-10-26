package model;

public abstract class Loan {

    protected double amount;
    protected int tenure;

    public Loan(double amount, int tenure) {
        this.amount = amount;
        this.tenure = tenure;
    }

    public abstract double getInterestRate();
    public abstract String getType();
}