import { Component, OnInit } from '@angular/core';
import { TokenService } from '../_services/token.service';
const URL = "http://localhost:4200/"

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  title: string = "Profile"
  isLoggedIn?: boolean;
  userInSession?;

  constructor(private tokenService: TokenService) { }

  ngOnInit() {
    this.isLoggedIn = this.tokenService.isLoggedIn();
    if (!this.isLoggedIn){
      window.location.replace(URL + "login")
    } else {
      this.userInSession = this.tokenService.getUser();
    }
  }

  logout(): void {
    this.tokenService.logout();
    window.location.reload();
  }

}