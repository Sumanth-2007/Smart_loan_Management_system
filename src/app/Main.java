package app;

import java.util.Scanner;
import model.*;
import service.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        LoanService ls = new LoanService();
        EligibilityService es = new EligibilityService();

        int num = 99;

        while(true) {
           
          System.out.println("\n==============Services=============");
            System.out.println("|                                 | ");
            System.out.println("|        1.Add Customer           |");
            System.out.println("|        2.Search Customer        |");
            System.out.println("|        3.Maximum loan           |");
            System.out.println("|        4.Check Eligibility      |");
            System.out.println("|        5.Apply Loan             |");
            System.out.println("|        6.Calculate EMI          |");
            System.out.println("|        7.View Applications      |");
            System.out.println("|        8.Summary                |");
            System.out.println("|        9.Exit                   |");
            System.out.println("|                                 | ");
            System.out.println("===================================");

            System.out.print("\nSelect an option: ");
            int ch = Integer.parseInt(sc.nextLine());

            try {

                switch(ch) {

                    case 1:
                        System.out.println("\n---Please enter details of the customer---\n");
                        num++;
                        String id = Integer.toString(num);
                        System.out.println("Customer ID: " + id);
                        // String id = sc.nextLine();

                        System.out.print("Name: ");
                        String name = sc.nextLine().trim();

                        System.out.print("Age: ");
                        int age = Integer.parseInt(sc.nextLine());

                        System.out.print("Income: ");
                        double income = Double.parseDouble(sc.nextLine());

                        System.out.print("Credit Score: ");
                        int cs = Integer.parseInt(sc.nextLine());

                        ls.addCustomer(new Customer(id, name, age, income, cs));
                        System.out.println("\n---Customer added successfully!---");
                        break;

                    case 2:

                        System.out.print("\nSearch name: ");
                        ls.searchCustomer(sc.nextLine().trim());
                        break;

                    case 3:

                        System.out.print("\nCustomer ID: ");
                        String idM = sc.nextLine();

                        System.out.print("Loan Type (HOME/PERSONAL): ");
                        String Ltype = sc.nextLine();

                        double maxLoan = es.calculateMaxLoan(ls.findCustomer(idM),Ltype);
                        if(maxLoan == 0){
                            System.out.println("Invalid loan type");
                            break;
                        }
                        System.out.printf("Maximum Loan amount: Rs.%,.2f\n",maxLoan );
                        
                        break;

                    case 4:
                        System.out.print("\nCustomer ID: ");
                        Customer c = ls.findCustomer(sc.nextLine());

                        System.out.print("Loan Type (HOME/PERSONAL): ");
                        String type = sc.nextLine();

                        String result = es.checkEligibility(c, type);
                        if(result == "APPROVED"){
                            System.out.println("ELIGIBLE");
                        }
                        else if(result == "Invalid loan type"){
                            System.out.println(result);
                        }
                        else{
                            System.out.println("NOT ELIGIBLE: " + result);
                        }
                        break;

                    case 5:
                        System.out.print("\nCustomer ID: ");
                        String cid = sc.nextLine();

                        Customer cust = ls.findCustomer(cid);

                        System.out.print("Loan Type (HOME/PERSONAL): ");
                        String t = sc.nextLine();

                        System.out.print("Amount: ");
                        double amt = sc.nextDouble();
                        sc.nextLine();

                        if(amt > es.calculateMaxLoan(cust,t)){
                            System.out.println("Amount can't be greater than maximum possible loan amount");
                            break;
                        }
                       
                        String status = es.checkEligibility(cust, t);
                        
                        ls.applyLoan(cid, status);
                        if(status.equals("APPROVED"))
                             System.out.println("Application Submitted: " + status);
                        else if(status.equals("Invalid loan type"))
                             System.out.println(status);
                        else    
                             System.out.println("Application Submitted: REJECTED -> " + status);    
                        break;

                    case 6:

                        System.out.print("\nLoan type (HOME/PERSONAL): ");
                        String loanType = sc.nextLine();

                        if(!loanType.trim().toLowerCase().equals("home") && !loanType.trim().toLowerCase().equals("personal")){
                            System.out.println("Invalid loan type");
                            break;
                        }

                        System.out.print("Amount: Rs.");
                        double a = Double.parseDouble(sc.nextLine());

                        System.out.print("Tenure(Months): ");
                        int m = Integer.parseInt(sc.nextLine());

                        Loan loan = null;

                        if(loanType.trim().toLowerCase().equals("home")){
                           loan = new HomeLoan(a,m);            
                        }
                        else if(loanType.trim().toLowerCase().equals("personal")){
                           loan = new PersonalLoan(a,m);
                        }
                        
                        System.out.println("Interest (" + loan.getType() + "): " + loan.getInterestRate());
                        System.out.printf("EMI = Rs.%,.2f\n",ls.calculateEMI(a,loan.getInterestRate(),m));
                        break;

                    case 7:
                        ls.listApplications();
                        break;

                    case 8:
                        ls.summary();
                        break;

                    case 9:
                        return;

                    default: System.out.println("Invalid choice");    
                }

            } catch(Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}