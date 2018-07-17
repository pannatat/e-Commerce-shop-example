import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ProductDataDbService} from '../../service/product-data-db.service';
import {Router} from '@angular/router';
import {Product} from '../product';
import {ProductDataServerService} from '../../service/product-data-server.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  product: any = {};
  constructor(private productDataServerService: ProductDataServerService, private router: Router) { }

  ngOnInit() {
    this.product = new Product();
  }

  @ViewChild('fileInput') inputEl: ElementRef;

  addProduct(product: Product){
    let result: Product;
    console.log(this.product);
    let inputEl: HTMLInputElement = this.inputEl.nativeElement;
    product.show = true;
    this.productDataServerService.addProduct(product,inputEl.files.item(0))
      .subscribe(resultProduct =>{
        result = resultProduct;
        if(result != null){
          this.router.navigate(['/list']);
        }else {
          alert('Error in adding the product');
        }
    })
  }

  onFileChange(event,product: any){
    var filename = event.target.files[0].name;
    console.log(filename);
    this.product.image = filename;
  }

}
