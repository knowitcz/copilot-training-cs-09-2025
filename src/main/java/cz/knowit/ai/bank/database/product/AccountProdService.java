package cz.knowit.ai.bank.database.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class AccountProdService implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    AccountProdService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> getAccountById(long id) {
        return accountRepository.findById(id);
    }

    @Override
    public void transferMoney(long fromAccountId, long toAccountId, int amount) {
        accountRepository.withdrawMoney(fromAccountId, amount);
        accountRepository.depositMoney(toAccountId, amount);
    }

    @Override
    public void withdrawMoney(long accountId, int amount) {
        accountRepository.withdrawMoney(accountId, amount);
    }

    @Override
    public void depositMoney(long accountId, int amount) {
        accountRepository.depositMoney(accountId, amount);
    }
}
