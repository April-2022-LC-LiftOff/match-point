import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserAuthService } from '../user-auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  title: string = 'Sign Up';
  model: User = new User();

  constructor(private auth: UserAuthService) { }

  ngOnInit() {
  }

  registerUser(){
    this.auth.addUser(this.model).subscribe();
    this.model = new User();
  }

}
