package cz.knowit.ai.bank.api;

/**
 * Service for banking operations
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
public interface BankingService {

    /**
     * Transfers money from one account to another
     *
     * @param fromAccountId id of the account to transfer money from
     * @param toAccountId id of the account to transfer money to
     * @param amount amount of money to transfer
     */
    void makeTransfer(long fromAccountId, long toAccountId, int amount);

    /**
     * Withdraws money from an account
     *
     * @param accountId id of the account to withdraw money from
     * @param amount amount of money to withdraw
     */
    void withdraw(long accountId, int amount);

    /**
     * Deposits money into an account
     *
     * @param accountId id of the account to deposit money into
     * @param amount amount of money to deposit
     */
    void deposit(long accountId, int amount);
}
