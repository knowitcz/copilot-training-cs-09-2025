package cz.knowit.ai.bank.database.product;

import cz.knowit.ai.bank.database.transaction.Transaction;
import cz.knowit.ai.bank.database.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public final class AccountProdService implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;
    private final LocalityRepository localityRepository;

    @Autowired
    AccountProdService(AccountRepository accountRepository, TransactionService transactionService, LocalityRepository localityRepository) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
        this.localityRepository = localityRepository;
    }

    @Override
    public Optional<Account> getAccountById(long id) {
        return accountRepository.findById(id);
    }

    @Override
    public void transferMoney(long fromAccountId, long toAccountId, int amount) {
        transferMoney(fromAccountId, toAccountId, amount, TransactionType.ONLINE, null);
    }

    @Override
    public void withdrawMoney(long accountId, int amount) {
        withdrawMoney(accountId, amount, TransactionType.ONLINE, null);
    }

    @Override
    public void depositMoney(long accountId, int amount) {
        depositMoney(accountId, amount, TransactionType.ONLINE, null);
    }

    @Override
    public void transferMoney(long fromAccountId, long toAccountId, int amount, TransactionType transactionType, Long localityId) {
        saveTransaction(fromAccountId, -amount, transactionType, localityId);
        accountRepository.withdrawMoney(fromAccountId, amount);
        saveTransaction(toAccountId, amount, transactionType, localityId);
        accountRepository.depositMoney(toAccountId, amount);
    }

    @Override
    public void withdrawMoney(long accountId, int amount, TransactionType transactionType, Long localityId) {
        saveTransaction(accountId, -amount, transactionType, localityId);
        accountRepository.withdrawMoney(accountId, amount);
    }

    @Override
    public void depositMoney(long accountId, int amount, TransactionType transactionType, Long localityId) {
        saveTransaction(accountId, amount, transactionType, localityId);
        accountRepository.depositMoney(accountId, amount);
    }

    private void saveTransaction(long accountId, int amount) {
        saveTransaction(accountId, amount, TransactionType.ONLINE, null);
    }

    private void saveTransaction(long accountId, int amount, TransactionType transactionType, Long localityId) {
        var transaction = new Transaction();
        transaction.setAccount(accountRepository.findById(accountId).orElseThrow());
        transaction.setAmount(amount);
        transaction.setCreatedAt(Instant.now());
        transaction.setTransactionType(transactionType);
        
        if (localityId != null) {
            transaction.setLocality(localityRepository.findById(localityId).orElse(null));
        }
        
        transactionService.save(transaction);
    }
}
