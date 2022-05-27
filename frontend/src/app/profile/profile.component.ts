import { Component, OnInit } from '@angular/core';
import { TokenService } from '../_services/token.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  title: string = "Profile"
  userInSession?;

  constructor(private tokenService: TokenService) { }

  ngOnInit() {
    this.userInSession = this.tokenService.getUser();
    console.log(this.userInSession);
  }

  logout(): void {
    this.tokenService.logout();
    window.location.reload();
  }

}
