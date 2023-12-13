package com.etxo.bank_app.reposi;

import com.etxo.bank_app.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AccountRepository extends JpaRepository<Account, Long> {

    //@Transactional
    //@Query(value = "select * from account  where client_id = ?1",
    //        nativeQuery = true)
    Set<Account> getAccountsByClientId(Long clientId);

}
