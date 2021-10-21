import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = 'kiran@gmail.com'
  password = 'kiran@123'
  invalidLogin = false;

  

  constructor(private router: Router,
    private loginservice: AuthenticationService) { }

  ngOnInit() {

  }

  checkLogin() {
    // console.log("checking");
    // console.log(sessionStorage.getItem("userLogin"));

    
    if (this.loginservice.authenticate(this.username,this.password))
     {

    //  this.loginservice.authenticate(this.username,this.password)
      this.router.navigate(['/{ sessionStorage.getItem("userId")}/startExam'])
      this.invalidLogin = false
      console.log("sucessful Login!!");
    } else
      this.invalidLogin = true
     
  }

}
