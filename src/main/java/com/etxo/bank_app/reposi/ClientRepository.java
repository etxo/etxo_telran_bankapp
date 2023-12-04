package com.etxo.bank_app.reposi;

import com.etxo.bank_app.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
