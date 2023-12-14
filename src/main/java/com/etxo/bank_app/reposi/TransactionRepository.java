package com.etxo.bank_app.reposi;

import com.etxo.bank_app.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select t from Transaction t join t.sender s where s.id = ?1")
    Set<Transaction> findTransactionByAccountId(Long accountId);
}
