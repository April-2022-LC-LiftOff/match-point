package org.lauchcode.matchpoint.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Event {

    int id;
    String firstName;
    String lastName;

    @NotNull
    @Size(min = 1, max = 20, message = "Event name must be between 1 and 20 characters long")
    public String eventName;

    @NotNull
    @Size(min = 1, max = 100, message = "Location must be between 1 and 100 characters long")
    public String location;

    @NotNull
    @DateTimeFormat
    public java.time.LocalDateTime eventDate;

    @NotNull
    @DateTimeFormat
    public java.time.LocalDateTime eventTime;

    public Event(int id, String firstName, String lastName, String eventName, String location, LocalDateTime eventTime) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eventName = eventName;
        this.location = location;
        this.eventTime = eventTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }
}
