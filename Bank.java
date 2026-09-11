package bankmanagement;

import java.util.ArrayList;
import java.util.HashMap;

public class Bank {

    // Collection to store accounts
    private HashMap<Integer, Account> accounts;

    // Constructor
    public Bank() {
        accounts = new HashMap<>();
    }

    // Add Account
    public void addAccount(Account account) {

        if (accounts.containsKey(account.getAccountNumber())) {
            System.out.println("Account number already exists.");
            return;
        }

        accounts.put(account.getAccountNumber(), account);
        System.out.println("Account added successfully.");
    }

    // View All Accounts
    public void viewAllAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        System.out.println("\n========== ALL ACCOUNTS ==========");

        for (Account account : accounts.values()) {
            account.displayAccountDetails();
            System.out.println("--------------------------------");
        }
    }

    // Search Account
    public Account searchAccount(int accountNumber) {

        return accounts.get(accountNumber);
    }

    // Update Account Holder Name
    public void updateAccount(int accountNumber, String newName) {

        Account account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        account.setAccountHolderName(newName);

        System.out.println("Account updated successfully.");
    }

    // Delete Account
    public void deleteAccount(int accountNumber) {

        Account removedAccount = accounts.remove(accountNumber);

        if (removedAccount == null) {
            System.out.println("Account not found.");
        } else {
            System.out.println("Account deleted successfully.");
        }
    }

    // Get all accounts
    public ArrayList<Account> getAllAccounts() {

        return new ArrayList<>(accounts.values());
    }
}