package cz.knowit.ai.bank.api;

import cz.knowit.ai.bank.database.product.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Branch implementation of the {@link BankingService}.
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@Service
final class BranchBankingService implements BankingService {

    private final AccountService accountService;

    @Autowired
    BranchBankingService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void makeTransfer(long fromAccountId, long toAccountId, int amount) {
        accountService.transferMoney(fromAccountId, toAccountId, amount);
    }

    @Override
    public void withdraw(long accountId, int amount) {
        accountService.withdrawMoney(accountId, amount);
    }

    @Override
    public void deposit(long accountId, int amount) {
        accountService.depositMoney(accountId, amount);
    }
}
