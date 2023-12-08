package com.etxo.bank_app.reposi;

import com.etxo.bank_app.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByEmail(String email);
    Optional<Client> getClientByEmail(String email);
}
