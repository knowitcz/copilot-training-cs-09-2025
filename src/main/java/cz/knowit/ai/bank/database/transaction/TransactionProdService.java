package cz.knowit.ai.bank.database.transaction;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Production implementation of {@link TransactionService}.
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@Service
public final class TransactionProdService implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionProdService(@Nonnull TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> getById(long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        //TODO: Implement
    }
}
