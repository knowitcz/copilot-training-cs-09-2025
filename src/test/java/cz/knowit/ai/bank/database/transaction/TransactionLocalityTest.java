package cz.knowit.ai.bank.database.transaction;

import cz.knowit.ai.bank.database.product.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for transaction locality functionality.
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
final class TransactionLocalityTest {

    private TransactionProdService transactionService;
    private TestTransactionRepository transactionRepository;
    private Account testAccount;
    private Locality testLocality;

    @BeforeEach
    void setUp() {
        transactionRepository = new TestTransactionRepository();
        transactionService = new TransactionProdService(transactionRepository);
        
        testAccount = new Account();
        testAccount.setId(1L);
        testAccount.setName("Test Account");
        testAccount.setBalance(1000);
        
        testLocality = new Locality();
        testLocality.setId(1L);
        testLocality.setName("Test Branch");
        testLocality.setAddress("Test Address 123");
        testLocality.setCity("Test City");
    }

    @Test
    void createOnlineTransaction() {
        transactionService.createTransaction(testAccount, 100, TransactionType.ONLINE, null);
        
        var transaction = transactionRepository.getLastSavedTransaction();
        
        assertThat(transaction).isNotNull();
        assertThat(transaction.getAccount()).isEqualTo(testAccount);
        assertThat(transaction.getAmount()).isEqualTo(100);
        assertThat(transaction.getType()).isEqualTo(TransactionType.ONLINE);
        assertThat(transaction.getLocality()).isNull();
        assertThat(transaction.getCreatedAt()).isNotNull();
    }

    @Test
    void createBranchTransaction() {
        transactionService.createTransaction(testAccount, 200, TransactionType.BRANCH, testLocality);
        
        var transaction = transactionRepository.getLastSavedTransaction();
        
        assertThat(transaction).isNotNull();
        assertThat(transaction.getAccount()).isEqualTo(testAccount);
        assertThat(transaction.getAmount()).isEqualTo(200);
        assertThat(transaction.getType()).isEqualTo(TransactionType.BRANCH);
        assertThat(transaction.getLocality()).isEqualTo(testLocality);
        assertThat(transaction.getCreatedAt()).isNotNull();
    }

    @Test
    void createAtmTransaction() {
        transactionService.createTransaction(testAccount, -50, TransactionType.ATM, testLocality);
        
        var transaction = transactionRepository.getLastSavedTransaction();
        
        assertThat(transaction).isNotNull();
        assertThat(transaction.getAccount()).isEqualTo(testAccount);
        assertThat(transaction.getAmount()).isEqualTo(-50);
        assertThat(transaction.getType()).isEqualTo(TransactionType.ATM);
        assertThat(transaction.getLocality()).isEqualTo(testLocality);
        assertThat(transaction.getCreatedAt()).isNotNull();
    }

    /**
     * Test implementation of TransactionRepository for unit testing.
     */
    private static class TestTransactionRepository implements TransactionRepository {
        private Transaction lastSavedTransaction;

        @Override
        public Transaction save(Transaction transaction) {
            this.lastSavedTransaction = transaction;
            return transaction;
        }

        public Transaction getLastSavedTransaction() {
            return lastSavedTransaction;
        }

        // Minimal implementations for required interface methods
        @Override
        public java.util.List<Transaction> findAll() { return java.util.Collections.emptyList(); }
        @Override
        public java.util.List<Transaction> findAllById(Iterable<Long> longs) { return java.util.Collections.emptyList(); }
        @Override
        public long count() { return 0; }
        @Override
        public void deleteById(Long aLong) {}
        @Override
        public void delete(Transaction entity) {}
        @Override
        public void deleteAllById(Iterable<? extends Long> longs) {}
        @Override
        public void deleteAll(Iterable<? extends Transaction> entities) {}
        @Override
        public void deleteAll() {}
        @Override
        public <S extends Transaction> java.util.List<S> saveAll(Iterable<S> entities) { return java.util.Collections.emptyList(); }
        @Override
        public java.util.Optional<Transaction> findById(Long aLong) { return java.util.Optional.empty(); }
        @Override
        public boolean existsById(Long aLong) { return false; }
        @Override
        public void flush() {}
        @Override
        public <S extends Transaction> S saveAndFlush(S entity) { 
            save(entity);
            return entity;
        }
        @Override
        public <S extends Transaction> java.util.List<S> saveAllAndFlush(Iterable<S> entities) { return java.util.Collections.emptyList(); }
        @Override
        public void deleteAllInBatch(Iterable<Transaction> entities) {}
        @Override
        public void deleteAllByIdInBatch(Iterable<Long> longs) {}
        @Override
        public void deleteAllInBatch() {}
        @Override
        public Transaction getOne(Long aLong) { return null; }
        @Override
        public Transaction getById(Long aLong) { return null; }
        @Override
        public Transaction getReferenceById(Long aLong) { return null; }
        @Override
        public <S extends Transaction> java.util.Optional<S> findOne(org.springframework.data.domain.Example<S> example) { return java.util.Optional.empty(); }
        @Override
        public <S extends Transaction> java.util.List<S> findAll(org.springframework.data.domain.Example<S> example) { return java.util.Collections.emptyList(); }
        @Override
        public <S extends Transaction> java.util.List<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Sort sort) { return java.util.Collections.emptyList(); }
        @Override
        public <S extends Transaction> org.springframework.data.domain.Page<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Pageable pageable) { return null; }
        @Override
        public <S extends Transaction> long count(org.springframework.data.domain.Example<S> example) { return 0; }
        @Override
        public <S extends Transaction> boolean exists(org.springframework.data.domain.Example<S> example) { return false; }
        @Override
        public <S extends Transaction, R> R findBy(org.springframework.data.domain.Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return null; }
        @Override
        public java.util.List<Transaction> findAll(org.springframework.data.domain.Sort sort) { return java.util.Collections.emptyList(); }
        @Override
        public org.springframework.data.domain.Page<Transaction> findAll(org.springframework.data.domain.Pageable pageable) { return null; }
    }
}