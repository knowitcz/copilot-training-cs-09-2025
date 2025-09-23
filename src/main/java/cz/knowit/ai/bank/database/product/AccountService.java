package cz.knowit.ai.bank.database.product;

import java.util.Optional;

public interface AccountService {

    Optional<Account> getAccountById(long id);
    void transferMoney(long fromAccountId, long toAccountId, int amount);
    void withdrawMoney(long accountId, int amount);
    void depositMoney(long accountId, int amount);
    
    // New methods with transaction locality support
    void transferMoney(long fromAccountId, long toAccountId, int amount, TransactionType transactionType, Long localityId);
    void withdrawMoney(long accountId, int amount, TransactionType transactionType, Long localityId);
    void depositMoney(long accountId, int amount, TransactionType transactionType, Long localityId);
}
