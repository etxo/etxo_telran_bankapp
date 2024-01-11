package com.etxo.bank_app.reposi;

import com.etxo.bank_app.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select a from Account a join a.client c where c.id = ?1")
    List<Account> getAccountsByClientId(Long clientId);

}
