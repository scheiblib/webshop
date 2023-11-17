import {User} from "./user";
import {Item} from "./item";

export interface Order {
  id: number,
  status: string,
  totalPrice: number,
  createdAt: Date,
  //user: User,
  items: Item[]
}
