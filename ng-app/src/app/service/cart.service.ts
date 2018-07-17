import { Injectable } from '@angular/core';
import { Product } from "../products/product";
import {BehaviorSubject} from "rxjs/BehaviorSubject";

@Injectable()
export class CartService {
  products:Product[] = [];

  private itemsNumSource = new BehaviorSubject<number>(0);
  numItems = this.itemsNumSource.asObservable();

  product: Product = {
    "id": 1,
  "productId": "G-001",
  "name": "string",
  "description": "string",
  "price": 599,
  "image": "dota.jpg",
  "show": true,
  "selected": false,
  "clicked": 15
  };

  constructor() {
    this.products.push(this.product);
    this.changeNumItems(this.products.length);
  }

  changeNumItems(num:number) {
    this.itemsNumSource.next(num);
  }

  addProduct(product:Product) {
    this.products.push(product);
   //
  }

  getItems(){
    return this.products;
  }
}
