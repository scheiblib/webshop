import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {User} from "../model/user";
import {environment} from "../environment";
import {USERS} from "../mock-user";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  getUsers(): Observable<User[]> {
    return of(USERS);
    // return this.http.get<User[]>(`${this.apiServerUrl}/users/findAllUsers`);
  }
  getUserById(id: number) {
    const user = USERS.find(u => u.id === id)!;
    return of(user);
  }

}
