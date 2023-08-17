import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from './models/customer';
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  apiUrl:string;
  // Replace with your API URL

  constructor(private http: HttpClient) {
    this.apiUrl = 'http://localhost:8080/customers';
  }

  getCustomerData(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.apiUrl);
  }
}