import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

const API_URL = "http://localhost:8080/api/user/";


@Injectable({
  providedIn: "root",
})
export class UserService {
  constructor(private http: HttpClient) {}

  deleteUser(id: number): Observable<any> {
    return this.http.delete(API_URL + id, { responseType: "text" });
  }

  editUser(id: number, username: string, email: string, password: string): Observable<any> {
    return this.http.put(
      API_URL + id,
      {
        username,
        email,
        password,
      },
      { responseType: "text" }
    );
  }
}
