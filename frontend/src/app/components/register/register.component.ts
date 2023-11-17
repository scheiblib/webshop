import {Component, inject} from '@angular/core';
import {take} from "rxjs";
import {UserService} from "../../service/user.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  userService: UserService = inject(UserService);

  registrationForm!: FormGroup;
  registrationSuccess: boolean = false;

ngOnInit() {
  this.registrationForm = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    phone: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required]),
  });

}
  register() {
    this.userService
      .register(
        this.registrationForm.value.firstName,
        this.registrationForm.value.lastName,
        this.registrationForm.value.email,
        this.registrationForm.value.password,
        this.registrationForm.value.phone,
        this.registrationForm.value.city,
        this.registrationForm.value.address,
      )
      .pipe(take(1))
      .subscribe((response) => {
        this.registrationSuccess = true;
      });
  }
}
