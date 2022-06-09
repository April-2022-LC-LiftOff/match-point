package org.lauchcode.matchpoint.controllers;

import org.lauchcode.matchpoint.models.Event;
import org.lauchcode.matchpoint.models.data.EventRepository;
import org.lauchcode.matchpoint.models.dto.EventFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AuthenticationController authenticationController;


    @PostMapping("/event")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<Object> processCreateEventForm(@RequestBody EventFormDTO eventFormDTO, HttpServletRequest request) {

        Event newEvent = new Event(eventFormDTO.getEventName(), eventFormDTO.getEventLocation(), eventFormDTO.getEventDate());
        Event savedEvent= eventRepository.save(newEvent);


        return ResponseEntity.ok(savedEvent);
    }


    //I changed this from /events to /event? Not sure if that matters
    @GetMapping("/events")
    @ResponseBody
    @CrossOrigin
    public ResponseEntity<Object> displayAllEvents( HttpServletRequest request) {
        List<Event> events =  eventRepository.findAll();
        return ResponseEntity.ok(events);
    }


    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {

        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }

        return "redirect:";
    }

}

