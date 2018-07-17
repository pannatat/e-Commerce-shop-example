import {RouterModule, Routes} from '@angular/router';
import {ProductListComponent} from './product-list/product-list.component';
import {AddComponent} from './add/add.component';
import {NgModule} from '@angular/core';
import {ViewComponent} from './view/view.component';
import {ManagementComponent} from './management/management.component';
import {UpdateComponent} from './update/update.component';

const productRoutes: Routes = [
  {path: 'list', component: ProductListComponent},
  {path: 'add-product', component: AddComponent },
  {
    path: '',
    redirectTo: '/list',
    pathMatch: 'full'
  },
  {path: 'detail/:id', component: ViewComponent},
  {path: 'manage-product', component: ManagementComponent},
  {path: 'update/:id', component: UpdateComponent}
];
@NgModule({
  imports: [
    RouterModule.forRoot(productRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class ProductRoutingModule{}

