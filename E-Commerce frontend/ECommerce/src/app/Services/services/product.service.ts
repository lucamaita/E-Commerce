import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable,  } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private baseUrl = 'http://localhost:5454';

  productSubject = new BehaviorSubject<any>({
    prodotti: []
  });

  constructor(private http: HttpClient) { }

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwt');
    return new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem('jwt')}`,
    });
  }

  getAllProducts(): Observable<any> {
    const headers = this.getHeaders();
    return this.http.get(`${this.baseUrl}/products/getAll`, {headers});
  }

  getProductById(productId: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/api/products/${productId}`);
  }

  addProduct(productData: any): Observable<any> {
    const headers = this.getHeaders();
    return this.http.post<any>(`${this.baseUrl}/api/products/insert`, productData, {headers});
  }

  updateProduct(productId: number, productData: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/api/products/${productId}`, productData);
  }

  deleteProduct(productId: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/api/products/${productId}`);
  }
}
