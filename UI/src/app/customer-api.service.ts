import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, Observable } from 'rxjs';
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
    return this.http.get<Customer[]>(this.apiUrl)
  }

  addCustomer(customer:Customer): Observable<Customer> {
    return this.http.post<Customer>(this.apiUrl, customer)
  }

  deleteCustomer(id: string): Observable<boolean>{
    return this.http.delete<boolean>(this.apiUrl+"/"+ id)
  }
}