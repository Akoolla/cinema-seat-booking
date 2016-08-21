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
    private int maxSeats = 0;
    private int maxWheelchairs = 0;
    
    //Costs
    private int concessionCost = 0;
    private int StandardCost = 0;
    private int wheelChairCost = 0;
    
    //Member costs
    private int memberPrice = 0;
    private int memberConceesions = 0;
    private int memberWheelChair = 0;
    

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

    public Screening(DateTime screeningTime, int seats, int wheelchair) {
        this();
        this.screeningTime = screeningTime;
        this.maxSeats = seats;
        this.maxWheelchairs = wheelchair;
    }

    public Screening(DateTime screeningTime, IFilm film, int numOfSeats, int numOfWheelChairs) {
        this(screeningTime, numOfSeats, numOfWheelChairs);

        // TODO: Hack to fix jackson deserialisation issue - should really look at jackson rather than do this
        this.film = new Film(film.getUniqueReference(), film.getName(), film.getDescription(), film.getRating());
    }

    /**
     * @param bookingRequest
     * @return
     * @see com.akoolla.cinemabooking.IScreening#createBooking(com.akoolla.cinemabooking.IBookingRequest)
     */
    public IBooking createBooking(IBookingRequest bookingRequest) throws ScreeningIsFullyBookedException {

        
        //TODO: More validation on seats being booked for wheelchairs..
        if (((bookingRequest.getNumberOfStandardSeats() + bookingRequest.getNumberOfConcessionSeats() + getNumberOfBookedSeats(SEAT_TYPE.STANDARD)) > getNumberOfBookableSeats(SEAT_TYPE.STANDARD))
                & (bookingRequest.overrideSeatLimits() == false)) {
            throw new ScreeningIsFullyBookedException();
        }
                

        Booking booking = new Booking(bookingRequest.getNumberOfStandardSeats(), 
                bookingRequest.getNumberOfConcessionSeats(), 
                bookingRequest.getNumberOfWheelChairs(), 
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
     * @param standard 
     * @return
     * @see com.akoolla.cinemabooking.IScreening#getNumberOfBookedSeats()
     */
    @JsonIgnore
    public int getNumberOfBookedSeats(SEAT_TYPE standard) {
        int seats = 0;

        for (IBooking booking : bookings) {
            seats += booking.getNumberOfConcessionSeats() + booking.getNumberOfStandardSeats();
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

    /**
     * @return
     * @see com.akoolla.cinema.seatbooking.core.IScreening#getConncessionCost()
     */
    @Override
    public int getConncessionCost() {
        return concessionCost;
    }

    /**
     * @param i
     * @see com.akoolla.cinema.seatbooking.core.IScreening#setConcessionCost(int)
     */
    @Override
    public void setConcessionCost(int concessionCost) {
        this.concessionCost = concessionCost;
    }

    /**
     * @param i
     * @see com.akoolla.cinema.seatbooking.core.IScreening#setStandardCost(int)
     */
    @Override
    public void setStandardCost(int StandardCost) {
        this.StandardCost = StandardCost;
    }

    /**
     * @return
     * @see com.akoolla.cinema.seatbooking.core.IScreening#getStandardCost()
     */
    @Override
    public int getStandardCost() {
        return StandardCost;
    }

    /**
     * @param wheelChairCost
     * @see com.akoolla.cinema.seatbooking.core.IScreening#setWheelChairCost(int)
     */
    @Override
    public void setWheelChairCost(int wheelChairCost) {
        this.wheelChairCost = wheelChairCost;
    }

    /**
     * @return
     * @see com.akoolla.cinema.seatbooking.core.IScreening#getWheelChairCost()
     */
    @Override
    public int getWheelChairCost() {
        return wheelChairCost;
    }

    /**
     * @param i
     * @see com.akoolla.cinema.seatbooking.core.IScreening#setMemberPrice(int)
     */
    @Override
    public void setMemberPrice(int memberPrice) {
        this.memberPrice = memberPrice;
    }

    /**
     * @return
     * @see com.akoolla.cinema.seatbooking.core.IScreening#getMemberPrice()
     */
    @Override
    public int getMemberPrice() {
        return memberPrice;
    }

    /**
     * @param memberConceesions
     * @see com.akoolla.cinema.seatbooking.core.IScreening#setMemberConcession(int)
     */
    @Override
    public void setMemberConcession(int memberConceesions) {
        this.memberConceesions = memberConceesions;
    }

    /**
     * @return
     * @see com.akoolla.cinema.seatbooking.core.IScreening#getMemberConcession()
     */
    @Override
    public int getMemberConcession() {
        return memberConceesions;
    }

    /**
     * @param memberWheelChair
     * @see com.akoolla.cinema.seatbooking.core.IScreening#setMemberWheelChairPrice(int)
     */
    @Override
    public void setMemberWheelChairPrice(int memberWheelChair) {
        this.memberWheelChair = memberWheelChair;
    }

    /**
     * @return
     * @see com.akoolla.cinema.seatbooking.core.IScreening#getMemberWheelChairPrice()
     */
    @Override
    public int getMemberWheelChairPrice() {
        return memberWheelChair;
    }
}
