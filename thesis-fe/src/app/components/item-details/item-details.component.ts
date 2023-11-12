import {Component, Input, OnInit} from '@angular/core';
import {Item} from "../../model/item";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {ItemService} from "../../service/item.service";

@Component({
  selector: 'app-items-details',
  templateUrl: './item-details.component.html',
  styleUrls: ['./item-details.component.css']
})
export class ItemDetailsComponent implements OnInit {
@Input() item?: Item;

  constructor(
    private route: ActivatedRoute,
    private itemService: ItemService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getItem();
  }

  private getItem() {
      const id = Number(this.route.snapshot.paramMap.get('id'));
      this.itemService.getItemById(id)
        .subscribe(item => this.item = item);
  }

  goBack() {
    this.location.back();
  }
}
