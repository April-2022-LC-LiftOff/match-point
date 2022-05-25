import { Component, OnInit } from '@angular/core';
import data from '../games.json';

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class GamesComponent {
  Games: any = data;
  Title= "View Our Game Database!"
  constructor() { }

  // ngOnInit() {

  //     console.log(data);
  
    
  // }

}
