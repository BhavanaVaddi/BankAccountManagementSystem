package bankmanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManager {

    private static final String FILE_NAME = "accounts.txt";

    // Save accounts to file
    public static void saveAccounts(Bank bank) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {

            for (Account account : bank.getAllAccounts()) {

                String type = account.getAccountType();

                double extraValue = 0;

                if (account instanceof SavingsAccount) {
                    SavingsAccount savings = (SavingsAccount) account;
                    extraValue = savings.getInterestRate();

                } else if (account instanceof CurrentAccount) {
                    CurrentAccount current = (CurrentAccount) account;
                    extraValue = current.getOverdraftLimit();
                }

                writer.println(
                    type + "|" +
                    account.getAccountNumber() + "|" +
                    account.getAccountHolderName() + "|" +
                    account.getBalance() + "|" +
                    extraValue
                );
            }

            System.out.println("Accounts saved to file successfully.");

        } catch (IOException e) {
            System.out.println("Error while saving accounts: " + e.getMessage());
        }
    }

    // Read accounts from file
    public static void readAccounts() {

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            System.out.println("\n========== SAVED ACCOUNT DATA ==========");

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Error while reading file: " + e.getMessage());
        }
    }
}
