package cz.knowit.ai.bank.database.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public TransactionProdService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
