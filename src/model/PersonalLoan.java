package model;

public class PersonalLoan extends Loan {

    public PersonalLoan(double amount, int tenure) {
        super(amount, tenure);
    }

    public double getInterestRate() {
        return 12.0;
    }

    public String getType() {
        return "PERSONAL LOAN";
    }
}