package com.etxo.bank_app.entity;

import com.etxo.bank_app.entity.enums.AccountType;
import com.etxo.bank_app.entity.enums.Currency;
import com.etxo.bank_app.entity.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Entity
//@Table(name = "account")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "iban", nullable = false)
    private String iban;

    @Column(name = "bic", nullable = false)
    private String bic;

    @Column(name = "account_type")
    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "currency_code")
    @Enumerated(value = EnumType.STRING)
    private Currency currencyCode;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private Set<Transaction> senderTransactions;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private Set<Transaction> receiverTransactions;
}
