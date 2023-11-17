import {Component, inject, Input, OnInit} from '@angular/core';
import { Location } from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../service/user.service";
import {User} from "../../model/user";

@Component({
  selector: 'app-users-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
  userService: UserService = inject(UserService);

  ngOnInit(): void {

  }


}
