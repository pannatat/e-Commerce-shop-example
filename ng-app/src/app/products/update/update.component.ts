import {Component,OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {ProductDataServerService} from '../../service/product-data-server.service';
import {Product} from '../product';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  constructor(private route: ActivatedRoute, private productDataServerService:ProductDataServerService, private router: Router) { }
  product: Product;
  isNoData:boolean;
  inputCount: number;
  ngOnInit() {
    this.isNoData = false;
    this.inputCount = 15;
    this.route.params
      .switchMap((params:Params)=> this.productDataServerService.getProduct(+params['id']))
      .subscribe((product:Product)=>{
        if(this.product !== null)
          this.product = product;
        else
          this.isNoData = true;
      })
  }
  updateProduct(product:Product){
    this.productDataServerService.updateProduct(product).subscribe((product)=>{
      console.log(product);
      this.router.navigate(['/'])
    })
  }

  onFileChange(event,product: any){
    var filename = event.target.files[0].name;
    console.log(filename);
    this.product.image = filename;
  }
}
