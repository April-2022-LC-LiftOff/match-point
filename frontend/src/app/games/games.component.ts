import { Component, OnInit } from '@angular/core';
import data from '../games.json';

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class GamesComponent {
  Games: any = data;
  title= "View Our Game Database!"
  // min_Players: number;
  // max_Players: number;
  // min_Playtime: number;
  // max_Playtime: number;
  
  constructor() { }

}
