import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SiteComponent } from './Pages/site/site.component';
import { LoginComponent } from './Pages/login/login.component';
import { ShopSiteComponent } from './Components/shopSite/shopSite.component';
import { OrdiniComponent } from './Pages/gestionale/ordini/ordini.component';
import { ImpostazioniComponent } from './Pages/gestionale/impostazioni/impostazioni.component';
import { ProdottiComponent } from './Pages/gestionale/prodotti/prodotti.component';
import { PageNotFoundComponent } from './Components/page-not-found/page-not-found.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'shop', component: ShopSiteComponent },
  { path: 'home', component: SiteComponent },
  { path: 'login', component: LoginComponent },
  { path: 'gestionale/ordini', component: OrdiniComponent},
  { path: 'gestionale/impostazioni', component: ImpostazioniComponent},
  { path: 'gestionale/prodotti', component: ProdottiComponent},
  { path: '**', component: PageNotFoundComponent } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }