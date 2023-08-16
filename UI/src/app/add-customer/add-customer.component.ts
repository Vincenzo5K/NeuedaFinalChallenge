import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {
  customerForm: FormGroup;

  constructor(private fb: FormBuilder) {
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
      // Process the submitted data (e.g., save to backend)
      console.log(formData);
    }
  }
}
