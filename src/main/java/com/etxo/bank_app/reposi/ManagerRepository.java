package com.etxo.bank_app.reposi;

import com.etxo.bank_app.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

}
