package entities;

public class SavingsAccount extends Account {
    private double interestRate;
    
    @SuppressWarnings("SillyAssignment")
    public SavingsAccount(String accountNumber, Customer customer, double balance, double interestRate1) {
        super(accountNumber, customer, balance);
        this.interestRate = interestRate;
    }
     public void addInterest() {
        balance += balance * interestRate / 100;
        System.out.println("Interest added. New balance: " + balance);
    }

    public double getInterestRate() {
        return interestRate;
    }
     
     @Override
    public String toString() {
        return super.toString() + ", Interest Rate: " + interestRate + "%";
    }
    
    
}
