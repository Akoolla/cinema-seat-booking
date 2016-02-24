package com.akoolla.cinema.seatbooking.core;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.akoolla.cinema.seatbooking.core.film.IFilm;
import com.akoolla.cinema.seatbooking.core.impl.Screening;

/**
 * ScreeningInformationTests.
 *
 * @author rich
 */
public class ScreeningBookingTests extends Mockito {
    private IScreening screening;

    @Before
    public void initialiseTests() {
        screening = new Screening(new DateTime(), mock(IFilm.class), 9);
    }

    @Test
    public void ScreeningShouldReturnTheDateTimeOfTheScreening() throws Exception {
        DateTime now = new DateTime();

        screening = new Screening(now, mock(IFilm.class), 9);
        assertEquals(now, screening.getScreeningTime());
    }

    @Test
    public void YouShouldBeAbleToCreateABookingForAScreening() throws Exception {
        IBookingRequest bookingRequest = mock(IBookingRequest.class);
        when(bookingRequest.getNumberOfSeats()).thenReturn(1);
        screening.createBooking(bookingRequest);

        List<IBooking> bookings = screening.listBookings();
        assertNotNull("Booking list for a screening should never be null", bookings);
        assertTrue("There should be one booking for a screening", bookings.size() > 0);

        IBooking booking = bookings.get(0);
        assertNotNull(booking);
    }

    @Test
    public void AScreeningShouldReturnTheCorrectNumberOfBookedSeats() throws Exception {
        IBookingRequest bookingRequest = mock(IBookingRequest.class);
        when(bookingRequest.getNumberOfSeats()).thenReturn(2);
        screening.createBooking(bookingRequest);

        assertEquals("No. of booked seats",
                Integer.valueOf(2), Integer.valueOf(screening.getNumberOfBookedSeats()));
    }

    @Test
    public void ItShouldBePossibleToSetTheNumberOfSeatsAvailableForAScreening() {
        IScreening screening = new Screening(new DateTime(), mock(IFilm.class), 20);
        assertEquals(20, screening.getNumberOfBookableSeats());
    }

    @Test(expected = ScreeningIsFullyBookedException.class)
    public void IfABookingIsOverSubscribedAWarningShouldBeGiven() throws Exception {
        IBookingRequest bookingRequest = mock(IBookingRequest.class);
        when(bookingRequest.getNumberOfSeats()).thenReturn(10);

        screening.createBooking(bookingRequest);
    }

    @Test
    public void ItShouldBePossibleToBookMoreSeatsThanAreAvailable() {
        IBookingRequest bookingRequest = mock(IBookingRequest.class);
        when(bookingRequest.getNumberOfSeats()).thenReturn(2);
        when(bookingRequest.overrideSeatLimits()).thenReturn(true);

        try {
            screening.createBooking(bookingRequest);
        } catch (ScreeningIsFullyBookedException e) {
            fail("Could not book more seats than available");
        }
    }

    @Test
    public void CancelingABookingShouldDecreaseTheNumberOfSeatsBooked() throws Exception {
        IBookingRequest firstBookingRequest = mock(IBookingRequest.class);
        when(firstBookingRequest.getNumberOfSeats()).thenReturn(2);
        screening.createBooking(firstBookingRequest);

        IBookingRequest secondBookingRequest = mock(IBookingRequest.class);
        when(secondBookingRequest.getNumberOfSeats()).thenReturn(1);

        IBooking secondBooking = screening.createBooking(secondBookingRequest);
        assertEquals(3, screening.getNumberOfBookedSeats());

        screening.cancelBooking(secondBooking);
        assertEquals(firstBookingRequest.getNumberOfSeats(), screening.getNumberOfBookedSeats());
    }
    
    @Test
    public void CustomerShouldBeCreatedInBookingUsingDetailsFromRequest() throws Exception {
        IBookingRequest bookingRequest = mock(IBookingRequest.class);
        when(bookingRequest.getNumberOfSeats()).thenReturn(1);
        when(bookingRequest.getCustomerName()).thenReturn("Tom");
        when(bookingRequest.getContactNumber()).thenReturn("0898 50 50 50");
        
        screening.createBooking(bookingRequest);
        List<IBooking> bookings = screening.listBookings();
        
        IBooking booking = bookings.get(0);
        assertNotNull("Customer", booking.getCustomer());
        assertEquals("Customer Name","Tom", booking.getCustomer().getCustomerName());
        assertEquals("Customer Number", "0898 50 50 50", booking.getCustomer().getContactNumber());        
    }
}
