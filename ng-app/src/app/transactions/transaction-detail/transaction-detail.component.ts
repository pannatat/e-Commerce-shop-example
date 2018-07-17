import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {TransactionDataServerService} from '../../service/transaction-data-server.service';
import {Transaction} from '../transaction';

@Component({
  selector: 'app-transaction-detail',
  templateUrl: './transaction-detail.component.html',
  styleUrls: ['./transaction-detail.component.css']
})
export class TransactionDetailComponent implements OnInit {

  constructor(private route: ActivatedRoute, private transactionDataServerService: TransactionDataServerService) { }
  transaction:Transaction;
  isNoData:boolean;
  inputCount:number;
  ngOnInit() {
    this.isNoData = false;
    this.inputCount = 15;
    this.route.params
      .switchMap((params:Params)=>this.transactionDataServerService.getTransaction(+params['id']))
      .subscribe((transaction:Transaction)=>{
      if(this.transaction !== null)
        this.transaction = transaction;
      else
        this.isNoData = true;
      })
  }

}
