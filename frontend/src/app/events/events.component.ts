import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { EventsService } from "./events.service";
import { Event } from "./event";
import { TokenService } from "../_services/token.service";

@Component({
  selector: "app-events",
  templateUrl: "./events.component.html",
  styleUrls: ["./events.component.css"],
})
export class EventsComponent implements OnInit {
  event: Event = {
    id: 0,
    eventName: "",
    eventLocation: "",
    eventDate: "",
  };
  isLoggedIn: boolean = false;
  events: Event[] = [];

  constructor(private eventsService: EventsService, 
              private route: Router,
              private tokenService: TokenService) {}

  ngOnInit(): void {
    this.isLoggedIn = this.tokenService.isLoggedIn();
    const eventsObservable = this.eventsService.getAllEvents();
    eventsObservable.subscribe((eventData: Event[]) => {
      this.events = eventData;
      // console.log(this.events);
    });

    const Observable = this.eventsService.getAllEvents();
    eventsObservable.subscribe((eventData: Event[]) => {
      this.events = eventData;
      // console.log(this.events);
    });
  }

  onClickSubmit(): void {
    this.eventsService.createEvent(this.event).subscribe((savedEvent) => {
      console.log(`reminder saved: ${savedEvent}`);
      this.events.push(savedEvent);
      this.route.navigate(["/events"]);
    });
    this.event = {
      id: 0,
      eventName: "",
      eventLocation: "",
      eventDate: "",
    };
  }
}
