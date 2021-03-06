import { Component, OnInit} from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { User } from '../models/user';
import { UserService } from '../user.service';
import { Address } from '../address';
import { Router } from '@angular/router';



@Component({
  selector: 'app-adduser',
  templateUrl: './adduser.component.html',
  styleUrls: ['./adduser.component.css'],
  providers: [DatePipe]
})
export class AddUserComponent implements OnInit{
  user: User = new User();
  isAdded = false;
  constructor(private userService: UserService, private datePipe: DatePipe, private router:Router){}

  currentDate = new Date();
  userForm!: FormGroup;
  ngOnInit() {
    this.userForm = new FormGroup({
      userName: new FormControl('', [Validators.required, Validators.minLength(5)]),
      userEmail: new FormControl('', [Validators.required, Validators.minLength(3)]),
      userMobile: new FormControl('', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]),
      dateOfBirth: new FormControl(this.datePipe.transform(this.currentDate, 'yyyy-MM-dd')),
      qualification: new FormControl('', [Validators.required]),
      yearOfGraduation: new FormControl('', [Validators.required,  Validators.pattern("^[0-9]{4}$")]),
      password: new FormControl('', [Validators.required, Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{5,}')]),
      address: new FormControl('', [Validators.required,  Validators.minLength(7)]),

    });
  }

  onSubmit(){
    this.user.userName = this.userForm.value.userName;
    this.user.userEmail = this.userForm.value.userEmail;
    this.user.userMobile = this.userForm.value.userMobile;
    this.user.dateOfBirth = this.userForm.value.dateOfBirth;
    this.user.qualification = this.userForm.value.qualification;
    this.user.yearOfGraduation = this.userForm.value.yearOfGraduation;
    this.user.password = this.userForm.value.password;
    var str = this.userForm.value.address;
    var addr = new Address();
    addr.city = str.split(",")[0];
    addr.state = str.split(",")[1];
    this.user.address = addr;


    this.save();

    this.router.navigate(["/login"]);
  }

  save(){
    this.userService.addUser(this.user)
                    .subscribe((user: any)=> {console.log(user);
                      this.isAdded = true;
                    }, (error: any)=>console.log(error))
  }
  resetUserForm(){
    this.isAdded = false;
    this.userForm.reset();
  }
}
