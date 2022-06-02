import { Injectable } from "@angular/core";
import { Games } from './games';
import { GAMES } from './games.json';

@Injectable({
    providedIn: 'root',
})

getGame(): Game[] {
    return Games;
  }

  constructor(private gameService: GameService) {}

import { Injectable } from '@angular/core';

import { Hero } from './hero';
import { HEROES } from './mock-heroes';

@Injectable({
  providedIn: 'root',
})
export class GameService {



  getGames(): Observable<Game[]> {
    const games = of(GAMES);
    return games;
  }
}
