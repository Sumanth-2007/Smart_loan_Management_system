package service;

import model.Customer;

public class EligibilityService {

    public String checkEligibility(Customer c, String loanType) {

        if(loanType.trim().toLowerCase().equals("home") || loanType.trim().toLowerCase().equals("personal"))
        {
        if (c.getCreditScore() < 650)
            return "Low credit score";

        if (c.getIncome() < 20000)
            return "Income too low";

        if (c.getAge() > 60)
            return "Age too high";

        if (loanType.toLowerCase().equals("home") && c.getIncome() < 50000)
            return "Need higher income for home loan";

        if (loanType.toLowerCase().equals("personal") && c.getCreditScore() < 700)
            return "Credit score too low for personal loan";

        return "APPROVED";
        }

        else{
            return "Invalid loan type";
        }
    }

    public double calculateMaxLoan(Customer c, String type) {

        if(type.trim().toLowerCase().equals("home"))
            return c.getIncome() * 30;
        else if(type.trim().toLowerCase().equals("personal"))
            return c.getIncome() * 10;
        else
            return 0;

    }
}