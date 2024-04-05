import { Component, OnInit } from '@angular/core';
import { Ordine } from './OrdineModel';
import { OrdiniService } from './ordini.service';

@Component({
  selector: 'ordini',
  templateUrl: './ordini.component.html',
  styleUrls: ['./ordini.component.css']
})
export class OrdiniComponent implements OnInit {

  ordini: Ordine[] = [];

  isActive = false;

  constructor(private ordiniService: OrdiniService) { }

  ngOnInit(): void {
    this.caricaOrdini();
  }

  caricaOrdini(): void {
    this.ordiniService.getOrdini().subscribe((ordin: Ordine[]) => {
      this.ordini = ordin;
      
    });
  }

  onClick(){
    this.isActive = !this.isActive;

  }
}
