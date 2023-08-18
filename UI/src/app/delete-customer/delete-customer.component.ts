import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomerApiService } from '../customer-api.service';
@Component({
  selector: 'app-delete-customer',
  templateUrl: './delete-customer.component.html',
  styleUrls: ['./delete-customer.component.css']
})
export class DeleteCustomerComponent implements OnInit {
  customerForm: FormGroup;

  constructor(private customerService: CustomerApiService, private fb: FormBuilder) {
    this.customerForm = this.fb.group({
      customerId: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    if (this.customerForm.valid) {
      const customerId = this.customerForm.value.customerId;
      this.customerService.deleteCustomer(customerId).subscribe((response) => {
        console.log('Response:', response);
        // Handle the response here
      },
      (error) => {
        console.error('Error:', error);
        // Handle the error here
      });
      // Process the deletion logic (e.g., delete from backend)
      console.log(`Deleting customer with ID: ${customerId}`);
    }
  }
}
