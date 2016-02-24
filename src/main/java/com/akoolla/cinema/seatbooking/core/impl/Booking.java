package com.akoolla.cinema.seatbooking.core.impl;

import org.joda.time.DateTime;
import org.pojomatic.annotations.AutoProperty;

import com.akoolla.cinema.seatbooking.core.IBooking;
import com.akoolla.cinema.seatbooking.core.IBookingReference;
import com.akoolla.cinema.seatbooking.core.ICustomer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import lombok.EqualsAndHashCode;

/**
 * Booking.
 *
 * @author rich
 * @version $Id$
 */
@AutoProperty
@EqualsAndHashCode
public class Booking implements IBooking {

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
    @JsonProperty
    private final IBookingReference bookingReference;
    
    @JsonProperty
    private int numberOfSeats = 0;
    
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
    @JsonProperty
    private final ICustomer customer;

    @JsonCreator
    public Booking(@JsonProperty("numberOfSeats") int numberOfSeats, @JsonProperty("customer") ICustomer customer) {
        bookingReference = new BookingReference();
        this.numberOfSeats = numberOfSeats;
        this.customer = customer;
    }

    /**
     * @return
     * @see com.akoolla.cinemabooking.IBooking#getNumberOfSeats()
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    /**
     * @return
     * @see com.akoolla.cinemabooking.IBooking#hasBeenPaidFor()
     */
    public boolean hasBeenPaidFor() {
        return false;
    }

    /**
     * @return
     * @see com.akoolla.cinemabooking.IBooking#dateOfBooking()
     */
    public DateTime dateOfBooking() {
        return null;
    }

    /**
     * @return
     * @see com.akoolla.cinemabooking.IBooking#isCancelled()
     */
    public boolean isCancelled() {
        return false;
    }

    /**
     * @return
     * @see com.akoolla.cinemabooking.IBooking#getCustomer()
     */
    public ICustomer getCustomer() {
        return customer;
    }

    /**
     * @return
     * @see com.akoolla.cinemabooking.IBooking#getBookingReference()
     */
    public IBookingReference getBookingReference() {
        return bookingReference;
    }
}
