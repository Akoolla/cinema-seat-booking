package com.akoolla.cinema.seatbooking.core;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;

import com.akoolla.cinema.seatbooking.core.film.Film;
import com.akoolla.cinema.seatbooking.core.impl.Screening;

/**
 * BookingCostTest.
 *
 * @author richardTiffin
 */
public class BookingCostTest {
    
/*    Member: £3.50

    Member Concession, and member wheelchair: £3

    Non-member: £6

    Non-member concession, and non-member wheelchair: £4

    Wheelchair: £3
*/
    @Test
    public void canGetCostOfDifferentSeatTypesInScreenign() throws Exception {
        IScreening screening = new Screening(
                DateTime.now(), new Film("x", "x", "x", "15"), 20, 1);
        
        
        screening.setConcessionCost(400);
        assertEquals("Cost of consesions", 400, screening.getConncessionCost());
        
        screening.setStandardCost(600);
        assertEquals("Cost of standard", 600, screening.getStandardCost());
        
        screening.setWheelChairCost(400);
        assertEquals("Cost of wheelchair", 400, screening.getWheelChairCost());
        
        screening.setMemberPrice(350);
        assertEquals("Price for a member", 350, screening.getMemberPrice());
        
        screening.setMemberConcession(300);
        assertEquals("Price for a member concession", 300, screening.getMemberConcession());
        
        screening.setMemberWheelChairPrice(300);
        assertEquals("Price for a member wheelchair", 300, screening.getMemberWheelChairPrice());
        
    }
}
