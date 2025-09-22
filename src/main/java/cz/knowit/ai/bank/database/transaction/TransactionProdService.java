package cz.knowit.ai.bank.database.transaction;

import cz.knowit.ai.bank.database.product.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

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

    @Override
    public void createTransaction(Account account, int amount, TransactionType type, Locality locality) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setLocality(locality);
        transaction.setCreatedAt(Instant.now());
        
        save(transaction);
    }
}
