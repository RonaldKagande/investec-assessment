package com.investec.assessment.addresses.impl;

import com.investec.assessment.addresses.IAddressPrinter;
import com.investec.assessment.addresses.IAddressValidator;
import com.investec.assessment.addresses.model.Address;
import com.investec.assessment.addresses.model.AddressLineDetail;

import java.util.List;
public class AddressValidatorImpl implements IAddressValidator {

    private final IAddressPrinter printer;
    public static final String COUNTRY_CODE_ZA = "ZA";
    public AddressValidatorImpl(IAddressPrinter printer) {
        this.printer = printer;
    }
    @Override
    public void validateAddresses(List<Address> addresses) {
        System.out.println("Start validating addresses...");
        for (Address address : addresses) {
            if (!isValidAddress(address)) {
                System.out.println("Invalid Address: " + printer.prettyPrintAddress(address));
            }
        }
        System.out.println("Finished validating addresses...\n");
    }
    private boolean isValidAddress(Address address) {
        StringBuilder errorMessage = new StringBuilder();
        boolean isValid = true;

        // Check postal code
        if (!isNumeric(address.getPostalCode())) {
            errorMessage.append("Invalid Postal Code. Postal code must be numeric: ").append(address.getPostalCode()).append("\n");
            isValid = false;
        }

        // Check country
        if (isEmptyOrNull(address.getCountry().getName())) {
            errorMessage.append("Invalid Address: Country is required ").append("\n");
            isValid = false;
        }

        // Check address lines
        AddressLineDetail addressLineDetail = address.getAddressLineDetail();
        if (addressLineDetail == null ||
                (isEmptyOrNull(addressLineDetail.getLine1()) && isEmptyOrNull(addressLineDetail.getLine2()))) {
            errorMessage.append("At least 1 address line is required" ).append("\n");
            isValid = false;
        }

        // Check province for ZA country
        if (COUNTRY_CODE_ZA.equalsIgnoreCase(address.getCountry().getCode()) && (address.getProvinceOrState() == null || isEmptyOrNull(address.getProvinceOrState().getName()))) {
            errorMessage.append("Province is required for South African address.").append("\n");
            isValid = false;
        }

        // Print error message if address is invalid
        if (!isValid) {
            System.out.println("Invalid Address - ID: " + address.getId() + " " +  errorMessage.toString());
        } else {
            System.out.println("Valid Address - ID: " + address.getId());
        }

        return isValid;
    }

    private boolean isEmptyOrNull(String str) {
        return str == null || str.trim().isEmpty();
    }

    private boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

}
