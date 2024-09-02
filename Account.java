package entities;

import java.io.Serializable;

public abstract class Account implements Serializable {
    private String accountNumber;
    private Customer customer;
    protected double balance;

    public Account(String accountNumber, Customer customer, double balance) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount){
        if(amount > 0){
            balance += amount;
            System.out.println(" Deposited: " + amount);
        }
        else{
            System.out.println(" Invalid deposit amount. ");
        }
    }
    
    public void withdraw(double amount){
        if(amount > 0 && amount <= balance){
            balance -= amount;
            System.out.println(" Withdrawn: " + amount);
        }
        else{
            System.out.println(" Invalid or insufficient withdrawal amount. ");
        }
    }
    
    @Override
    public String toString(){
        return " Account number: " + accountNumber + " , " + customer + " , Balance: " + balance;
    }
            

   
    
    
    
}
