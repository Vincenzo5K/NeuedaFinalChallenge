import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-transaction',
  templateUrl: './add-transaction.component.html',
  styleUrls: ['./add-transaction.component.css']
})
export class AddTransactionComponent implements OnInit {
  transactionForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.transactionForm = this.fb.group({
      transactionId: ['', Validators.required],
      transactionDateTime: ['', Validators.required],
      amount: ['', Validators.required],
      customerId: ['', Validators.required],
      merchant: [''],
      category: [''],
      city: [''],
      state: [''],
      cityPopulation: ['']
    });
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    if (this.transactionForm.valid) {
      const formData = this.transactionForm.value;
      // Process the submitted data (e.g., save to backend)
      console.log(formData);
    }
  }
}
