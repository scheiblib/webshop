import { EventEmitter, Component, Output } from '@angular/core';
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";
import {Login} from "../../model/login";
import {Register} from "../../model/register";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
})
export class LoginFormComponent {

  @Output() onSubmitLoginEvent = new EventEmitter();
  @Output() onSubmitRegisterEvent = new EventEmitter();

  constructor(private userService: UserService, private router: Router) {
  }

  active: string = "login";

  credentials: Login = {
    email: "",
    password: ""
  }

  register: Register = {
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    phone: "",
    city: "",
    address: ""
  }

  loginError = false;

  onLoginTab(): void {
    this.active = "login";
  }

  onRegisterTab(): void {
    this.active = "register";
  }

  login() {
    this.userService.login(this.credentials).subscribe(
      response => {
        const token = response.token;

        localStorage.setItem('token', token);

        this.router.navigate(['/items']);
      },
      error => {
        this.loginError = true;
        alert("Hibás e-mail vagy jelszó!");
      }
    )
  }

  onSubmitRegister(): void {
    this.onSubmitRegisterEvent.emit({
      "firstName": this.register.firstName,
      "lastName": this.register.lastName,
      "email": this.register.email,
      "password": this.register.password,
      "phone": this.register.phone,
      "city": this.register.city,
      "address": this.register.address
    });
  }

}
