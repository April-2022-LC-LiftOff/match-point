import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  private apiUrl = 'http://localhost:8080/'
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) {}

  getUser(){
    return this.http.get(this.apiUrl+'user');
  }

  addUser(user: User){
    return this.http.post<User>(this.apiUrl+'register', user, this.httpOptions)
  }
}
