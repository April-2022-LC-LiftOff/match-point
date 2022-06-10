import { Component, OnInit } from '@angular/core';
import data from '../games.json';
import { Game } from "../game";
import { TokenService } from '../_services/token.service';
import { GameService } from '../_services/game.service';
const URL= "http://localhost:4200"

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
  // games: Game[] = [];

  constructor(
    private gameService: GameService, private tokenService: TokenService
  ){}
  
  games: Game[] = [];

  ngOnInit()  {
    this.isLoggedIn = this.tokenService.isLoggedIn();
    this.isInLibrary = this.gameService.isInLibrary();

  }

  // addToLibrary() {
  //   this.gameService.addToLibrary(this.userInSession, ).subscribe(savedGame) =>{
  //     console.log("Game added to library");
  //     this.games.push(savedGame);

  //   }
  //}
}

