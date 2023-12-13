package com.etxo.bank_app.dto;

import com.etxo.bank_app.entity.enums.CountryCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AddressDto {
    private Long id;
    @Size(min = 4, max = 7)
    private String postalCode;
    @Size(min = 2, max = 16)
    private String city;
    @NotBlank
    private String street;
    @Size(min = 1, max = 4)
    private String houseNr;
    @NotNull
    @Enumerated
    private CountryCode countryCode;
    @JsonIgnore
    private ClientDto client;
}
