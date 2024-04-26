package com.investec.assessment.addresses;

import com.investec.assessment.addresses.impl.AddressPrinterImpl;
import com.investec.assessment.addresses.impl.AddressValidatorImpl;
import com.investec.assessment.addresses.model.Address;
import java.util.List;

import static com.investec.assessment.addresses.util.AddressUtil.loadAddressesFromFile;


public class AddressApp {

    public static void main(String[] args) {

        IAddressPrinter printer = new AddressPrinterImpl();
        IAddressValidator validator = new AddressValidatorImpl(printer);

        // Load addresses from file
        List<Address> addresses = loadAddressesFromFile("src/main/resources/addresses.json");

        if (addresses != null) {

            printer.prettyPrintAllAddresses(addresses);
            printer.printAddressOfType(addresses, "Postal Address");
            validator.validateAddresses(addresses);

        } else {
            System.out.println("Failed to load addresses from file.");
        }
    }

}
