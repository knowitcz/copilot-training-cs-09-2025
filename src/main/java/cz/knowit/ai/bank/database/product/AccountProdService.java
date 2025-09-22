package cz.knowit.ai.bank.database.product;

import cz.knowit.ai.bank.database.transaction.Locality;
import cz.knowit.ai.bank.database.transaction.Transaction;
import cz.knowit.ai.bank.database.transaction.TransactionService;
import cz.knowit.ai.bank.database.transaction.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public final class AccountProdService implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    @Autowired
    AccountProdService(AccountRepository accountRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @Override
    public Optional<Account> getAccountById(long id) {
        return accountRepository.findById(id);
    }

    @Override
    public void transferMoney(long fromAccountId, long toAccountId, int amount) {
        saveTransaction(fromAccountId, -amount);
        accountRepository.withdrawMoney(fromAccountId, amount);
        saveTransaction(toAccountId, amount);
        accountRepository.depositMoney(toAccountId, amount);
    }

    @Override
    public void withdrawMoney(long accountId, int amount) {
        saveTransaction(accountId, -amount);

        accountRepository.withdrawMoney(accountId, amount);
    }

    @Override
    public void depositMoney(long accountId, int amount) {
        saveTransaction(accountId, amount);

        accountRepository.depositMoney(accountId, amount);
    }

    @Override
    public void transferMoney(long fromAccountId, long toAccountId, int amount, TransactionType type, Locality locality) {
        saveTransaction(fromAccountId, -amount, type, locality);
        accountRepository.withdrawMoney(fromAccountId, amount);
        saveTransaction(toAccountId, amount, type, locality);
        accountRepository.depositMoney(toAccountId, amount);
    }

    @Override
    public void withdrawMoney(long accountId, int amount, TransactionType type, Locality locality) {
        saveTransaction(accountId, -amount, type, locality);
        accountRepository.withdrawMoney(accountId, amount);
    }

    @Override
    public void depositMoney(long accountId, int amount, TransactionType type, Locality locality) {
        saveTransaction(accountId, amount, type, locality);
        accountRepository.depositMoney(accountId, amount);
    }

    private void saveTransaction(long accountId, int amount) {
        var transaction = new Transaction();
        transaction.setAccount(accountRepository.findById(accountId).orElseThrow());
        transaction.setAmount(amount);
        transaction.setCreatedAt(Instant.now());
        transactionService.save(transaction);
    }

    private void saveTransaction(long accountId, int amount, TransactionType type, Locality locality) {
        Account account = accountRepository.findById(accountId).orElseThrow();
        transactionService.createTransaction(account, amount, type, locality);
    }
}
