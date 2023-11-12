import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Item} from "../model/item";
import {Order} from "../model/order";
import {ItemService} from "./item.service";
import {environment} from "../environment";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient, private itemService: ItemService) { }

  public getOrder(id: number): Observable<Order> {
    return this.http.get<Order>(`${this.apiServerUrl}/orders/${id}`);
  }

  public getAllOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiServerUrl}/orders/allOrders`);
  }

  public myOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiServerUrl}/orders/myOrders`);
  }

  public addItem(itemId: number): void {
    this.http.put(`${this.apiServerUrl}/orders/addItem/${itemId}`, this.itemService.getItemById(itemId));
  }

  public placeOrder(order: Order): Observable<Order> {
    return this.http.post<Order>(`${this.apiServerUrl}/orders/placeOrder`, order);
  }
}
