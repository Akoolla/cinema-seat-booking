package com.akoolla.cinema.seatbooking.core.impl;

import com.akoolla.cinema.seatbooking.core.ICustomer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Customer.
 *
 * @author richardTiffin
 * @version $Id$
 */
public class Customer implements ICustomer {

    @JsonProperty()
    private final String customerName;
    @JsonProperty()
    private final String contactNumber;

    @JsonCreator
    public Customer(@JsonProperty("customerName") final String customerName,
            @JsonProperty("contactNumber") final String contactNumber) {
        this.contactNumber = contactNumber;
        this.customerName = customerName;
    }

    /**
     * @return
     * @see com.akoolla.cinemabooking.ICustomer#getCustomerName()
     */
    @Override
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @return
     * @see com.akoolla.cinemabooking.ICustomer#getContactNumber()
     */
    @Override
    public String getContactNumber() {
        return contactNumber;
    }

}
