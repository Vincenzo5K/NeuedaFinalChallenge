import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomerApiService } from '../customer-api.service';
import { Customer } from '../models/customer';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {
  customerForm: FormGroup;
  actionType!: string;
  id!: string;

  constructor(private customerService: CustomerApiService, private fb: FormBuilder,
    private avRoute: ActivatedRoute, private router: Router) {
      const idParam = 'id';
      this.actionType = 'Add';
      this.id = '';

    this.customerForm = this.fb.group({
      customerId: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      gender: ['male'],
      job: [''],
      dateOfBirth: ['']
    });
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    if (this.customerForm.valid) {
      const formData = this.customerForm.value;
      if(this.customerForm.dirty && this.customerForm.valid){
        alert("All good");
      }

      if(this.actionType === 'Add'){
        let customer: Customer = {
          id: "1000",
          customerId: this.customerForm.get('customerId')!.value,
          first: this.customerForm.get('firstName')!.value,
          last: this.customerForm.get('lastName')!.value,
          gender: this.customerForm.get('gender')!.value,
          job: this.customerForm.get('job')!.value,
          dob: this.customerForm.get('dateOfBirth')!.value,
        };
        this.customerService.addCustomer(customer).subscribe((response) => {
          console.log('Response:', response);
          // Handle the response here
        },
        (error) => {
          console.error('Error:', error);
          // Handle the error here
        });
      }
      // Process the submitted data (e.g., save to backend)
      console.log(formData);
    }
  }
}
