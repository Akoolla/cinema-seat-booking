package com.akoolla.cinema.seatbooking.core;

import org.joda.time.DateTime;

/**
 * IBooking.
 *
 * @author rich
 * @version $Id$
 */
public interface IBooking {

    /**
     * TODO.
     *
     * @return
     */
    int getNumberOfSeats();

    boolean hasBeenPaidFor();

    DateTime dateOfBooking();

    boolean isCancelled();

    ICustomer getCustomer();

    IBookingReference getBookingReference();
}
