package com.etxo.bank_app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "account")
@Table(name = "account")
@Setter
@Getter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "client_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @Column(name = "iban", nullable = false)
    private String iban;

    @Column(name = "bic", nullable = false)
    private String bic;

    @Column(name = "type")
    private AccountType type;

    @Column(name = "status")
    private Status status;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "currency_code")
    @Max(2)
    private String currencyCode;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "account")
    @JoinColumn(name = "account_id")
    private List<Agreement> agreements;
}
