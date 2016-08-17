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
    private int numStandardSeats = 0;
    @JsonProperty
    private int numConcessionSeats  = 0;
    @JsonProperty
    private int numWheelChairs  = 0;
    
    
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
    @JsonProperty
    private final ICustomer customer;

    @JsonCreator
    public Booking(
            @JsonProperty("numStandardSeats") int numStandardSeats, 
            @JsonProperty("numConcessionSeats") int numConcessionSeats, 
            @JsonProperty("numWheelChairs") int numWheelChairs, 
            @JsonProperty("customer") ICustomer customer) {
        
        
        bookingReference = new BookingReference();
        this.numStandardSeats = numStandardSeats;
        this.numConcessionSeats = numConcessionSeats;
        this.numWheelChairs = numWheelChairs;
        
        this.customer = customer;
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
    /**
     * @return
     * @see com.akoolla.cinema.seatbooking.core.IBooking#getNumberOfStandardSeats()
     */
    @Override
    public int getNumberOfStandardSeats() {
        return numStandardSeats;
    }
    /**
     * @return
     * @see com.akoolla.cinema.seatbooking.core.IBooking#getNumberOfConcessionSeats()
     */
    @Override
    public int getNumberOfConcessionSeats() {
        return numConcessionSeats;
    }
    /**
     * @return
     * @see com.akoolla.cinema.seatbooking.core.IBooking#getNumberOfWheelChairs()
     */
    @Override
    public int getNumberOfWheelChairs() {
        return getNumberOfConcessionSeats();
    }
}
