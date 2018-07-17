import { Component, OnInit } from '@angular/core';
import {TransactionDataServerService} from '../../service/transaction-data-server.service';
import {Router} from '@angular/router';
import {Transaction} from '../transaction';

@Component({
  selector: 'app-list-of-transactions',
  templateUrl: './list-of-transactions.component.html',
  styleUrls: ['./list-of-transactions.component.css']
})
export class ListOfTransactionsComponent implements OnInit {
  transactions:Transaction[];
  constructor(private transactionDataServerService: TransactionDataServerService, private router: Router) { }

  ngOnInit() {
    this.transactionDataServerService.getTransactionsData().subscribe(transitions => this.transactions = transitions);
  }
  search:string;
  onSearch(){
    this.transactionDataServerService.findTransaction(this.search)
      .subscribe(
        transactions => this.transactions = transactions
        ,(error) => {
          if (error.status === 401){
            //error
          }
        })
  }

  goToDetail(transaction:Transaction){
    this.router.navigate(['/transaction-detail',transaction.id])
  }

}
