package cz.knowit.ai.bank.api;

/**
 * Service for banking operations
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
public interface BankingService extends TransferBankingService {

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
