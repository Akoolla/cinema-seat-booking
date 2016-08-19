package com.akoolla.cinema.seatbooking.core;

import java.util.List;

import org.joda.time.DateTime;

import com.akoolla.cinema.seatbooking.core.film.IFilm;

/**
 * Represents the screening/showing of a film including bookings made against it.
 *
 * @author rich
 * @version $Id$
 */
public interface IScreening {
    
    String get_id();

    DateTime getScreeningTime();

    IBooking createBooking(IBookingRequest bookingRequest) throws ScreeningIsFullyBookedException;

    List<IBooking> listBookings();

    int getNumberOfBookedSeats();

    IFilm getFilm();

    /**
     * TODO.
     *
     * @return
     */
    int getNumberOfBookableSeats();

    /**
     * TODO.
     *
     * @param booking
     */
    void cancelBooking(IBooking booking);
    
    int getCostOfBookingInPence(IBooking booking);
}
