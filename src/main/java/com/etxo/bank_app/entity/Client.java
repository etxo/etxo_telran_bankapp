package com.etxo.bank_app.entity;

import com.etxo.bank_app.entity.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;
import java.util.Set;

@Entity
//@Table(name = "client")
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "status")
    @Enumerated
    private Status status;

    @NotNull@Length(min = 2, max = 22)
    @Column(name = "first_name")
    private String firstName;

    @NotNull@Length(min = 2, max = 32)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "email", unique = true)
    @Email
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "phone")
    @Length(min = 7, max = 15)
    private String phone;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "manager_id")
    private Manager manager;

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL)
    private Set<Account> accounts;

}