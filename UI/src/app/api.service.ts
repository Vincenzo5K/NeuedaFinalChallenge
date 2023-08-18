import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LowHigh } from './models/LowHigh';
import { ByState } from './models/ByState';
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  apiUrl: string;
 
  // Replace with your API URL
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8'
    })
  };
  constructor(private http: HttpClient) {
    this.apiUrl= 'http://localhost:8080/transactions/';
  }

  getData():any{}
  
  getByState(state:string):Observable<ByState[]>{
    return this.http.get<ByState[]>(this.apiUrl+"CategorySpendByState/"+state);
  }

  getLowHighData(lowerhigher:string): Observable<LowHigh[]> {
    return this.http.get<LowHigh[]>(this.apiUrl+"LowHighTransactionTotals/"+lowerhigher);
  }
}
