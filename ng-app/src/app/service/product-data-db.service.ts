import { Injectable } from '@angular/core';
import {Product} from '../products/product';
import {Observable} from 'rxjs/Observable';
import {Subscriber} from 'rxjs/Subscriber';

@Injectable()
export class ProductDataDbService {
  constructor() { }
  getProductsData(){
    //return Observable.create((subscriber:Subscriber<Product[]>)=>subscriber.next(this.products));
  }
  getProduct(id:number){
    //let product = this.products.find(product=>product.id === +id);
    //return Observable.create((subscriber:Subscriber<Product>)=>subscriber.next(product));
  }

}
