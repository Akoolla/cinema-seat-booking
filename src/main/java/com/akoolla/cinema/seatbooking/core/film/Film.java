package com.akoolla.cinema.seatbooking.core.film;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    public static final String REF_JSON_PROP = "ref";
    public static final String NAME_JSON_PROP = "name";
    public static final String DESC_JSON_PROP = "description";
    public static final String RATING_JSON_PROP = "rating";
    
    @JsonProperty(REF_JSON_PROP)
    private String ref;
    @JsonProperty(NAME_JSON_PROP)
    private String name;
    @JsonProperty(DESC_JSON_PROP)
    private String description;
    @JsonProperty(RATING_JSON_PROP)
    private String rating;

    private Film() {
    }

    @JsonCreator
    public Film(
            @JsonProperty(REF_JSON_PROP)final String ref, 
            @JsonProperty(NAME_JSON_PROP)final String name, 
            @JsonProperty(DESC_JSON_PROP)final String description, 
            @JsonProperty(RATING_JSON_PROP) final String rating) {
        this();
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
