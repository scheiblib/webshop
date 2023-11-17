import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing/app-routing.module';

import { AppComponent } from './app.component';
import { UsersComponent } from './components/users/users.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { OrderComponent } from './components/order/order.component';
import { ItemsComponent } from './components/items/items.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { ItemDetailsComponent } from './components/item-details/item-details.component';
import {FormsModule} from "@angular/forms";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {AuthInterceptor} from "./auth.interceptor";
import { ReactiveFormsModule } from '@angular/forms';
import {AuthComponent} from "./components/auth/auth.component";
import { RegisterComponent } from './components/register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    OrderComponent,
    ItemsComponent,
    UserDetailsComponent,
    ItemDetailsComponent,
    AuthComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    NgbModule,
    ReactiveFormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
  ],
  bootstrap: [AppComponent, RegisterComponent, AuthComponent]
})
export class AppModule { }
