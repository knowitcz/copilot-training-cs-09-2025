package cz.knowit.ai.bank.database.transaction;

import java.util.Optional;

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
     * Creates a transaction
     *
     * @param id ID of the transaction to retrieve
     * @return an Optional containing the transaction if found, or an empty Optional if not found
     */
    Optional<Transaction> getById(long id);

    /**
     * Deletes a transaction
     *
     * @param id ID of the transaction to delete
     */
    void deleteById(long id);
}
