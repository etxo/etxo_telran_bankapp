package com.etxo.bank_app.reposi;

import com.etxo.bank_app.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Transactional
    Set<Account> getAccountsByClientId(Long ClientId);

    //@Query()
}
