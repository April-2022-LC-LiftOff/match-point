import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
const API_URL = "http://localhost:8080/api/user/";

@Injectable({
  providedIn: "root",
})
export class UserService {
  constructor(private http: HttpClient) {}

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + "public", { responseType: "text" });
  }

  getPrivateContent(): Observable<any> {
    return this.http.get(API_URL + "private", { responseType: "text" });
  }
}
