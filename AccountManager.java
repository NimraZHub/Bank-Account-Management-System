package entities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AccountManager {
    private List<Account> accounts;
    private List<Transaction> transactions;
    private static final String ACCOUNT_FILE = "accounts.dat";
    private static final String TRANSACTION_FILE = "transactions.dat";

    public AccountManager() {
        accounts = loadAccounts();
        transactions = loadTransactions();
    }

    // Account Operations

    public void createAccount(Account account) {
        accounts.add(account);
        saveAccounts();
        System.out.println("Account created successfully.");
    }

    public void deleteAccount(String accountNumber) throws AccountNotFoundException {
        Account account = findAccount(accountNumber);
        accounts.remove(account);
        saveAccounts();
        System.out.println("Account deleted successfully.");
    }

    public void updateAccount(String accountNumber, String newName, double newBalance) throws AccountNotFoundException {
        Account account = findAccount(accountNumber);
        Customer customer = account.getCustomer();
        Customer updatedCustomer = new Customer(customer.getCustomerId(), newName, customer.getContactNumber(), customer.getEmail());

        if (account instanceof SavingsAccount) {
            SavingsAccount updatedAccount = new SavingsAccount(account.getAccountNumber(), updatedCustomer, newBalance, ((SavingsAccount) account).getInterestRate());
            accounts.set(accounts.indexOf(account), updatedAccount);
        } else if (account instanceof CurrentAccount) {
            CurrentAccount updatedAccount = new CurrentAccount(account.getAccountNumber(), updatedCustomer, newBalance);
            accounts.set(accounts.indexOf(account), updatedAccount);
        }

        saveAccounts();
        System.out.println("Account updated successfully.");
    }

    public Account searchAccount(String accountNumber) throws AccountNotFoundException {
        return findAccount(accountNumber);
    }

    // Transaction Operations

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        Account account = findAccount(accountNumber);
        account.deposit(amount);
        saveAccounts();
        recordTransaction(accountNumber, "Deposit", amount);
    }

    public void withdraw(String accountNumber, double amount) throws AccountNotFoundException, InsufficientFundsException {
        Account account = findAccount(accountNumber);
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient balance for withdrawal.");
        }
        account.withdraw(amount);
        saveAccounts();
        recordTransaction(accountNumber, "Withdrawal", amount);
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) throws AccountNotFoundException, InsufficientFundsException {
        Account fromAccount = findAccount(fromAccountNumber);
        Account toAccount = findAccount(toAccountNumber);

        if (fromAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient balance for transfer.");
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        saveAccounts();
        recordTransaction(fromAccountNumber, "Transfer Out", amount);
        recordTransaction(toAccountNumber, "Transfer In", amount);
        System.out.println("Transfer completed successfully.");
    }

    public double checkBalance(String accountNumber) throws AccountNotFoundException {
        Account account = findAccount(accountNumber);
        return account.getBalance();
    }

    // Helper Methods

    private Account findAccount(String accountNumber) throws AccountNotFoundException {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new AccountNotFoundException("Account not found: " + accountNumber);
    }

    private void recordTransaction(String accountNumber, String type, double amount) {
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), accountNumber, type, amount);
        transactions.add(transaction);
        saveTransactions();
    }

    // Serialization Methods

    private void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ACCOUNT_FILE))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<Account> loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ACCOUNT_FILE))) {
            return (List<Account>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing account data found. Starting fresh.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void saveTransactions() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TRANSACTION_FILE))) {
            oos.writeObject(transactions);
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<Transaction> loadTransactions() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TRANSACTION_FILE))) {
            return (List<Transaction>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing transaction data found.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
