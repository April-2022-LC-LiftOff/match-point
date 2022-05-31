import { Component, OnInit } from "@angular/core";
import { User } from "../user";
import { AuthService } from "../_services/auth.service";
import { TokenService } from "../_services/token.service";
const URL = "http://localhost:4200/"

@Component({
  selector: "app-signup",
  templateUrl: "./signup.component.html",
  styleUrls: ["./signup.component.css"],
})
export class SignupComponent implements OnInit {
  title: string = "Sign Up";
  model: User = new User();
  isLoggedIn: boolean = false;
  errMsg?: string;

  constructor(
    private authService: AuthService,
    private tokenService: TokenService
  ) {}

  ngOnInit() {
    this.isLoggedIn = this.tokenService.isLoggedIn();
    if (this.isLoggedIn){
      window.location.replace(URL + "profile")
    }
  }

  reloadPage(): void {
    window.location.reload();
  }

  registerUser() {
    this.authService
      .register(this.model.username, this.model.email, this.model.password)
      .subscribe({
        next: (data) => {
          console.log(data);
          this.tokenService.setToken(data.accessToken);
          this.tokenService.setUser(data);
          this.isLoggedIn = true;
          this.reloadPage();
          this.model = new User();
        },
        error: (err) => {
          console.log(err);
          this.errMsg = err.error.message;
        },
      });
  }
}
