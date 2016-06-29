package com.akoolla.cinema.seatbooking.core.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.akoolla.cinema.seatbooking.core.IBooking;
import com.akoolla.cinema.seatbooking.core.IBookingRequest;
import com.akoolla.cinema.seatbooking.core.IScreening;
import com.akoolla.cinema.seatbooking.core.ScreeningIsFullyBookedException;
import com.akoolla.cinema.seatbooking.core.film.Film;
import com.akoolla.cinema.seatbooking.core.film.IFilm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.EqualsAndHashCode;

/**
 * Screening.
 *
 * @author rich
 * @version $Id$
 */
@AutoProperty
@EqualsAndHashCode
public class Screening implements IScreening, Comparable<IScreening> {
    
    public String _id;

    private DateTime screeningTime;
    
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
    @JsonProperty()
    private final List<IBooking> bookings;

    private Film film;
    private int numberOfBookableSeats = 0;

    /**
     * Return the film.
     *
     * @return the film
     */
    public IFilm getFilm() {
        return film;
    }

    public Screening() {
        bookings = new ArrayList<>();
    }

    public Screening(DateTime screeningTime, int numberOfBookableSeats) {
        this();
        this.screeningTime = screeningTime;
        this.numberOfBookableSeats = numberOfBookableSeats;
    }

    public Screening(DateTime screeningTime, IFilm film, int numberOfBookableSeats) {
        this(screeningTime, numberOfBookableSeats);

        // TODO: Hack to fix jackson deserialisation issue - should really look at jackson rather than do this
        this.film = new Film(film.getUniqueReference(), film.getName(), film.getDescription());
    }

    /**
     * @param bookingRequest
     * @return
     * @see com.akoolla.cinemabooking.IScreening#createBooking(com.akoolla.cinemabooking.IBookingRequest)
     */
    public IBooking createBooking(IBookingRequest bookingRequest) throws ScreeningIsFullyBookedException {

        if (((bookingRequest.getNumberOfSeats() + getNumberOfBookedSeats()) > numberOfBookableSeats)
                & (bookingRequest.overrideSeatLimits() == false)) {
            throw new ScreeningIsFullyBookedException();
        }

        Booking booking = new Booking(
                bookingRequest.getNumberOfSeats(),
                new Customer(bookingRequest.getCustomerName(), bookingRequest.getContactNumber()));
        bookings.add(booking);

        return booking;
    }

    /**
     * @return
     * @see com.akoolla.cinemabooking.IScreening#listBookings()
     */
     @JsonDeserialize(keyAs=Booking.class)
    public List<IBooking> listBookings() {
        return bookings;
    }

    /**
     * @return
     * @see com.akoolla.cinemabooking.IScreening#getNumberOfBookedSeats()
     */
    @JsonIgnore
    public int getNumberOfBookedSeats() {
        int seats = 0;

        for (IBooking booking : bookings) {
            seats += booking.getNumberOfSeats();
        }

        return seats;
    }

    /**
     * @return
     * @see com.akoolla.cinemabooking.IScreening#getScreeningTime()
     */
    public DateTime getScreeningTime() {
        return screeningTime;
    }

    /**
     * @return
     * @see com.akoolla.cinemabooking.IScreening#getNumberOfSeats()
     */
    public int getNumberOfBookableSeats() {
        return numberOfBookableSeats;
    }

    /**
     * @param booking
     * @see com.akoolla.cinemabooking.IScreening#cancelBooking(com.akoolla.cinemabooking.IBooking)
     */
    public void cancelBooking(IBooking booking) {
        bookings.remove(booking);
    }

    /**
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return Pojomatic.toString(this);
    }

    /**
     * @param arg0
     * @return
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(IScreening arg0) {
        if (arg0.getScreeningTime().isEqual(screeningTime)) {
            return -1;
        } else if (arg0.getScreeningTime().isBefore(screeningTime)) {
            return 1;
        } else {
            return -1;
        }
    }
    
    @Override
    public String get_id() {
        return _id;
    }
}
