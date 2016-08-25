package com.akoolla.cinema.seatbooking.core.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.mongojack.ObjectId;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.akoolla.cinema.seatbooking.core.IBooking;
import com.akoolla.cinema.seatbooking.core.IBookingRequest;
import com.akoolla.cinema.seatbooking.core.IScreening;
import com.akoolla.cinema.seatbooking.core.ScreeningIsFullyBookedException;
import com.akoolla.cinema.seatbooking.core.film.Film;
import com.akoolla.cinema.seatbooking.core.film.IFilm;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.EqualsAndHashCode;

/**
 * Screening.
 * @author richardt
 */
@AutoProperty
@EqualsAndHashCode
public class Screening implements IScreening, Comparable<IScreening> {

    public static final String FILM_JSON_PROP = "flim";
    public static final String BOOKINGS_JSON_PROP = "bookings";
    public static final String DATE_JSON_PROP = "screeningTime";
    public static final String MAX_SEATS_PROP = "maxSeats";
    public static final String MAX_WHEELCHARS_PROP = "maxWheelChairs";

    @ObjectId
    public String _id;

    @JsonProperty(DATE_JSON_PROP)
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
    private DateTime screeningTime;

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
    @JsonProperty(BOOKINGS_JSON_PROP)
    private final List<IBooking> bookings;

    @JsonProperty(FILM_JSON_PROP)
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
    private Film film;

    @JsonProperty(MAX_SEATS_PROP)
    private int maxSeats = 0;
    @JsonProperty(MAX_WHEELCHARS_PROP)
    private int maxWheelchairs = 0;

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
    
    @JsonCreator
    public Screening(
            @JsonProperty(DATE_JSON_PROP) DateTime screeningTime,
            @JsonProperty(FILM_JSON_PROP)IFilm film, 
            @JsonProperty(MAX_SEATS_PROP) int maxSeats, 
            @JsonProperty(MAX_WHEELCHARS_PROP) int maxWheelchairs
            ){
        this();
        this.screeningTime = screeningTime;
        this.maxSeats = maxSeats;
        this.maxWheelchairs = maxWheelchairs;
    }
    
    /**
     * @param bookingRequest
     * @return
     * @see com.akoolla.cinemabooking.IScreening#createBooking(com.akoolla.cinemabooking.IBookingRequest)
     */
    public IBooking createBooking(IBookingRequest bookingRequest) throws ScreeningIsFullyBookedException {

        // TODO: More validation on seats being booked for wheelchairs..
        if (((bookingRequest.getNumberOfSeats() + getNumberOfBookedSeats(SEAT_TYPE.STANDARD)) > getNumberOfBookableSeats(SEAT_TYPE.STANDARD))
                & (bookingRequest.overrideSeatLimits() == false)) {
            throw new ScreeningIsFullyBookedException();
        }

        Booking booking = new Booking(bookingRequest.getNumberOfSeats(),
                bookingRequest.getNumberOfWheelChairs(),
                new Customer(bookingRequest.getCustomerName(), bookingRequest.getContactNumber()));
        bookings.add(booking);

        return booking;
    }

    /**
     * @return
     * @see com.akoolla.cinemabooking.IScreening#listBookings()
     */
    @JsonDeserialize(keyAs = Booking.class)
    public List<IBooking> listBookings() {
        return bookings;
    }

    /**
     * @param standard
     * @return
     * @see com.akoolla.cinemabooking.IScreening#getNumberOfBookedSeats()
     */
    @JsonIgnore
    public int getNumberOfBookedSeats(SEAT_TYPE standard) {
        int seats = 0;

        for (IBooking booking : bookings) {
            switch (standard) {
                case STANDARD:
                    seats += booking.getNumberOfSeats();
                    break;
                case WHEELCHAIR:
                    seats += booking.getNumberOfWheelChairs();
                    break;
                default:
                    break;
            }
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

    /**
     * @param booking
     * @return
     * @see com.akoolla.cinema.seatbooking.core.IScreening#getCostOfBookingInPence(com.akoolla.cinema.seatbooking.core.IBooking)
     */
    @Override
    public int getCostOfBookingInPence(IBooking booking) {
        return 0;
    }

    /**
     * @param ofSeats
     * @return
     * @see com.akoolla.cinema.seatbooking.core.IScreening#getNumberOfBookableSeats(com.akoolla.cinema.seatbooking.core.IScreening.SEAT_TYPE)
     */
    @Override
    public int getNumberOfBookableSeats(SEAT_TYPE ofSeats) {
        switch (ofSeats) {
            case STANDARD:
                return maxSeats;
            case WHEELCHAIR:
                return maxWheelchairs;
            default:
                return maxSeats;
        }
    }
}
