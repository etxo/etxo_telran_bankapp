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
    @Size(min = 4, max = 7)
    private String postalCode;

    @Column(name = "city")
    @Size(min = 2, max = 16)
    private String city;

    @Column(name = "street")
    @NotBlank
    private String street;

    @Column(name = "house_nr")
    @Size(min = 1, max = 4)
    private String houseNr;

    @Column(name = "country_code")
    @NotNull
    private CountryCode countryCode;
}
