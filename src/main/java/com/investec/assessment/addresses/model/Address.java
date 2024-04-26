package com.investec.assessment.addresses.model;

import lombok.Data;
@Data
public class Address {
    private String id;
    private AddressType type;
    private AddressLineDetail addressLineDetail;
    private ProvinceOrState provinceOrState;
    private String cityOrTown;
    private String postalCode;
    private Country country;
    private String suburbOrDistrict;
    private String lastUpdated;
}
