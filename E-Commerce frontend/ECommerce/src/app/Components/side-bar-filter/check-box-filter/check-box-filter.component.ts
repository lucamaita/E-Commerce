import { Component, EventEmitter, Output } from '@angular/core';
import { ApiService } from 'src/app/Services/api.service';

@Component({
  selector: 'check-box-filter',
  templateUrl: './check-box-filter.component.html',
  styleUrls: ['./check-box-filter.component.css']
})
export class CheckBoxFilterComponent {
  @Output() pippo: EventEmitter<any> = new EventEmitter<any>();

  constructor(private apiService: ApiService) { }

  fetchProducts(categoria: string): void {
    const url = `http://localhost:5454/products/getAllFe/F/${categoria}`;
    this.apiService.apiGET(url).subscribe(
      (response: any) => {
        this.pippo.emit(response)
      },
      (error: any) => {
        console.log('Errore durante il recupero dei prodotti:', error);
      }
    );
  }


}
