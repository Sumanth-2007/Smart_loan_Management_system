package model;

public class Customer extends Person {

    private String customerId;
    private double income;
    private int creditScore;

    public Customer(String id, String name, int age, double income, int creditScore) {
        super(name, age);

        if(income <= 0)
            throw new IllegalArgumentException("Income must be positive");

        if(creditScore < 300 || creditScore > 900)
            throw new IllegalArgumentException("Credit Score must be in range 300 to 900");

        this.customerId = id;
        this.income = income;
        this.creditScore = creditScore;
    }

    public String getCustomerId() { return customerId; }
    public double getIncome() { return income; }
    public int getCreditScore() { return creditScore; }
}