import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from './models/customer';
@Injectable({
  providedIn: 'root'
})
export class CustomerApiService {
  apiUrl: string;
 
  // Replace with your API URL
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8'
    })
  };
  constructor(private http: HttpClient) {
    this.apiUrl= 'http://localhost:8080/customers';
  }

  getCustomerData(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.apiUrl);
  }
}