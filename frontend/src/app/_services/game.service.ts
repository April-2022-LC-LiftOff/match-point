import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import internal from "assert";
import { Observable } from "rxjs";
import { Game } from "../game";
import { User } from "../user";

const API_URL = "http://localhost:8080/api/games/";
const httpOptions = {
  headers: new HttpHeaders({
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "http://localhost:4200",
  }),
};

@Injectable({
  providedIn: "root",
})

export class GameService {
    constructor(private http: HttpClient) {
      // this.http.get('api').subscribe(
      //   data => this.library = data;
      // )
    }

    addToLibrary( user: User, game: Game): Observable<any> {
        return this.http.post(
            API_URL + "games",
            {
              user,
              game,
            },  
            httpOptions
          );
        }
    
    //TODO: check if in library
    public isInLibrary(): any {
      const library = '';
      let game = {} as any;
      if(library.includes(game.externalGameId)) {
          return true;
      } else {
        return false;
      }
    }    


    }