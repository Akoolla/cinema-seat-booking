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
    
    @JsonProperty(NAME_JSON_PROP)
    private final String name;
    @JsonProperty(TEL_JSON_PROP)
    private final String tel;
    @JsonProperty(EMAIL_JSON_PROP)
    private final String email;

    
    @JsonCreator
    public Customer(
            @JsonProperty(NAME_JSON_PROP) final String customerName,
            @JsonProperty(TEL_JSON_PROP) final String contactNumber,
            @JsonProperty(EMAIL_JSON_PROP) final String email) {
        this.name = customerName;
        this.tel = contactNumber;
        this.email = email;
    }

    /**
     * @return
     * @see com.akoolla.cinemabooking.ICustomer#getCustomerName()
     */
    @JsonProperty(NAME_JSON_PROP)
    @Override
    public String getCustomerName() {
        return name;
    }

    /**
     * @return
     * @see com.akoolla.cinemabooking.ICustomer#getContactNumber()
     */
    @JsonProperty(TEL_JSON_PROP)
    @Override
    public String getContactNumber() {
        return tel;
    }

    /**
     * @return
     * @see com.akoolla.cinema.seatbooking.core.ICustomer#getEmail()
     */
    @Override
    public String getEmail() {
        return email;
    }

}
