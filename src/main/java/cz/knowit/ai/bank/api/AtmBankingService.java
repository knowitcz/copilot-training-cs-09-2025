package cz.knowit.ai.bank.api;

import cz.knowit.ai.bank.database.product.AccountService;
import cz.knowit.ai.bank.database.product.TransactionType;
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
    private static final Long DEFAULT_ATM_LOCALITY_ID = -2L; // Prague ATM Center

    @Autowired
    public AtmBankingService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void makeTransfer(long fromAccountId, long toAccountId, int amount) {
        AmountValidator.validateAtmLimit(amount);
        AmountValidator.validateEdgeCases(amount);
        accountService.transferMoney(fromAccountId, toAccountId, amount, TransactionType.ATM, DEFAULT_ATM_LOCALITY_ID);
    }

    @Override
    public void withdraw(long accountId, int amount) {
        AmountValidator.validateAtmLimit(amount);
        AmountValidator.validateEdgeCases(amount);
        accountService.withdrawMoney(accountId, amount, TransactionType.ATM, DEFAULT_ATM_LOCALITY_ID);
    }

    @Override
    public void deposit(long accountId, int amount) {
        AmountValidator.validateAtmLimit(amount);
        AmountValidator.validateEdgeCases(amount);
        accountService.depositMoney(accountId, amount, TransactionType.ATM, DEFAULT_ATM_LOCALITY_ID);
    }
}
