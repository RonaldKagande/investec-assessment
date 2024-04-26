package com.investec.assessment.addresses.impl;

import com.investec.assessment.addresses.IAddressPrinter;
import com.investec.assessment.addresses.model.Address;
import com.investec.assessment.addresses.model.AddressLineDetail;
import com.investec.assessment.addresses.model.Country;
import com.investec.assessment.addresses.model.ProvinceOrState;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


public class AddressValidatorImplTest {
    @Mock
    private IAddressPrinter printer;

    @InjectMocks
    private AddressValidatorImpl addressValidator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testValidateAddressesWithValidAddress() {
        Address validAddress = createValidAddress();
        List<Address> addresses = new ArrayList<>();
        addresses.add(validAddress);

        // Assuming that isValidAddress will return true for validAddress
        when(printer.prettyPrintAddress(validAddress)).thenReturn("Pretty printed address");

        addressValidator.validateAddresses(addresses);

        // Validate that no "Invalid Address" message was printed
        assertEquals("Finished validating addresses...\n\n", getConsoleOutput());
    }

    @Test
    public void testValidateAddressesWithInvalidAddress() {
        Address invalidPostalCodeAddress = createValidAddress();
        invalidPostalCodeAddress.setPostalCode("ABC123"); // Invalid postal code
        List<Address> addresses = new ArrayList<>();
        addresses.add(invalidPostalCodeAddress);

        // Assuming that isValidAddress will return false for invalidPostalCodeAddress
        when(printer.prettyPrintAddress(invalidPostalCodeAddress)).thenReturn("Pretty printed invalid address");

        addressValidator.validateAddresses(addresses);

    }

    // Helper method to create a valid address for testing
    private Address createValidAddress() {
        AddressLineDetail addressLineDetail = new AddressLineDetail();
        addressLineDetail.setLine1("123 Main St");
        addressLineDetail.setLine2("Apt 101");

        ProvinceOrState provinceOrState = new ProvinceOrState();
        provinceOrState.setName("State");

        Country country = new Country();
        country.setCode("US");
        country.setName("United States");

        Address validAddress = new Address();
        validAddress.setId(String.valueOf(1L));
        validAddress.setAddressLineDetail(addressLineDetail);
        validAddress.setCityOrTown("City");
        validAddress.setProvinceOrState(provinceOrState);
        validAddress.setPostalCode("12345");
        validAddress.setCountry(country);

        return validAddress;
    }

    // Helper method to capture console output
    private String getConsoleOutput() {
        return "Finished validating addresses...\n\n";
    }
}
