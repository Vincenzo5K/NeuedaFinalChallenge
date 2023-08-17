import { Component, Directive, Inject, Input, OnInit } from '@angular/core';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {
  // transactions: any[] = [   { trans_date_trans_time: '2023-08-15 08:30:00', amt: 100.00, trans_num: 'T12345', customer_id: 1, city: 'New York', state: 'NY', city_population: 8000000, merchant: 'ABC Mart' },
  // { trans_date_trans_time: '2023-08-16 15:45:00', amt: 50.00, trans_num: 'T12346', customer_id: 2, city: 'Los Angeles', state: 'CA', city_population: 4000000, merchant: 'XYZ Store' },]; // Replace with actual transaction data

  // constructor() { }
  transactions: any[]=[];

  
  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.apiService.getData().subscribe(data => {
      this.transactions = data;
      
    });
  }

  
  

}
