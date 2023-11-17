import { NgModule } from '@angular/core';
import {Router, RouterModule, Routes} from '@angular/router';
import {UsersComponent} from "../components/users/users.component";
import {UserDetailsComponent} from "../components/user-details/user-details.component";
import {AuthComponent} from "../components/auth/auth.component";
import {RegisterComponent} from "../components/register/register.component";

const routes: Routes = [
  {path: 'auth', component: AuthComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'users', component: UsersComponent},
  {path: 'users/:id', component: UserDetailsComponent},
  {path: '', redirectTo: '/items', pathMatch: 'full'},
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
