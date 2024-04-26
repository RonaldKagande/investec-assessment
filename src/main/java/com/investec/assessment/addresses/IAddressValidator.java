package com.investec.assessment.addresses;

import com.investec.assessment.addresses.model.Address;

import java.util.List;

public interface IAddressValidator {
    void validateAddresses(List<Address> addresses);
}
