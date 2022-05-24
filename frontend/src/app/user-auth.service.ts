import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { User } from "./user";

@Injectable({
  providedIn: "root",
})

export class UserAuthService {
  private apiUrl = "http://localhost:8080/";
  httpOptions = {
    headers: new HttpHeaders({
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "http://localhost:4200",
    }),
  };

  constructor(private http: HttpClient) {}

  addUser(user: User) {
    return this.http.post<User>(
      this.apiUrl + "register",
      user,
      this.httpOptions
    );
  }

}
