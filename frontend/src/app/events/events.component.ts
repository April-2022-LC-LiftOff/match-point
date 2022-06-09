import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EventsService } from './events.service';
import { Event } from './event';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {
  event: Event = {
    id: 0,
    eventName: "",
    eventLocation: "",
    eventDate: ""
  };

  events: Event[] = [];
  
  
  constructor(
    private eventsService: EventsService,
    private route: Router
  ) { }

  ngOnInit() {
    const eventsObservable= this.eventsService.getAllEvents();
          eventsObservable.subscribe((eventData:Event[]) => {
              this.events=eventData;
     });
  }







  onClickSubmit(): void {
    this.eventsService.createEvent(this.event).subscribe(
      (savedEvent) => {
        console.log(`reminder saved: ${savedEvent}`);
        this.events.push(savedEvent);
        this.route.navigate(["/events"]);
      },
    );
    this.event= {
      id: 0,
      eventName: "",
      eventLocation: "",
      eventDate: ""
    };
  }

}
