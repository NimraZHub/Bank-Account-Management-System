package entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {
     private String transactionId;
    private String accountNumber;
    private String type; // Deposit, Withdrawal, Transfer
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(String transactionId, String accountNumber, String type, double amount) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String toString() {
        return "Transaction ID: " + transactionId + ", Account: " + accountNumber + ", Type: " + type + ", Amount: " + amount + ", Date: " + timestamp;
    }
    
    
}
