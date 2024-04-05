import { Component ,Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-shop-site',
  templateUrl: './shopSite.component.html',
  styleUrls: ['./shopSite.component.css']
})
export class ShopSiteComponent {
  products:any;

  setProducts(products:any){
   this.products = products;
  }
}
