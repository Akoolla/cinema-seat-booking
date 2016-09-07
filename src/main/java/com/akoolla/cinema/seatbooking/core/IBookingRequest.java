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
    int getNumberOfSeats();
    int getNumberOfWheelChairs();
    String getEmail();
}
