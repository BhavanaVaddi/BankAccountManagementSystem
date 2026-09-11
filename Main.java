package bankmanagement;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Bank bank = new Bank();

    public static void main(String[] args) {

        int choice;

        do {
            displayMenu();
            choice = readInt("Enter your choice: ");

            try {

                switch (choice) {

                    case 1:
                        addAccount();
                        break;

                    case 2:
                        bank.viewAllAccounts();
                        break;

                    case 3:
                        searchAccount();
                        break;

                    case 4:
                        updateAccount();
                        break;

                    case 5:
                        deleteAccount();
                        break;

                    case 6:
                        depositMoney();
                        break;

                    case 7:
                        withdrawMoney();
                        break;

                    case 8:
                        FileManager.saveAccounts(bank);
                        System.out.println("Thank you for using Bank Management System!");
                        break;

                    case 9:
                        FileManager.readAccounts();
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 8);

        scanner.close();
    }

    // Display menu
    private static void displayMenu() {

        System.out.println("\n======================================");
        System.out.println("      BANK MANAGEMENT SYSTEM");
        System.out.println("======================================");
        System.out.println("1. Add Account");
        System.out.println("2. View All Accounts");
        System.out.println("3. Search Account");
        System.out.println("4. Update Account");
        System.out.println("5. Delete Account");
        System.out.println("6. Deposit Money");
        System.out.println("7. Withdraw Money");
        System.out.println("8. Save & Exit");
        System.out.println("9. Read Saved Data");
        System.out.println("======================================");
    }

    // Add account
    private static void addAccount() {

        int accountNumber = readInt("Enter Account Number: ");

        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();

        double balance = readDouble("Enter Initial Balance: ");

        System.out.println("\nSelect Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");

        int type = readInt("Enter type: ");

        Account account;

        if (type == 1) {

            double interestRate =
                    readDouble("Enter Interest Rate (%): ");

            account = new SavingsAccount(
                    accountNumber,
                    name,
                    balance,
                    interestRate
            );

        } else if (type == 2) {

            double overdraftLimit =
                    readDouble("Enter Overdraft Limit: ");

            account = new CurrentAccount(
                    accountNumber,
                    name,
                    balance,
                    overdraftLimit
            );

        } else {
            System.out.println("Invalid account type.");
            return;
        }

        bank.addAccount(account);
    }

    // Search account
    private static void searchAccount() {

        int accountNumber =
                readInt("Enter Account Number to Search: ");

        Account account = bank.searchAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
        } else {
            System.out.println("\nAccount Found:");
            account.displayAccountDetails();
        }
    }

    // Update account
    private static void updateAccount() {

        int accountNumber =
                readInt("Enter Account Number: ");

        System.out.print("Enter New Account Holder Name: ");
        String newName = scanner.nextLine();

        bank.updateAccount(accountNumber, newName);
    }

    // Delete account
    private static void deleteAccount() {

        int accountNumber =
                readInt("Enter Account Number to Delete: ");

        bank.deleteAccount(accountNumber);
    }

    // Deposit
    private static void depositMoney() {

        int accountNumber =
                readInt("Enter Account Number: ");

        Account account = bank.searchAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        double amount =
                readDouble("Enter Deposit Amount: ");

        Transaction.deposit(account, amount);

        startTransactionThread(account);
    }

    // Withdraw
    private static void withdrawMoney() {

        int accountNumber =
                readInt("Enter Account Number: ");

        Account account = bank.searchAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        double amount =
                readDouble("Enter Withdrawal Amount: ");

        Transaction.withdraw(account, amount);

        startTransactionThread(account);
    }

    // Start background transaction verification
    private static void startTransactionThread(Account account) {

        TransactionTask task =
                new TransactionTask(account);

        task.start();
    }

    // Read integer safely
    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }

    // Read double safely
    private static double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);
                return Double.parseDouble(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid amount."
                );
            }
        }
    }
}
