package com.akoolla.cinema.seatbooking.core.film;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import lombok.EqualsAndHashCode;

/**
 * Film.
 *
 * @author richardTiffin
 * @version $Id$
 */
@AutoProperty
@EqualsAndHashCode
public class Film implements IFilm {
    private String ref;
    private String name;
    private String description;
    private String rating;

    public Film() {
    }

    public Film(final String ref, final String name, final String description, final String rating) {
        this.ref = ref;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    /**
     * @return
     * @see com.akoolla.cinema.seatbooking.core.film.IFilm#getUniqueReference()
     */
    @Override
    public String getUniqueReference() {
        return ref;
    }

    /**
     * @return
     * @see com.akoolla.cinema.seatbooking.core.film.IFilm#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return
     * @see com.akoolla.cinema.seatbooking.core.film.IFilm#getDescription()
     */
    @Override
    public String getDescription() {
        return description;
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
     * @return
     * @see com.akoolla.cinema.seatbooking.core.film.IFilm#rating()
     */
    @Override
    public String getRating() {
        return rating;
    }
}
