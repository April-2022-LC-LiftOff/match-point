import { Component, OnInit } from '@angular/core';
import data from '../games.json';
import { Game } from "../game";
import { TokenService } from '../_services/token.service';
import { GameService } from '../_services/game.service';
const URL= "http://localhost:4200"

//PARSING THE JSON TO CREATE JSON WITH ONLY NEEDED KEY-VALUEPAIRS
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
  libraryAction: string = "Add to Library"

  // selectedGame? : Game;
  userGames: Game[] = [];

  constructor(
    private gameService: GameService, private tokenService: TokenService
  ){}
  
  Games: Game[] = parsedGames;

  ngOnInit()  {
    this.isLoggedIn = this.tokenService.isLoggedIn();
    this.gameService.loadUserGames().subscribe(resp => {
      this.userGames = resp;
    });
  }

  isSaved(game: Game) { 
    for(let usersGame of this.userGames) {
      if(usersGame.externalGameId === game.externalGameId) {
        return true;
      }
    }
    return false;    
  }

// toggleEvent(game) {
//    if(!this.isSaved(game)) {
//        this.addToLibrary(game);
//        this.libraryAction = "Delete from Library";
//        this.isInLibrary = true;
//    } else {
//       this.deleteFromLibrary(game);
//       this.libraryAction = "Add to Library";
//       this.isInLibrary = false;
//    }
// }

  addToLibrary(game) {
    this.gameService.addToLibrary(game).subscribe(resp => {
      console.log("Game added to library");
      this.userGames.push(game);
      console.log(this.userGames);
    });
  }

  deleteFromLibrary(game){
    this.gameService.deleteFromLibrary(game).subscribe(resp => {     
      this.userGames = this.userGames.filter((userGame) => userGame.gameName !== game.gameName);
      console.log(this.userGames);
      console.log("Game removed from library");
    });
  }
}

