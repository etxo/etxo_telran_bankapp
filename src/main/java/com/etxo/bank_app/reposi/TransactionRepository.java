package com.etxo.bank_app.reposi;

import com.etxo.bank_app.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select t from Transaction t join t.sender s where s.id = ?1")
    List<Transaction> findTransactionsByAccountId(Long accountId);

    @Query("select t from Transaction t join t.sender s join s.client c where c.id = ?1")
    List<Transaction> findTransactionsByClientId(Long clientId);
}
