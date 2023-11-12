import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {User} from "../model/user";
import {Item} from "../model/item";
import {environment} from "../environment";
import {Items} from "../mock-item";

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  private apiServerUrl = environment.apiBaseUrl;


  constructor(private http: HttpClient) { }

  getItems(): Observable<Item[]> {
    return of(Items);
    // return this.http.get<User[]>(`${this.apiServerUrl}/users/findAllUsers`);
  }
  getItemById(id: number) {
    const user = Items.find(i => i.id === id)!;
    return of(user);
  }

  // public getItems(): Observable<Item[]> {
  //   return this.http.get<Item[]>(`${this.apiServerUrl}/items/findAll`);
  // }
  //
  // public getItemById(itemId: number): Observable<Item> {
  //   return this.http.get<Item>(`${this.apiServerUrl}/items/findItemById/${itemId}`);
  // }
  //
  // public addItems(items: []): Observable<Item[]> {
  //   return this.http.post<Item[]>(`${this.apiServerUrl}/items/addItems`, items);
  // }
}
