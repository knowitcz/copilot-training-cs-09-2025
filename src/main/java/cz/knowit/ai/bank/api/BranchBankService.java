package cz.knowit.ai.bank.api;

import cz.knowit.ai.bank.database.product.AccountProdService;
import cz.knowit.ai.bank.database.product.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
final class BranchBankService implements BankService {

    private final AccountService accountService;

    @Autowired
    public BranchBankService(AccountProdService accountService) {
        this.accountService = Objects.requireNonNull(accountService);
    }

    @Override
    public void makeTransferAtBranch(long fromAccountId, long toAccountId, int amount) {
        accountService.transferMoney(fromAccountId, toAccountId, amount);
    }

    @Override
    public void withdrawAtBranch(long accountId, int amount) {
        accountService.withdrawMoney(accountId, amount);
    }

    @Override
    public void depositAtBranch(long accountId, int amount) {
        accountService.depositMoney(accountId, amount);
    }

    @Override
    public void makeATMTransfer(long fromAccountId, long toAccountId, int amount) {
        throw new UnsupportedOperationException("You cannot make an ATM transfer at a branch.");
    }

    @Override
    public void withdrawFromATM(long accountId, int amount) {
        throw new UnsupportedOperationException("You cannot withdraw from an ATM at a branch.");
    }

    @Override
    public void depositIntoATM(long accountId, int amount) {
        throw new UnsupportedOperationException("You cannot deposit into an ATM at a branch.");
    }
}
