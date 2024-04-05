import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/Services/api.service';
import { ProductService } from 'src/app/Services/services/product.service';

interface Prodotto {
[x: string]: any;
id: number;
nome: string;
descrizione: string;
foto: string;
codice_articolo: string;
categoria: string;
prezzo: number;
colore: string;
taglia: string;
genere: string;
accessorio: boolean;
peso: number;
quantita: number;
dataInserimento: string;
}

@Component({
  selector: 'prodotti',
  templateUrl: './prodotti.component.html',
  styleUrls: ['./prodotti.component.css']
})
export class ProdottiComponent implements OnInit {

  prodItem: Prodotto[] = [];

  filtrato = false;

  constructor(private prodottiService: ProductService, private apiService : ApiService){}


  ngOnInit() {
    this.caricaOrdini();
  }

  caricaOrdini(): void {

    this.apiService.apiGET("http://localhost:5454/products/getAll").subscribe(
      (response: any) => {
        this.prodItem = response; 
      },
      (error: any) => {
        console.log('Errore durante il recupero dei prodotti:', error);
      }
    );
  }


  editProduct(productId: string): void {
    // La logica di modifica dovrebbe essere gestita diversamente in un'app Angular, possibilmente utilizzando un form
  }

  deleteProduct(productId: string): void {
    if (!confirm("Sei sicuro di voler eliminare questo prodotto?")) {
      return;
    }
  
    
    this.apiService.deleteProduct(productId).subscribe({
      next: (response) => {
        console.log('Prodotto eliminato con successo:', response);
  
        this.caricaOrdini();
      },
      error: (error) => {
        console.error('Errore durante leliminazione del prodotto:', error);
      }
    });
  }
  
  

  addProduct(): void {
    
  }

  //   filtraEsaurimento(): void {
  //   const tableBody: HTMLElement | null = document.getElementById('product-table-body');
  //   if (tableBody) {
  //     tableBody.innerHTML = '';
  //     this.prodItem.filter((prodotto : Prodotto) => prodotto.quantita <= 10).forEach((prod) => {
  //       const row: string = `
  //         <tr>
  //           <td>${prod.id}</td>
  //           <td>${prod.nome}</td>
  //           <td>${prod.categoria}</td>
  //           <td>${prod.prezzo}</td>
  //           <td>${prod.quantita} (In esaurimento)</td>
  //           <td class="action-buttons">
  //             <button onclick="editProduct('${prod.id}')"><i class="fas fa-edit"></i></button>
  //             <button onclick="deleteProduct('${prod.id}')"><i class="fas fa-trash"></i></button>
  //           </td>
  //         </tr>
  //       `;
  //       if (tableBody) {
  //         tableBody.innerHTML += row;
  //       }
  //     });
  //   }
  // }

  showAddProductForm(){
    

  }

  filtraEsaurimento(): void {
    if(!this.filtrato){
      const arrayProd: Prodotto[] = this.prodItem.filter((prod: Prodotto) => prod.quantita < 10);
      this.prodItem = arrayProd;
    }
    else
    {
      this.apiService.apiGET("http://localhost:5454/products/getAll").subscribe(
      (response: any) => {
        this.prodItem = response; // Popola l'array di prodotti con i dati ricevuti dalla chiamata API
      },
      (error: any) => {
        console.log('Errore durante il recupero dei prodotti:', error);
      }
    );
    }
    this.filtrato = !this.filtrato;
  }
}
