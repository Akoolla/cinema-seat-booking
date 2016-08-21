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

    int getNumberOfBookedSeats(SEAT_TYPE ofSeats);

    IFilm getFilm();

    /**
     * TODO.
     *
     * @return
     */
    int getNumberOfBookableSeats(SEAT_TYPE ofSeats);

    /**
     * TODO.
     *
     * @param booking
     */
    void cancelBooking(IBooking booking);
    
    int getCostOfBookingInPence(IBooking booking);
    
    public enum SEAT_TYPE {
        STANDARD, WHEELCHAIR;
    }
    
    int getConncessionCost();
    void setConcessionCost(int i);

   
    void setStandardCost(int i);
    int getStandardCost();

    void setWheelChairCost(int i);
    int getWheelChairCost();

    void setMemberPrice(int i);
    int getMemberPrice();

    void setMemberConcession(int i);
    int getMemberConcession();

    void setMemberWheelChairPrice(int i);
    int getMemberWheelChairPrice();
}

