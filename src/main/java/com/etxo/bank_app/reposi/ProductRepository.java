package com.etxo.bank_app.reposi;

import com.etxo.bank_app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
