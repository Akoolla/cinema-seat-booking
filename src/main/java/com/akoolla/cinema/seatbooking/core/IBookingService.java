package com.akoolla.cinema.seatbooking.core;

import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

import com.akoolla.cinema.seatbooking.core.film.IFilm;

/**
 * IBookingService.
 *
 * @author rich
 * @since 1.0
 */
public interface IBookingService {
    /**
     * Returns a list of all screenings from midnight on a given date.
     *
     * @return {@link List} of screenings
     * @since 1.0
     */
    Set<IScreening> findAllScreenings(final DateTime fromDate);

    /**
     * Attempts to return a single screening for the given date.
     *
     * @param screeningDate
     * @return
     * @throws SearchReturnedMoreThanOneScreeningException.
     * @since 1.0
     */
    IScreening findScreening(final DateTime screeningDate) throws SearchReturnedMoreThanOneScreeningException;

    IScreening findScreening(final String filmName, final DateTime screeningDate);

    IScreening findNextScreening(final IFilm film);
    
    IScreening findScreening(final String screeningRef);

    void cancelABooking(final IBooking bookingToCancel);

    List<IBooking> listBookings(final IScreening sceening);

    List<IBooking> listBookings(final DateTime screeningDate);

    List<IBooking> listBookings(final IFilm film, final DateTime screeningDate);

    /**
     * Creates a new screening, throws an exception if the screening has already been created, uses equals method on
     * {@link IScreening} implementation to determine this.
     *
     * @param screening
     * @throws IllegalArgumentException
     * @since 1.0
     */
    void createScreening(final IScreening screening) throws IllegalArgumentException;

    /**
     * TODO.
     * 
     * @param screeningId
     * @param bookingRequest
     * @return
     * @throws ScreeningIsFullyBookedException
     */
    IBooking updateScreening(String screeningId, IBookingRequest bookingRequest) throws ScreeningIsFullyBookedException;
}
