package cz.knowit.ai.bank.database.product;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface AccountRepository extends JpaRepository<Account, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.balance = (a.balance - ?2) WHERE a.id = ?1")
    void withdrawMoney(Long accountId, int amount);

    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.balance = (a.balance + ?2) WHERE a.id = ?1")
    void depositMoney(Long accountId, int amount);
}
