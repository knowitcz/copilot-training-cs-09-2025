package cz.knowit.ai.bank.api;

/**
 * Service for transfer banking operations
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
public interface TransferBankingService {

    /**
     * Transfers money from one account to another
     *
     * @param fromAccountId id of the account to transfer money from
     * @param toAccountId   id of the account to transfer money to
     * @param amount        amount of money to transfer
     */
    void makeTransfer(long fromAccountId, long toAccountId, int amount);
}
