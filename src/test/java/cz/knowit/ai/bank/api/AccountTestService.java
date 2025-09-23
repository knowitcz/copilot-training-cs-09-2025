package cz.knowit.ai.bank.api;

import cz.knowit.ai.bank.database.product.Account;
import cz.knowit.ai.bank.database.product.AccountService;
import cz.knowit.ai.bank.database.product.TransactionType;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * This is a test implementation of the {@link AccountService} interface.
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
final class AccountTestService implements AccountService {

    private final Set<Account> accounts = new HashSet<>();

    public AccountTestService() {
        var account = new Account();
        account.setId(1L);
        account.setName("Test account");
        account.setBalance(1000);

        var account2 = new Account();
        account2.setId(2L);
        account2.setName("Test account 2");
        account2.setBalance(2000);

        accounts.add(account);
        accounts.add(account2);
    }

    @Override
    public Optional<Account> getAccountById(long id) {
        return accounts.stream().filter(account -> account.getId() == id).findFirst();
    }

    @Override
    public void transferMoney(long fromAccountId, long toAccountId, int amount) {
        var fromAccount = getAccountById(fromAccountId).orElseThrow();
        var toAccount = getAccountById(toAccountId).orElseThrow();

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
    }

    @Override
    public void withdrawMoney(long accountId, int amount) {
        var account = getAccountById(accountId).orElseThrow();
        account.setBalance(account.getBalance() - amount);
    }

    @Override
    public void depositMoney(long accountId, int amount) {
        var account = getAccountById(accountId).orElseThrow();
        account.setBalance(account.getBalance() + amount);
    }

    @Override
    public void transferMoney(long fromAccountId, long toAccountId, int amount, TransactionType transactionType, Long localityId) {
        // For test purposes, just delegate to the basic method
        transferMoney(fromAccountId, toAccountId, amount);
    }

    @Override
    public void withdrawMoney(long accountId, int amount, TransactionType transactionType, Long localityId) {
        // For test purposes, just delegate to the basic method
        withdrawMoney(accountId, amount);
    }

    @Override
    public void depositMoney(long accountId, int amount, TransactionType transactionType, Long localityId) {
        // For test purposes, just delegate to the basic method
        depositMoney(accountId, amount);
    }
}
