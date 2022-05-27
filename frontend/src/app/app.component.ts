import { Component, OnInit } from '@angular/core';
import { AuthService } from './_services/auth.service';
import { TokenService } from './_services/token.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title: string = 'MatchPoint';
  isLoggedIn: boolean = false;
  userInSession?;
  username: string = 'Stranger';

  constructor(private tokenService: TokenService, private authService: AuthService){}
  
  ngOnInit(): void {
    this.isLoggedIn = this.tokenService.isLoggedIn();
    if (this.isLoggedIn) {
      this.userInSession = this.tokenService.getUser();
      this.username = this.userInSession.username
    }
  }
  
}
