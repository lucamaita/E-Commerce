import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject, tap } from 'rxjs';
import { Ordine } from './OrdineModel'; 

@Injectable({
  providedIn: 'root'
})
export class OrdiniService {
  private apiUrl = 'http://localhost:5454/api/orders/findAll'; 
  constructor(private http: HttpClient) { }

 

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwt');
    return new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem('jwt')}`,
    });
  }

  getOrdini(): Observable<any> {
    const headers = this.getHeaders();
    return this.http.get(this.apiUrl, {headers});
  }
}