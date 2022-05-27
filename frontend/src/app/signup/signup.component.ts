import { Component, OnInit } from "@angular/core";
import { User } from "../user";
import { AuthService } from "../_services/auth.service";

@Component({
  selector: "app-signup",
  templateUrl: "./signup.component.html",
  styleUrls: ["./signup.component.css"],
})
export class SignupComponent implements OnInit {
  title: string = "Sign Up";
  model: User = new User();
  errMsg?: string;

  constructor(private authService: AuthService) {}

  ngOnInit() {}

  registerUser() {
    this.authService
      .register(this.model.username, this.model.email, this.model.password)
      .subscribe({
        next: (data) => {
          console.log(data);
          this.model = new User();
        },
        error: (err) => {
          console.log(err);
          this.errMsg = err.error.message;
        },
      });
  }
}
