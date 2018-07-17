import { Injectable } from '@angular/core';
import {Product} from "../products/product";
import "rxjs/add/operator/mergeMap";
import {Http, Response, RequestOptions, Headers, URLSearchParams} from "@angular/http";
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';




@Injectable()
export class ProductDataServerService {
  private product: Product;
  constructor(private http:Http) { }

  getProductsData(){
    let productArray: Product[];
    return this.http.get('http://localhost:8080/product')
      .map(res=>res.json());
  }

  getProduct(id:number){
    let product:Product;
    return this.http.get('http://localhost:8080/product/'+id)
      .map((res:Response)=>{
      if(res){
        if(res.status === 200){
          return res.json();
        }
        if(res.status === 204){
          return null;
        }
      }
      });
  }
  addProduct(product: Product,file:any):Observable<Product>{
    const formData = new FormData();
    let fileName: string;
    formData.append('file',file);
    return this.http.post('http://localhost:8080/upload',formData)
      .flatMap(filename => {
        product.image = filename.text();
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers, method: 'post'});
        let body = JSON.stringify(product);
        return this.http.post('http://localhost:8080/product',body,options)
          .map(res =>{
            return res.json()
          })
      })
  }

  findProduct(search:string){
    let product: Product;
    let params: URLSearchParams = new URLSearchParams();
    params.set('search',search);
    let headers = new Headers({
      'Content-type': 'application/json'
      //, 'Authorization': 'Bearer ' + this.authenticationService.getToken()
    });
    return this.http.get('http://localhost:8080/products/',{headers:headers,search:params})
      .map(res => res.json());
  }


  updateProduct(product:Product){
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers});
    return this.http.put('http://localhost:8080/product',JSON.stringify(product),options)
      .map((response:Response) => response.json()
      );
  }

}
