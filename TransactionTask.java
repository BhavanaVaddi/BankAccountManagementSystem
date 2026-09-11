package bankmanagement;

public class TransactionTask extends Thread {

    private Account account;

    public TransactionTask(Account account) {
        this.account = account;
    }

    @Override
    public void run() {

        System.out.println("\nBackground transaction task started...");

        try {
            Thread.sleep(2000);

            System.out.println("Transaction verification completed.");
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Current Balance: ₹" + account.getBalance());

        } catch (InterruptedException e) {
            System.out.println("Transaction task interrupted.");
        }
    }
}
