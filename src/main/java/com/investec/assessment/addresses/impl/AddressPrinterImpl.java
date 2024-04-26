package com.investec.assessment.addresses.impl;

import com.investec.assessment.addresses.IAddressPrinter;
import com.investec.assessment.addresses.model.Address;
import com.investec.assessment.addresses.model.AddressLineDetail;

import java.util.List;

public class AddressPrinterImpl implements IAddressPrinter {

   @Override
   public String prettyPrintAddress(Address address) {
       StringBuilder builder = new StringBuilder();

       builder.append("Type: ")
               .append(address.getType().getName())
               .append(": ");

       AddressLineDetail addressLineDetail = address.getAddressLineDetail();
       if (addressLineDetail != null) {
           String line1 = addressLineDetail.getLine1() != null ? addressLineDetail.getLine1() : "";
           String line2 = addressLineDetail.getLine2() != null ? " " + addressLineDetail.getLine2() : "";
           builder.append(line1)
                   .append(line2);
       }

       builder.append(" - ")
               .append(address.getCityOrTown() != null ? address.getCityOrTown() : "")
               .append(" - ")
               .append(address.getProvinceOrState() != null ? address.getProvinceOrState().getName() : "")
               .append("/")
               .append(address.getPostalCode() != null ? address.getPostalCode() : "")
               .append(" - ")
               .append(address.getCountry() != null ? address.getCountry().getName() : "");


       return builder.toString();
   }

    @Override
    public void prettyPrintAllAddresses(List<Address> addresses) {
        System.out.println("Start Executing prettyPrintAllAddresses() method...");
        for (Address address : addresses) {
            System.out.println(prettyPrintAddress(address));
        }
        System.out.println("Finished Executing prettyPrintAllAddresses() method...\n");

    }

    @Override
    public void printAddressOfType(List<Address> addresses, String type) {
        System.out.println("Start Executing printAddressesOfType() method...");
        for (Address address : addresses) {
            if (address.getType().getName().equalsIgnoreCase(type)) {
                System.out.println(prettyPrintAddress(address));
            }
        }
        System.out.println("Finished Executing printAddressesOfType() method...\n");
    }

}
