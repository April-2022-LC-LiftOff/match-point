package org.lauchcode.matchpoint.controllers;

import org.lauchcode.matchpoint.models.Event;
import org.lauchcode.matchpoint.models.data.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent,
                                         Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }

        eventRepository.save(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
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

//@Controller
//public class EventController {
//    @Autowired
//    EventRepository eventRepository;
//
//    @RequestMapping("/createAnEvent")
//    public class CreateAnEventController {
//
//        @GetMapping("/createAnEvent")
//        @ResponseBody
//        public String displayCreateAnEventForm() {
//            return "<html>" +
//                    "<body>" +
//                    "<h1>Create an Event</h1>" +
//                    "<form action='/createAnEvent' method='post'>" +
//                    "<label> Event Name <input type='text' name='eventName' > </label>" +
//                    "<label> Username <input type='text' name='username' > </label>" +
//                    "<label> Location <input type='password' name='location' > </label>" +
//                    "<label> Time <input type='password' name='time' > </label>" +
//                    "<label> Date <input type='password' name='date' > </label>" +
//                    "<input type='submit' value='CreateAnEvent' >" +
//                    "</form>" +
//                    "</body>" +
//                    "</html>";
//        }
//    }
//}