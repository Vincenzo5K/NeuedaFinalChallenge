import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from './models/customer';
@Injectable({
  providedIn: 'root'
})
export class CustomerApiService {
  apiUrl= 'http://localhost:8080/customers';
  // Replace with your API URL
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8'
    })
  };
  constructor(private http: HttpClient) {
    
  }

  getCustomerData(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.apiUrl);
  }
}