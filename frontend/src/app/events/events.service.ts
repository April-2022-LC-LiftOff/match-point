import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Event } from "./event";

const API_URL = "http://localhost:8080/api/events/";
const BASE_URL = "http://localhost:8080/api";

@Injectable({
  providedIn: "root",
})
export class EventsService {
  constructor(private http: HttpClient) {}

  getAllEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(BASE_URL + "/events");
  }

  createEvent(event: Event): Observable<Event> {
    return this.http.post<Event>(BASE_URL + "/event", event);
  }
}
