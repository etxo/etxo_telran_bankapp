package com.etxo.bank_app.dto;

import com.etxo.bank_app.entity.enums.CountryCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AddressDto {
    private Long id;
    private String postalCode;
    private String city;
    private String street;
    private String houseNr;
    private CountryCode countryCode;
}
