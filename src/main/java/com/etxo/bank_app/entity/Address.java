package com.etxo.bank_app.entity;

import com.etxo.bank_app.entity.enums.CountryCode;
import jakarta.persistence.*;
import lombok.Data;

@Entity
//@Table(name = "address")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house_nr")
    private String houseNr;

    @Column(name = "country_code")
    private CountryCode countryCode;

    @OneToOne(mappedBy = "address")
    private Client client;
}
