package com.akoolla.cinema.seatbooking.core.impl;

import java.util.UUID;

import org.pojomatic.annotations.AutoProperty;

import com.akoolla.cinema.seatbooking.core.IBookingReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;

/**
 * BookingReference.
 *
 * @author rich
 * @version $Id$
 */
@AutoProperty
@EqualsAndHashCode
public class BookingReference implements IBookingReference {
    public static final String REF_JSON_PROP = "bookingReference";
    
    @JsonProperty(REF_JSON_PROP)
    private final String reference;
    
    public BookingReference() {
        this.reference = UUID.randomUUID().toString();
    }
    
    @JsonCreator
    private BookingReference(@JsonProperty(REF_JSON_PROP) String reference){
        this.reference = reference;
    }
    
    /**
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return reference;
    }
}
