package com.etxo.bank_app.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
//@Table(name = "transaction")
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    private Timestamp createdAt;

}
