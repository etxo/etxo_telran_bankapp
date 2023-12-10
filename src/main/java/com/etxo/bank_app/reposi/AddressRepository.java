package com.etxo.bank_app.reposi;

import com.etxo.bank_app.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {}
