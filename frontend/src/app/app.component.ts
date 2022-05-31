import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserAuthService } from './user-auth.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'MatchPoint';
  userInSession?;
  user = this.userInSession || 'Stranger';

  constructor(private auth: UserAuthService){}
  
  ngOnInit(): void {
  }
  
}
