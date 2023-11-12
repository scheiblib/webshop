import {Component, OnInit} from '@angular/core';
import {Item} from "../../model/item";
import {ItemService} from "../../service/item.service";

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.css']
})
export class ItemsComponent implements OnInit {
  items: Item[] = [];
  constructor(private itemService: ItemService) {}
  ngOnInit(): void {
    this.getItems();
  }

  private getItems() {
    this.itemService.getItems()
      .subscribe(items => this.items = items);
  }
}
