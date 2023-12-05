package com.etxo.bank_app.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Address {
    private final String postalCode;
    private final String city;
    private final String street;
    private final int houseNr;
    private final String countryCode;
}
