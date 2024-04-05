import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SiteComponent } from './Pages/site/site.component';
import { HeaderComponent } from './Components/header/header.component';
import { FooterComponent } from './Components/footer/footer.component';
import { HeaderButtonsComponent } from './Components/header/headerButtons/headerButtons.component';
import { HeaderSearchBarComponent } from './Components/header/headerSearchBar/headerSearchBar.component';
import { ShopSiteComponent } from './Components/shopSite/shopSite.component';
import { SideBarFilterComponent } from './Components/side-bar-filter/side-bar-filter.component';
import { BreadCrumbBarComponent } from './Components/bread-crumb-bar/bread-crumb-bar.component';
import { CheckBoxFilterComponent } from './Components/side-bar-filter/check-box-filter/check-box-filter.component';
import { MeasuresFilterComponent } from './Components/side-bar-filter/measures-filter/measures-filter.component';
import { ColorsFilterComponent } from './Components/side-bar-filter/colors-filter/colors-filter.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';
import { PriceFilterComponent } from './Components/side-bar-filter/price-filter/price-filter.component';
import { ShopCardsComponent } from './Components/shopSite/shop-cards/shop-cards.component';
import { HttpClientModule } from '@angular/common/http';
import { HeaderDropDownMenuComponent } from './Components/header/header-drop-down-menu/header-drop-down-menu.component';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './Pages/login/login.component';
import { MatFormFieldControl, MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule, MatIconButton } from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { OrdiniComponent } from './Pages/gestionale/ordini/ordini.component';
import { ImpostazioniComponent } from './Pages/gestionale/impostazioni/impostazioni.component';
import { ProdottiComponent } from './Pages/gestionale/prodotti/prodotti.component';
import { SidebarComponent } from './Pages/gestionale/sidebar/sidebar.component';
import { PageNotFoundComponent } from './Components/page-not-found/page-not-found.component'; 


@NgModule({
  declarations: [
    AppComponent,
    SiteComponent,
    HeaderComponent,
    FooterComponent,
    HeaderButtonsComponent,
    HeaderSearchBarComponent,
    ShopSiteComponent,
    SideBarFilterComponent,
    BreadCrumbBarComponent,
    CheckBoxFilterComponent,
    MeasuresFilterComponent,
    ColorsFilterComponent,
    PriceFilterComponent,
    ShopCardsComponent,
    HeaderDropDownMenuComponent,
    LoginComponent,
    OrdiniComponent,
    ImpostazioniComponent,
    ProdottiComponent,
    SidebarComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSliderModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
  ],
  providers: [
  
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
