package bankmanagement;

public class Transaction {

    // Deposit money
    public static void deposit(Account account, double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }

        account.balance += amount;

        System.out.println("₹" + amount + " deposited successfully.");
        System.out.println("New Balance: ₹" + account.getBalance());
    }

    // Withdraw money
    public static void withdraw(Account account, double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }

        if (account instanceof CurrentAccount) {

            CurrentAccount currentAccount = (CurrentAccount) account;

            if (!currentAccount.canWithdraw(amount)) {
                throw new IllegalArgumentException("Insufficient balance and overdraft limit exceeded.");
            }

            account.balance -= amount;

        } else {

            if (amount > account.getBalance()) {
                throw new IllegalArgumentException("Insufficient balance.");
            }

            account.balance -= amount;
        }

        System.out.println("₹" + amount + " withdrawn successfully.");
        System.out.println("New Balance: ₹" + account.getBalance());
    }
}
