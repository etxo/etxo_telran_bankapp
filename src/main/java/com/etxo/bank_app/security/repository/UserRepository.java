package com.etxo.bank_app.security.repository;

import com.etxo.bank_app.security.entity.Role;
import com.etxo.bank_app.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByRole(Role role);
    Optional<User> findByEmail(String email);
}
