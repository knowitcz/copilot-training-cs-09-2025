package cz.knowit.ai.bank.database.transaction;

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
}
