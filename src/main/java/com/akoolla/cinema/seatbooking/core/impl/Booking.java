package com.akoolla.cinema.seatbooking.core.impl;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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
 * @author richardt
 */
@AutoProperty
@EqualsAndHashCode
public class Booking implements IBooking {
    public static final String REF_JSON_PROP = "bookingReference";
    public static final String STANDARD_SEATS_JSON_PROP = "numberOfSeats";
    public static final String WHEELCHAIRS_JSON_PROP = "numWheelChairs";
    public static final String CUSTOMER_JSON_PROP = "customer";
    public static final String BOOKING_DATE = "dateOfBooking";
    
    
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
    @JsonProperty(REF_JSON_PROP)
    private final IBookingReference bookingReference;
    
    @JsonProperty(STANDARD_SEATS_JSON_PROP)
    private int numStandardSeats = 0;
    
    @JsonProperty(WHEELCHAIRS_JSON_PROP)
    private int numWheelChairs  = 0;
        
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
    @JsonProperty
    private final ICustomer customer;

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
    @JsonProperty(BOOKING_DATE)
    private final DateTime dateOfBooking;
    
    /**
     * Creates a new booking with a new unique booking reference
     * @param numberOfSeats
     * @param numWheelChairs
     * @param customer
     */
    public Booking(
            int numberOfSeats, 
            int numWheelChairs, 
            ICustomer customer) {
        
        this.bookingReference = new BookingReference();
        this.numStandardSeats = numberOfSeats;
        this.numWheelChairs = numWheelChairs;
        this.customer = customer;
        
        //TODO: Something to set default Zone maybe in the constructor
        this.dateOfBooking = DateTime.now(DateTimeZone.getDefault());
    }
    
    @JsonCreator
    private Booking(
            @JsonProperty(STANDARD_SEATS_JSON_PROP) int numberOfSeats,
            @JsonProperty(WHEELCHAIRS_JSON_PROP) int numWheelChairs, 
            @JsonProperty(CUSTOMER_JSON_PROP) ICustomer customer,
            @JsonProperty(REF_JSON_PROP) IBookingReference bookingRef,
            @JsonProperty(BOOKING_DATE) DateTime dateOfBooking) {

        this.numStandardSeats = numberOfSeats;
        this.numWheelChairs = numWheelChairs;
        this.bookingReference = bookingRef;
        this.dateOfBooking = dateOfBooking;
        
        this.customer = customer;
    }
    
    /**
     * @return
     * @see com.akoolla.cinemabooking.IBooking#dateOfBooking()
     */
    public DateTime dateOfBooking() {
        return dateOfBooking;
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
     * @see com.akoolla.cinema.seatbooking.core.IBooking#getNumberOfSeats()
     */
    @Override
    public int getNumberOfSeats() {
        return numStandardSeats;
    }
    /**
     * @return
     * @see com.akoolla.cinema.seatbooking.core.IBooking#getNumberOfWheelChairs()
     */
    @Override
    public int getNumberOfWheelChairs() {
        return numWheelChairs;
    }
}
