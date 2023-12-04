package com.etxo.bank_app.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private String description;


}
