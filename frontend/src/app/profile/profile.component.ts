import { Component, OnInit } from '@angular/core';
import { GameService } from '../_services/game.service';
import { TokenService } from '../_services/token.service';
import { UserService } from '../_services/user.service';
const URL = "http://localhost:4200/"

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  title: string = "Profile"
  isLoggedIn?: boolean;
  isEditing?: boolean = false;
  userInSession?;
  userGames?;

  constructor(private tokenService: TokenService, private userService: UserService,  private gameService: GameService) { }

  ngOnInit() {
    this.isLoggedIn = this.tokenService.isLoggedIn();
    if (!this.isLoggedIn){
      window.location.replace(URL + "login")
    } else {
      this.userInSession = this.tokenService.getUser();
      this.gameService.loadUserGames().subscribe({
        next: data => {
          console.log(data);
          this.userGames = data;
        }
      });
    }
  }

  logout(): void {
    this.tokenService.logout();
    window.location.reload();
  }

  editAccount(): void {
    this.isEditing = !this.isEditing;
    this.userInSession = this.tokenService.getUser();
  }

  deleteAccount(): void {
    this.userService.deleteUser(this.userInSession.id).subscribe(() => {
      this.tokenService.logout();
      window.location.reload();
    });
  }

  onEditSubmit(): void {
    const {id, username, email, password} = this.userInSession;
    this.userService.editUser(id, username, email, password).subscribe();
    this.tokenService.setUser(this.userInSession);
    this.editAccount();
  }

}