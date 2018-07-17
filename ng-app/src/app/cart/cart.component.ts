import {Component, OnInit} from '@angular/core';
import {Product} from '../products/product';
import {Router} from '@angular/router';
import {ProductDataServerService} from '../service/product-data-server.service';
import has = Reflect.has;
import {AuthenticationService} from "../service/authentication.service";
import {CartService} from "../service/cart.service";
import * as _ from "lodash";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  private numItems: number;
  items: Product[] = [];
  products: Product[];

  constructor(private productDataServerService: ProductDataServerService, private router: Router, private authen: AuthenticationService, private cartService: CartService) {
  }

  ngOnInit() {
    this.productDataServerService.getProductsData().subscribe(products => this.products = products);
    this.items = this.cartService.getItems();
  }

  checkout(products: Product[]) {
    if (this.authen.hasRole("CUSTOMER"))
      this.router.navigate(['/confirmation', products])
    else
      this.router.navigate(['/login'])
  }

  removeSelected(product: Product) {
    console.log(product);
    //   console.log(item);
    //
    //   let removedItem = _.remove(this.items, (product) => {
    //     return product.id == item.id;
    //   });
    //
    //   this.cartService.changeNumItems(this.items.length);
    //   console.log(this.numItems);
    //   console.log(this.items.length);
    //   console.log("Removed", removedItem);
    // }
    product.selected = false;
    this.productDataServerService.updateProduct(product).subscribe((product) => {
      console.log(product);
    });
       // this.cartService.changeNumItems(this.items.length);
       // console.log(this.numItems);
       // console.log(this.items.length);

  }
  getTotalPrice() {
    //power of _
    return _.sumBy(this.items, item => item.amountSelected * item.price);
  }
}
