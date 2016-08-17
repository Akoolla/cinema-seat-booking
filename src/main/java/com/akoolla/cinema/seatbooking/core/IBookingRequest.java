package com.akoolla.cinema.seatbooking.core;

/**
 * IBookingRequest.
 *
 * @author rich
 * @version $Id$
 */
public interface IBookingRequest {
    String getCustomerName();

    String getContactNumber();

    boolean overrideSeatLimits();

    void overrideSeatLimits(boolean overide);
    
    /**
     * This is the number of concession + standard seats.
     * 
     * @return
     */
    int getNumberOfSeats();
    
    int getNumberOfStandardSeats();
    
    int getNumberOfConcessionSeats();
    
    int getNumberOfWheelChairs();
}
