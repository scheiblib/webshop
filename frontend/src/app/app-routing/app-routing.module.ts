import { NgModule } from '@angular/core';
import {Router, RouterModule, Routes} from '@angular/router';
import {UsersComponent} from "../components/users/users.component";
import {ItemsComponent} from "../components/items/items.component";
import {OrderComponent} from "../components/order/order.component";
import {UserDetailsComponent} from "../components/user-details/user-details.component";
import {ItemDetailsComponent} from "../components/item-details/item-details.component";

const routes: Routes = [
  {path: 'users', component: UsersComponent},
  {path: 'users/:id', component: UserDetailsComponent},
  {path: 'items', component: ItemsComponent},
  {path: 'items/:id', component: ItemDetailsComponent},
  {path: 'orders', component: OrderComponent},
  // {path: 'orders/:id', component: OrderDetailsComponent},
  {path: '', redirectTo: '/items', pathMatch: 'full'}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
