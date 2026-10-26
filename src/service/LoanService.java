package service;

import model.*;

public class LoanService {

    private Customer[] customers = new Customer[100];
    private int custCount = 0;

    private Application[] applications = new Application[200];
    private int appCount = 0;

    public void addCustomer(Customer c) {
        customers[custCount++] = c;
    }

    public Customer findCustomer(String id) {
        for(int i = 0; i < custCount; i++) {
            if(customers[i].getCustomerId().equals(id))
                return customers[i];
        }
        throw new DomainException("Customer not found");
    }

    public void searchCustomer(String keyword) {
        boolean found = false;
        for(int i = 0; i < custCount; i++) {
            if(customers[i].getName().toLowerCase().equals(keyword.toLowerCase())){
                found = true;
                System.out.println("\n--------------------");
                System.out.println("Customer details ");
                System.out.println("--------------------");
                System.out.println("Customer ID: " + customers[i].getCustomerId());
                System.out.println("Name: " + customers[i].getName());
                System.out.println("Income: " + customers[i].getIncome());
                System.out.println("Credit Score: " + customers[i].getCreditScore());
            }
        }
        if(!found){
        throw new DomainException("Customer not found");}
    }

    public void applyLoan(String custId, String status) {
        if(status.equals("Invalid loan type")){
            return ;
        }
        String id = "APP" + (appCount+1);
        applications[appCount++] = new Application(id, custId, status);
    }

    public void listApplications() {
        if(appCount == 0){
            System.out.println("No applications found");
            return;
        }
        for(int i = 0; i < appCount; i++)
            System.out.println(applications[i]);
    }

    public void summary() {
        int approved = 0, rejected = 0;

        for(int i = 0; i < appCount; i++) {
            if(applications[i].getStatus().equals("APPROVED"))
                approved++;
            else if(applications[i].getStatus().equals("Invalid loan type"))
                continue;
            else
                rejected++;
        }

        System.out.println("\n----------------------");
        System.out.println("      Summary");
        System.out.println("----------------------");
        System.out.println("Approved: " + approved);
        System.out.println("Rejected: " + rejected);
    }

    public double calculateEMI(double P, double rate, int months) {
        double R = rate / (12 * 100);
        return (P * R * Math.pow(1+R, months)) /
               (Math.pow(1+R, months) - 1);
    }
}