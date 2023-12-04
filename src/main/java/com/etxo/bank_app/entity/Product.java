package com.etxo.bank_app.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private ProductStatus status;
    @Column(name = "currency_code")
    private int currencyCode;
    @Column(name = "interest_rate")
    private int interestRate;

    //credit_limit int,
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
