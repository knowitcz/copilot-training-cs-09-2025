package cz.knowit.ai.bank.api;

interface BankService {

    void makeTransferAtBranch(long fromAccountId, long toAccountId, int amount);
    void makeATMTransfer(long fromAccountId, long toAccountId, int amount);

    void withdrawAtBranch(long accountId, int amount);
    void withdrawFromATM(long accountId, int amount);

    void depositAtBranch(long accountId, int amount);
    void depositIntoATM(long accountId, int amount);
}
