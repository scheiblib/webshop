import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {environment} from "../environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiServerUrl = environment.apiBaseUrl;

  http: HttpClient = inject(HttpClient);

  login(email: string, password: string): Observable<HttpResponse<void>> {
    return this.http.post<void>(
      `${this.apiServerUrl}/users/login`,
      {email, password},
      {
        observe: 'response',
      }
    );
  }

  register(firstName: string,
           lastName: string,
           email: string,
           password: string,
           phone: string,
           city: string,
           address: string) : Observable<void> {
    return this.http.post<void>(`${this.apiServerUrl}/users/register`, {firstName, lastName, email, password, phone, city, address});
  }

  profile(): Observable<any> {
    return this.http.get<any>('/me');
  }

  getUsers(): Observable<any> {
    return this.http.get<any>(`${this.apiServerUrl}/users/all`);
  }

  getUserByEmail(email: string | null) {
    return this.http.get<any>(`${this.apiServerUrl}/users/${email}`)
  }

}
