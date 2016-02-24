package com.akoolla.cinema.seatbooking.core.impl;

import java.util.UUID;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.akoolla.cinema.seatbooking.core.IBookingReference;

import lombok.EqualsAndHashCode;

/**
 * BookingReference.
 *
 * @author rich
 * @version $Id$
 */
@AutoProperty
@EqualsAndHashCode
public class BookingReference implements IBookingReference {
    private final UUID reference = UUID.randomUUID();

    /**
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return Pojomatic.toString(this);
    }
}
