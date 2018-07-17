import {NgModule}              from '@angular/core';
import {RouterModule, Routes}  from '@angular/router';
import {CartComponent} from './cart/cart.component';
import {LoginComponent} from './login/login.component';
import {ConfirmationComponent} from './confirmation/confirmation.component';
import {SlipComponent} from './confirmation/slip/slip.component';
import {PaypalComponent} from './confirmation/paypal/paypal.component';
import {ListOfTransactionsComponent} from './transactions/list-of-transactions/list-of-transactions.component';
import {TransactionDetailComponent} from './transactions/transaction-detail/transaction-detail.component';
import {ActorListComponent} from './Actor/actor-list/actor-list.component';


const appRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'cart', component: CartComponent},
  {path: 'confirmation', component: ConfirmationComponent},
  {path: 'slip', component: SlipComponent},
  {path: 'paypal', component: PaypalComponent},
  {path: 'list-of-transactions', component: ListOfTransactionsComponent},
  {path: 'transaction-detail/:id', component: TransactionDetailComponent},
  {path: 'actor', component: ActorListComponent},
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
