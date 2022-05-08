package org.lauchcode.matchpoint.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventController {

    LocalDate ld = LocalDate.now();

    @Controller
    @RequestMapping("/createAnEvent")
    public class CreateAnEventController {

        @GetMapping("/createAnEvent")
        @ResponseBody
        public String displayCreateAnEventForm() {
            return "<html>" +
                    "<body>" +
                    "<h1>Create an Event</h1>" +
                    "<form action='/createAnEvent' method='post'>" +
                    "<label> Event Name <input type='text' name='eventName' > </label>" +
                    "<label> Username <input type='text' name='username' > </label>" +
                    "<label> Location <input type='password' name='location' > </label>" +
                    "<label> Time <input type='password' name='time' > </label>" +
                    "<label> Date <input type='password' name='date' > </label>" +
                    "<input type='submit' value='CreateAnEvent' >" +
                    "</form>" +
                    "</body>" +
                    "</html>";
        }
    }
}
