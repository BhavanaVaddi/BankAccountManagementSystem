package bankmanagement;

public class CurrentAccount extends Account {

    private double overdraftLimit;

    // Constructor
    public CurrentAccount(int accountNumber, String accountHolderName,
                          double balance, double overdraftLimit) {

        super(accountNumber, accountHolderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    // Getter
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    // Polymorphism
    @Override
    public String getAccountType() {
        return "Current Account";
    }

    // Check withdrawal limit
    public boolean canWithdraw(double amount) {
        return amount <= balance + overdraftLimit;
    }
}