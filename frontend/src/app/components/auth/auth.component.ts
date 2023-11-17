import {inject, NgModule} from "@angular/core";

import { Component } from '@angular/core';
import {FormControl, FormGroup, NgForm, Validators} from "@angular/forms";
import { JwtHelperService } from '@auth0/angular-jwt';
import {UserService} from "../../service/user.service";
import { take } from 'rxjs';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent {
  userService = inject(UserService);

  isLoginMode = true;

  loginForm!: FormGroup;
  isLoggedIn: boolean = false;
  name = '';
  roles: string[] = [];
  ngOnInit(): void {
    if(localStorage.getItem('auth-token') !== '') {
      this.isLoggedIn = true;
      this.setName();
    }
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    });
  }

  login() {
    this.userService
      .login(this.loginForm.value.email, this.loginForm.value.password)
      .pipe(take(1))
      .subscribe((response) => {
        localStorage.setItem(
          'auth-token',
          response.headers.get('auth-token') || ''
        );
        if (localStorage.getItem('auth-token') !== '') {
          this.isLoggedIn = true;
          this.setName();
        }
      });
  }

  logout() {
    localStorage.setItem('auth-token', '');
    this.isLoggedIn = false;
  }
  onSwitchMode() {
    this.isLoginMode = !this.isLoginMode;
  }

  onSubmit(form: NgForm) {
    console.log(form.value);
    form.reset();
  }

  private setName() {
    const helper = new JwtHelperService();
    const decodedToken = helper.decodeToken(
      localStorage.getItem('auth-token') || ''
    );
    this.name = decodedToken?.sub;
    this.roles = decodedToken['roles'];
  }
}
