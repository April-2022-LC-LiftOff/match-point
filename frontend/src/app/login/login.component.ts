import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { TokenService } from '../_services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  title: string = "Login";
  model: any = {
    username: null,
    password: null,
  }
  isLoggedIn: boolean = false;
  errMsg?: string;

  constructor(private authService: AuthService, private tokenService: TokenService) { }

  ngOnInit(): void {
    if (this.tokenService.getToken()){
      this.isLoggedIn = true;
    }
  }

  reloadPage(): void {
    window.location.reload();
  }

  onSubmit(): void {
    this.authService.login(this.model.username, this.model.password).subscribe({
      next: data => {
        this.tokenService.setToken(data.accessToken);
        this.tokenService.setUser(data);
        this.isLoggedIn = true;
        this.reloadPage();
      },
      error: err => {
        this.errMsg = err.error.message;
      }
    })
  }

}
