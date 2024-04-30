package com.investec.assessment.addresses.impl;

import com.investec.assessment.addresses.model.Address;
import com.investec.assessment.addresses.model.AddressLineDetail;
import com.investec.assessment.addresses.model.AddressType;
import com.investec.assessment.addresses.model.Country;
import com.investec.assessment.addresses.model.ProvinceOrState;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AddressPrinterImplTest {
    @Mock
    private Address mockAddress;
    @Mock
    private AddressLineDetail mockAddressLineDetail;

    @InjectMocks
    private AddressPrinterImpl addressPrinter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPrettyPrintAddress() {

        ProvinceOrState state = new ProvinceOrState();
        state.setName("State");

        AddressType addressType = new AddressType();
        addressType.setName("Home");

        Country country = new Country();
        country.setName("Country");


        // Mock the behavior of the address and its details
        when(mockAddress.getType()).thenReturn(addressType);
        when(mockAddress.getAddressLineDetail()).thenReturn(mockAddressLineDetail);
        when(mockAddressLineDetail.getLine1()).thenReturn("123 Street");
        when(mockAddressLineDetail.getLine2()).thenReturn("Apt 101");
        when(mockAddress.getCityOrTown()).thenReturn("City");
        when(mockAddress.getProvinceOrState()).thenReturn(state);
        when(mockAddress.getPostalCode()).thenReturn("12345");
        when(mockAddress.getCountry()).thenReturn(country);

        // Expected result after pretty printing
        String expectedResult = "Type: Home: 123 Street Apt 101 - City - State/12345 - Country";

        // Test the prettyPrintAddress method
        String printedAddress = addressPrinter.prettyPrintAddress(mockAddress);
        assertEquals(expectedResult, printedAddress);
    }

    @Test
    public void testPrettyPrintAddressWithNullAddressLineDetail() {
        AddressType addressType = new AddressType();
        addressType.setName("Work");

        Country country = new Country();
        country.setName("Country");

        // Mock the behavior of the address with null AddressLineDetail
        when(mockAddress.getType()).thenReturn(addressType);
        when(mockAddress.getAddressLineDetail()).thenReturn(null);
        when(mockAddress.getCityOrTown()).thenReturn("City");
        when(mockAddress.getProvinceOrState()).thenReturn(null);
        when(mockAddress.getPostalCode()).thenReturn("12345");
        when(mockAddress.getCountry()).thenReturn(country);

        // Expected result after pretty printing
        String expectedResult = "Type: Work:  - City - /12345 - Country";

        // Test the prettyPrintAddress method
        String printedAddress = addressPrinter.prettyPrintAddress(mockAddress);
        assertEquals(expectedResult, printedAddress);
    }

    @Test
    public void testPrettyPrintAddressWithEmptyLines() {
        AddressType addressType = new AddressType();
        addressType.setName("Office");

        Country country = new Country();
        country.setName("Country");

        // Mock the behavior of the address with empty AddressLineDetail lines
        when(mockAddress.getType()).thenReturn(addressType);
        when(mockAddress.getAddressLineDetail()).thenReturn(mockAddressLineDetail);
        when(mockAddressLineDetail.getLine1()).thenReturn("");
        when(mockAddressLineDetail.getLine2()).thenReturn("");
        when(mockAddress.getCityOrTown()).thenReturn("City");
        when(mockAddress.getProvinceOrState()).thenReturn(null);
        when(mockAddress.getPostalCode()).thenReturn("12345");
        when(mockAddress.getCountry()).thenReturn(country);

        // Expected result after pretty printing
        String expectedResult = "Type: Office:   - City - /12345 - Country";

        // Test the prettyPrintAddress method
        String printedAddress = addressPrinter.prettyPrintAddress(mockAddress);
        assertEquals(expectedResult, printedAddress);
    }

    @Test
    public void testPrettyPrintAllAddresses() {
        // Create a list of addresses
        List<Address> addresses = createMockAddresses();

        // Set up ByteArrayOutputStream to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method to be tested
        addressPrinter.prettyPrintAllAddresses(addresses);

        // Reset System.out
        System.setOut(System.out);

        // Test the printed output
        String printedOutput = outContent.toString();
        String expectedOutput = "Start printing all addresses...\n" +
                "Type: Home: 123 Street Apt 101 - City - State/12345 - Country\n" +
                "Type: Work:  - City - /12345 - Country\n" +
                "Type: Office:  - City - /12345 - Country\n" +
                "Finished printing all addresses...\n\n";
        String normalizedExpected = normalizeLineEndings(expectedOutput);
        String normalizedActual = normalizeLineEndings(printedOutput);
        assertEquals(normalizedExpected, normalizedActual);
    }

    @Test
    public void testPrintAddressOfType() {
        // Create a list of addresses
        List<Address> addresses = createMockAddresses();

        // Set up ByteArrayOutputStream to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method to be tested
        addressPrinter.printAddressOfType(addresses, "Home");

        // Reset System.out
        System.setOut(System.out);

        // Test the printed output
        String printedOutput = outContent.toString();
        String expectedOutput = "Start printing address of type...\n" +
                "Type: Home: 123 Street Apt 101 - City - State/12345 - Country\n" +
                "Finished printing address of type...\n\n";
        String normalizedExpected = normalizeLineEndings(expectedOutput);
        String normalizedActual = normalizeLineEndings(printedOutput);
        assertEquals(normalizedExpected, normalizedActual);

    }

    // Helper method to create mock addresses for testing
    private List<Address> createMockAddresses() {
        List<Address> addresses = new ArrayList<>();

        AddressType addressType = new AddressType();
        addressType.setName("Home");

        AddressLineDetail addressLineDetail = new AddressLineDetail();
        addressLineDetail.setLine1("123 Street");
        addressLineDetail.setLine2("Apt 101");

        ProvinceOrState provinceOrState = new ProvinceOrState();
        provinceOrState.setName("State");

        Country country = new Country();
        country.setName("Country");


        Address address1 = new Address();
        address1.setType(addressType);
        address1.setAddressLineDetail(addressLineDetail);
        address1.setCityOrTown("City");
        address1.setProvinceOrState(provinceOrState);
        address1.setPostalCode("12345");
        address1.setCountry(country);
        addresses.add(address1);

        AddressType addressType2 = new AddressType();
        addressType2.setName("Work");
        Country country2 = new Country();
        country2.setName("Country");

        Address address2 = new Address();
        address2.setType(addressType2);
        address2.setCityOrTown("City");
        address2.setPostalCode("12345");
        address2.setCountry(country2);
        addresses.add(address2);


        AddressType addressType3 = new AddressType();
        addressType3.setName("Office");
        Country country3 = new Country();
        country3.setName("Country");

        Address address3 = new Address();
        address3.setType(addressType3);
        address3.setCityOrTown("City");
        address3.setPostalCode("12345");
        address3.setCountry(country3);
        addresses.add(address3);

        return addresses;
    }

    private String normalizeLineEndings(String text) {
        return text.replaceAll("\\r\\n", "\n");
    }


}
