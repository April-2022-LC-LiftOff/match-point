package org.lauchcode.matchpoint.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 1, max = 50, message = "Event name must be between 1 and 50 characters long")
    private String eventName;

    @NotNull
    @Size(min = 1, max = 100, message = "Location must be between 1 and 100 characters long")
    private String eventLocation;

    @NotNull
    @DateTimeFormat
    private java.time.LocalDateTime eventDate;


    public Event(int id, String eventName, String eventLocation, LocalDateTime eventDate) {
        this.id = id;
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
    }

    public Event() {}

    public int getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getLocation() {
        return eventLocation;
    }

    public void setLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
