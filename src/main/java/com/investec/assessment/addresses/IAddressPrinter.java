package com.investec.assessment.addresses;

import com.investec.assessment.addresses.model.Address;

import java.util.List;

public interface IAddressPrinter {
    String prettyPrintAddress(Address address);

    void prettyPrintAllAddresses(List<Address> addresses);

    void printAddressOfType(List<Address> addresses, String type);

}
