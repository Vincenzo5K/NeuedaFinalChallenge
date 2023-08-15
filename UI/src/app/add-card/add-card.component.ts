import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
@Component({
  selector: 'app-add-card', // Use 'app-edit-card' for the edit component
  templateUrl: './add-card.component.html', // Use 'edit-card.component.html' for the edit component
  styleUrls: ['./add-card.component.css'] // Use 'edit-card.component.css' for the edit component
})
export class AddCardComponent {
  cardForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.cardForm = this.fb.group({
      cardNumber: ['', [Validators.required, Validators.pattern(/^\d{16}$/)]],
      cardholderName: ['', [Validators.required]],
      expirationDate: ['', [Validators.required]],
      // Other form controls for cardholder name, expiration date, etc.
    });
  }

  onSubmit(): void {
    if (this.cardForm.valid) {
      // Process the submitted data (e.g., save to backend)
    }
  }
}
