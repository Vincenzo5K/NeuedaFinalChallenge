import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  // customers: any[] = [ { id: 1, firstName: 'John', lastName: 'Doe', gender: 'Male', job: 'Engineer', dateOfBirth: '1990-01-15' },
  // { id: 2, firstName: 'Jane', lastName: 'Smith', gender: 'Female', job: 'Designer', dateOfBirth: '1985-08-22' },]; // Replace with actual customer data

  // constructor() { } 

  customers: any[]=[];

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.apiService.getData().subscribe(data => {
      this.customers = data;
    });
  }
}


