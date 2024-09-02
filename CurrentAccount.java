package entities;

public class CurrentAccount extends Account {
    
    public CurrentAccount(String accountNumber, Customer customer, double balance) {
        super(accountNumber, customer, balance);
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Account Type: Current";
    }
    
}
