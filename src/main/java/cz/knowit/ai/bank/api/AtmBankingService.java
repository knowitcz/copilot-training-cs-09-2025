package cz.knowit.ai.bank.api;

import cz.knowit.ai.bank.database.product.AccountService;
import cz.knowit.ai.bank.database.transaction.LocalityService;
import cz.knowit.ai.bank.database.transaction.TransactionType;
import cz.knowit.ai.bank.validator.AmountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ATM implementation of the {@link BankingService}.
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@Service
final class AtmBankingService implements BankingService {

    private final AccountService accountService;
    private final LocalityService localityService;

    @Autowired
    public AtmBankingService(AccountService accountService, LocalityService localityService) {
        this.accountService = accountService;
        this.localityService = localityService;
    }

    @Override
    public void makeTransfer(long fromAccountId, long toAccountId, int amount) {
        AmountValidator.validateAtmLimit(amount);
        AmountValidator.validateEdgeCases(amount);
        // Use ATM locality (ID -2 from our data.sql)
        var locality = localityService.getLocalityById(-2).orElse(null);
        accountService.transferMoney(fromAccountId, toAccountId, amount, TransactionType.ATM, locality);
    }

    @Override
    public void withdraw(long accountId, int amount) {
        AmountValidator.validateAtmLimit(amount);
        AmountValidator.validateEdgeCases(amount);
        // Use ATM locality (ID -2 from our data.sql)
        var locality = localityService.getLocalityById(-2).orElse(null);
        accountService.withdrawMoney(accountId, amount, TransactionType.ATM, locality);
    }

    @Override
    public void deposit(long accountId, int amount) {
        AmountValidator.validateAtmLimit(amount);
        AmountValidator.validateEdgeCases(amount);
        // Use ATM locality (ID -2 from our data.sql)
        var locality = localityService.getLocalityById(-2).orElse(null);
        accountService.depositMoney(accountId, amount, TransactionType.ATM, locality);
    }
}
