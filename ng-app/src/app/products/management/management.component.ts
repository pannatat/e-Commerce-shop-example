import { Component, OnInit } from '@angular/core';
import {Product} from '../product';
import {Router} from '@angular/router';
import {ProductDataServerService} from '../../service/product-data-server.service';

@Component({
  selector: 'app-management',
  templateUrl: './management.component.html',
  styleUrls: ['./management.component.css']
})
export class ManagementComponent implements OnInit {
  products: Product[];
  constructor(private productDataServerService: ProductDataServerService, private router: Router) { }

  ngOnInit() {
    this.productDataServerService.getProductsData().subscribe(products => this.products = products);
  }

  goToUpdateProduct(product:Product){
    this.router.navigate(['/update',product.id])
  }

  deleteProduct(product:Product){
    product.show = false;
    this.productDataServerService.updateProduct(product).subscribe((product)=>{
      console.log(product);
      this.router.navigate(['/'])
    })
  }



}
