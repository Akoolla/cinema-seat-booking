package com.akoolla.cinema.seatbooking.core.film;

import org.joda.time.DateTime;

/**
 * IFilm.
 *
 * @author rich
 */

public interface IFilm {
    public String getUniqueReference();

    public String getName();

    public String getDescription();
    
    public String getRating();
    
    public DateTime getReleaseDate();
    
    public int getLengthInMins();
    
    public String getCountry();
}
