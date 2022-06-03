import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

const API_URL = "http://localhost:8080/api/auth/";
const httpOptions = {
  headers: new HttpHeaders({
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "http://localhost:4200",
  }),
};

@Injectable({
  providedIn: "root",
})
export class AuthService {
  constructor(private http: HttpClient) {}

  // TODO: rewrite to align with user model
  register(username: string, email: string, password: string): Observable<any> {
    return this.http.post(
      API_URL + "register",
      {
        username,
        email,
        password,
      },
      httpOptions
    );
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post(
      API_URL + "login",
      {
        username,
        password,
      },
      httpOptions
    );
  }
}
