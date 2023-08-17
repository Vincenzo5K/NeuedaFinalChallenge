import { Component, OnInit } from '@angular/core';
import { CustomerApiService } from '../customer-api.service';
import { Customer } from '../models/customer';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  // customers: any[] = [ { id: 1, firstName: 'John', lastName: 'Doe', gender: 'Male', job: 'Engineer', dateOfBirth: '1990-01-15' },
  // { id: 2, firstName: 'Jane', lastName: 'Smith', gender: 'Female', job: 'Designer', dateOfBirth: '1985-08-22' },]; // Replace with actual customer data

  // constructor() { } 


  customers$!: Observable<Customer[]>;

  // customers: any[]=[];


  constructor(private customerService: CustomerApiService, private myrouter:Router) {}

  ngOnInit() {
    // this.customerService.getCustomerData().subscribe(data => {
    //   console.warn(typeof data)
    //   this.customers$ =data;
    // });
    this.customers$ = this.customerService.getCustomerData();
    console.warn(this.customers$)  
}


}