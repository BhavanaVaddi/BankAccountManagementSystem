package bankmanagement;

public class SavingsAccount extends Account {

    private double interestRate;

    // Constructor
    public SavingsAccount(int accountNumber, String accountHolderName,
                          double balance, double interestRate) {

        super(accountNumber, accountHolderName, balance);
        this.interestRate = interestRate;
    }

    // Getter
    public double getInterestRate() {
        return interestRate;
    }

    // Polymorphism
    @Override
    public String getAccountType() {
        return "Savings Account";
    }

    // Calculate interest
    public double calculateInterest() {
        return balance * interestRate / 100;
    }
}