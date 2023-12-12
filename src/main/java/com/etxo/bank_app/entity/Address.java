package com.etxo.bank_app.entity;

import com.etxo.bank_app.entity.enums.CountryCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Table(name = "address")
@NoArgsConstructor
@Getter
@Setter
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "client_id",
            referencedColumnName = "id")
    private Client client;
}
