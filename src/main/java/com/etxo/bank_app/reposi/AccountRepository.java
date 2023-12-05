package com.etxo.bank_app.reposi;

import com.etxo.bank_app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
