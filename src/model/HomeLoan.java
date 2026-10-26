package model;

public class HomeLoan extends Loan {

    public HomeLoan(double amount, int tenure) {
        super(amount, tenure);
    }

    public double getInterestRate() {
        return 7.5;
    }

    public String getType() {
        return "HOME LOAN";
    }
}