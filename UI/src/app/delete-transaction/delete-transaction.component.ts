import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-delete-transaction',
  templateUrl: './delete-transaction.component.html',
  styleUrls: ['./delete-transaction.component.css']
})
export class DeleteTransactionComponent implements OnInit {
  transactionForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.transactionForm = this.fb.group({
      transactionId: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  onDelete(): void {
    if (this.transactionForm.valid) {
      const transactionId = this.transactionForm.value.transactionId;
      // Process the deletion logic (e.g., delete from backend)
      console.log(`Deleting transaction with ID: ${transactionId}`);
    }
  }
}
