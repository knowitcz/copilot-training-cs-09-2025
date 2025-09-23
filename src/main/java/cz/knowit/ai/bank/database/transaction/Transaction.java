package cz.knowit.ai.bank.database.transaction;

import cz.knowit.ai.bank.database.product.Account;
import cz.knowit.ai.bank.database.product.Locality;
import cz.knowit.ai.bank.database.product.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;

/**
 * Domain entity that represents a transaction
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@Entity
@Table(name = "T_TRANSACTION")
public final class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AMOUNT")
    private int amount;

    @Column(name = "CREATED_AT")
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_TYPE")
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "LOCALITY_ID")
    private Locality locality;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }
}
