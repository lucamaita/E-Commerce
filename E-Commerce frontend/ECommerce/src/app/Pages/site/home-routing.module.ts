import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ShopComponent } from '../Shop/shop.component';

const routes: Routes = [
  { path: 'pino', component: ShopComponent },
  { path: '', redirectTo: '/pino' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
