import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-delete-customer',
  templateUrl: './delete-customer.component.html',
  styleUrls: ['./delete-customer.component.css']
})
export class DeleteCustomerComponent implements OnInit {
  customerForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.customerForm = this.fb.group({
      customerId: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  onDelete(): void {
    if (this.customerForm.valid) {
      const customerId = this.customerForm.value.customerId;
      // Process the deletion logic (e.g., delete from backend)
      console.log(`Deleting customer with ID: ${customerId}`);
    }
  }
}
