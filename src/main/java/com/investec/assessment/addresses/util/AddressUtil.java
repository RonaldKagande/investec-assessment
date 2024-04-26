package com.investec.assessment.addresses.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.investec.assessment.addresses.model.Address;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AddressUtil {
    public static List<Address> loadAddressesFromFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(filePath);
            Address[] addressArray = objectMapper.readValue(file, Address[].class);
            return Arrays.asList(addressArray);
        } catch (IOException e) {
            System.err.println("Error loading addresses from file: " + e.getMessage());
            return null;
        }
    }
}
