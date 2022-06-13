import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import internal from "assert";
import { Observable } from "rxjs";
import { Game } from "../game";
import { User } from "../user";
import { TokenService } from "./token.service";

const API_URL = "http://localhost:8080/api/games/";
const baseHeaders = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "http://localhost:4200",
}

@Injectable({
  providedIn: "root",
})

export class GameService {
    constructor(private http: HttpClient, private token: TokenService) {
      // this.http.get('api').subscribe(
      //   data => this.library = data;
      // )
    }

    loadUserGames() : Observable<Game[]> {
      return this.http.get<Game[]>(
        API_URL + "games",
        {
          headers:
          {
            ...baseHeaders,
            "Authorization": `Bearer ${this.token.getToken()}`
          }
        }
      );
    }

    addToLibrary(game: Game): Observable<any> {
        return this.http.post(
            API_URL + "games",
              game, 
            {
              headers:
              {
                ...baseHeaders,
                "Authorization": `Bearer ${this.token.getToken()}`
              }
            }
          );
        }

        deleteFromLibrary(game: Game): Observable<any> {
          return this.http.post(
              API_URL + "games/delete",
                game, 
              {
                headers:
                {
                  ...baseHeaders,
                  "Authorization": `Bearer ${this.token.getToken()}`
                }
              }
            );
          }
    }