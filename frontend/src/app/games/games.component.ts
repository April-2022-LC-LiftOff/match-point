import { Component, OnInit } from '@angular/core';
import data from '../games.json';
import { Game } from "../game";
import { TokenService } from '../_services/token.service';
import { GameService } from '../_services/game.service';
const URL= "http://localhost:4200"

const parsedGames: Game[] = data.map(valueInJson => ({
  externalGameId: valueInJson.id,
  gameName: valueInJson.name,
  gameImage: valueInJson.images.small,
  description: valueInJson.description_preview,
  minPlayers: valueInJson.min_players,
  maxPlayers: valueInJson.max_players,
  minPlaytime: valueInJson.min_playtime,
  maxPlaytime: valueInJson.max_playtime
} as Game));

console.log(parsedGames);

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class GamesComponent implements OnInit{
  title: String = "View Our Game Database!"
  model: Game = new Game();
  userInSession = this.tokenService.getUser();
  isInLibrary: boolean = false;
  isLoggedIn?: boolean;

  // selectedGame? : Game;
  usersGames: Game[] = [];

  constructor(
    private gameService: GameService, private tokenService: TokenService
  ){}
  
  Games: Game[] = parsedGames;

  ngOnInit()  {
    this.isLoggedIn = this.tokenService.isLoggedIn();
    this.gameService.loadUserGames().subscribe(resp => {
      this.usersGames = resp;
    });
  }

  isSaved(game: Game) { 
    for(let usersGame of this.usersGames) {
      if(usersGame.externalGameId === game.externalGameId) {
        return true;
      }
    }
    return false;    
  }

  addToLibrary(game) {
    this.gameService.addToLibrary(game).subscribe(resp => {
      console.log("Game added to library");
      this.usersGames.push(game);
    });
  }
}

