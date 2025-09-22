package cz.knowit.ai.bank.database.transaction;

import cz.knowit.ai.bank.database.product.Account;

/**
 * Service for accessing the {@link Transaction} from datastorage.
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
public interface TransactionService {

    /**
     * Saves a transaction
     *
     * @param transaction transaction to save
     */
    void save(Transaction transaction);

    /**
     * Creates and saves a transaction with specified type and locality
     *
     * @param account account associated with the transaction
     * @param amount transaction amount
     * @param type transaction type
     * @param locality locality (can be null for ONLINE transactions)
     */
    void createTransaction(Account account, int amount, TransactionType type, Locality locality);
}
