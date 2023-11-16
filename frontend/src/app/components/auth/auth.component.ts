import { Component } from '@angular/core';
import {NgForm} from "@angular/forms";
import {Register} from "../../model/register";
import {Login} from "../../model/login";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent {
  isLoginMode = true;

  credentials: Login = {
    email :'',
    password :'',
  }
  register: Register = {
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    phone: '',
    city: '',
    address: '',
  }
  onSwitchMode() {
    this.isLoginMode = !this.isLoginMode;
  }

  onSubmit(form: NgForm) {
    console.log(form.value);
    form.reset();
  }
}
