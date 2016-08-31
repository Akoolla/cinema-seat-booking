package com.akoolla.cinema.seatbooking.core;

import java.util.List;
import java.util.Set;

/**
 * IBookingService.
 *
 * @author rich
 * @since 1.0
 */
public interface IBookingService {
    Set<IScreening> findAllScreenings();
    
    IScreening findScreening(final String screeningRef);

    /**
     * @param bookingRef
     * @throws IllegalArgumentException if booking not found
     */
    void cancelABooking(final String bookingRef)  throws IllegalArgumentException;

    List<IBooking> listBookings(final String screeningRef);

    /**
     * Creates a new screening, throws an exception if the screening has already been created, uses equals method on
     * {@link IScreening} implementation to determine this.
     *
     * @param screening
     * @throws IllegalArgumentException
     * @since 1.0
     */
    IScreening createScreening(final IScreening screening) throws IllegalArgumentException;

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
