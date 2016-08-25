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
    public static final String NAME_JSON_PROP = "name";
    public static final String TEL_JSON_PROP = "tel";
    public static final String EMAIL_JSON_PROP = "email";
    
    @JsonProperty()
    private final String name;
    @JsonProperty()
    private final String tel;
    @JsonProperty()
    private final String email;

    
    @JsonCreator
    public Customer(
            @JsonProperty(NAME_JSON_PROP) final String customerName,
            @JsonProperty(TEL_JSON_PROP) final String contactNumber) {
        this.name = customerName;
        this.tel = contactNumber;
        this.email = "TODO";
    }

    /**
     * @return
     * @see com.akoolla.cinemabooking.ICustomer#getCustomerName()
     */
    @Override
    public String getCustomerName() {
        return name;
    }

    /**
     * @return
     * @see com.akoolla.cinemabooking.ICustomer#getContactNumber()
     */
    @Override
    public String getContactNumber() {
        return tel;
    }

}
