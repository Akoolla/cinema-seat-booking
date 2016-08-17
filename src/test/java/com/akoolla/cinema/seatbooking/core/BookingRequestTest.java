package com.akoolla.cinema.seatbooking.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.akoolla.cinema.seatbooking.core.impl.BookingRequest;

/**
 * BookingRequestTests.
 *
 * @author rich
 */
public class BookingRequestTest {

    @Test
    public void BookingShouldReturnTotalNumberOfSeatsBooked() {
        IBookingRequest bookingRequest = new BookingRequest("", "", 2, 2, 0);
        assertEquals(Integer.valueOf(4), Integer.valueOf(bookingRequest.getNumberOfSeats()));
    }

    @Test
    public void BookingRequestShouldReturnCustomerNameAndContactNumber() throws Exception {
        IBookingRequest bookingRequest = new BookingRequest("Fred", "0898 50 50 50", 4, 0 , 0);
        assertEquals("CustomerName", "Fred", bookingRequest.getCustomerName());
        assertEquals("ContactNo", "0898 50 50 50", bookingRequest.getContactNumber());
    }
}
