import { Injectable } from '@angular/core';
import {Http, Headers, URLSearchParams, Response} from "@angular/http";
import {Transaction} from '../transactions/transaction';
import 'rxjs/add/operator/map';

@Injectable()
export class TransactionDataServerService {

  constructor(private http:Http) { }

  getTransactionsData(){
    return this.http.get('http://localhost:8080/transaction')
      .map(res=>res.json())
  }

  findTransaction(search:string){
    let transaction: Transaction;
    let params: URLSearchParams = new URLSearchParams();
    params.set('search',search);
    let headers = new Headers({
      'Content-type': 'application/json'
      //, 'Authorization': 'Bearer ' + this.authenticationService.getToken()
    });
    return this.http.get('http://localhost:8080/transactions/',{headers:headers,search:params})
      .map(res => res.json());
  }

  getTransaction(id:number){
    let transaction:Transaction;
    return this.http.get('http://localhost:8080/transaction/'+id)
      .map((res:Response)=>{
      if(res.status === 200){
        return res.json();
      }
      if(res.status === 204){
        return null;
      }
      });
  }

}
