package entities;

import java.util.Scanner;


public class MainApplication {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        AccountManager manager = new AccountManager();
        Scanner scanner = new Scanner(System.in);
        String choice;

        
        Bank bank = initializeBank();

        do {
            System.out.println("=== Bank Account Management System ===");
            System.out.println("Bank Details: " + bank);
            System.out.println("1. Create Account");
            System.out.println("2. Delete Account");
            System.out.println("3. Update Account");
            System.out.println("4. Search Account");
            System.out.println("5. Deposit");
            System.out.println("6. Withdraw");
            System.out.println("7. Transfer");
            System.out.println("8. Check Balance");
            System.out.println("9. View Transactions");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1" -> createAccount(manager, scanner);
                    case "2" -> deleteAccount(manager, scanner);
                    case "3" -> updateAccount(manager, scanner);
                    case "4" -> searchAccount(manager, scanner);
                    case "5" -> deposit(manager, scanner);
                    case "6" -> withdraw(manager, scanner);
                    case "7" -> transfer(manager, scanner);
                    case "8" -> checkBalance(manager, scanner);
                    case "9" -> viewTransactions(manager);
                    case "10" -> System.out.println("Exiting the system. Goodbye!");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (AccountNotFoundException | InsufficientFundsException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();

        } while (!choice.equals("10"));

        scanner.close();
    }

    private static Bank initializeBank() {
        
        return new Bank("Allied Bank", "AB001", "Model Town", "Muhammad Asif");
    }

    private static void createAccount(AccountManager manager, Scanner scanner) {
        System.out.print("Enter account type (Savings/Current): ");
        String type = scanner.nextLine();
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter contact number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Customer customer = new Customer(customerId, customerName, contactNumber, email);

        System.out.print("Enter initial balance: ");
        double balance = readDouble(scanner);

        if (type.equalsIgnoreCase("Savings")) {
            System.out.print("Enter interest rate (%): ");
            double interestRate = readDouble(scanner);
            Account account = new SavingsAccount(accountNumber, customer, balance, interestRate);
            manager.createAccount(account);
        } else if (type.equalsIgnoreCase("Current")) {
            Account account = new CurrentAccount(accountNumber, customer, balance);
            manager.createAccount(account);
        } else {
            System.out.println("Invalid account type. Account creation failed.");
        }
    }

    private static void deleteAccount(AccountManager manager, Scanner scanner) throws AccountNotFoundException {
        System.out.print("Enter account number to delete: ");
        String accountNumber = scanner.nextLine();
        manager.deleteAccount(accountNumber);
    }

    private static void updateAccount(AccountManager manager, Scanner scanner) throws AccountNotFoundException {
        System.out.print("Enter account number to update: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Enter new customer name: ");
        String newName = scanner.nextLine();

        System.out.print("Enter new balance: ");
        double newBalance = readDouble(scanner);

        manager.updateAccount(accountNumber, newName, newBalance);
    }

    private static void searchAccount(AccountManager manager, Scanner scanner) throws AccountNotFoundException {
        System.out.print("Enter account number to search: ");
        String accountNumber = scanner.nextLine();
        Account account = manager.searchAccount(accountNumber);
        System.out.println("Account Details: " + account);
    }

    private static void deposit(AccountManager manager, Scanner scanner) throws AccountNotFoundException {
        System.out.print("Enter account number to deposit into: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter amount to deposit: ");
        double amount = readDouble(scanner);
        manager.deposit(accountNumber, amount);
    }

    private static void withdraw(AccountManager manager, Scanner scanner) throws AccountNotFoundException, InsufficientFundsException {
        System.out.print("Enter account number to withdraw from: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter amount to withdraw: ");
        double amount = readDouble(scanner);
        manager.withdraw(accountNumber, amount);
    }

    private static void transfer(AccountManager manager, Scanner scanner) throws AccountNotFoundException, InsufficientFundsException {
        System.out.print("Enter account number to transfer from: ");
        String fromAccount = scanner.nextLine();
        System.out.print("Enter account number to transfer to: ");
        String toAccount = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = readDouble(scanner);
        manager.transfer(fromAccount, toAccount, amount);
    }

    private static void checkBalance(AccountManager manager, Scanner scanner) throws AccountNotFoundException {
        System.out.print("Enter account number to check balance: ");
        String accountNumber = scanner.nextLine();
        double balance = manager.checkBalance(accountNumber);
        System.out.println("Current Balance: $" + balance);
    }

    private static void viewTransactions(AccountManager manager) {
            
        System.out.println("Feature to view transactions is not implemented yet.");
    }

    private static double readDouble(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine();
                double value = Double.parseDouble(input);
                if (value < 0) {
                    throw new NumberFormatException("Negative value entered.");
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a positive number: ");
            }
        }
    }
}

