package org.lauchcode.matchpoint.models.dto;

import org.lauchcode.matchpoint.models.Game;
import org.lauchcode.matchpoint.models.User;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class EventFormDTO {

    private String eventName;

    private String eventLocation;

    private String eventDate;

    private boolean eventCreator;

    private List<User> user;

    private List<Game> games;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public boolean isEventCreator() {
        return eventCreator;
    }

    public void setEventCreator(boolean eventCreator) {
        this.eventCreator = eventCreator;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
