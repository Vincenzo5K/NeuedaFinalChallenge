import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-card',
  templateUrl: './add-card.component.html',
  styleUrls: ['./add-card.component.css']
})
export class AddCardComponent  {
  cardForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.cardForm = this.fb.group({
      cardNumber: ['', [Validators.required, Validators.pattern(/^\d{16}$/)]],
      cardholderName: ['', Validators.required],
      expirationDate: ['', Validators.required]
    });
  }


  onSubmit(): void {
    if (this.cardForm.valid) {
      const formData = this.cardForm.value;
      // Process the submitted data (e.g., save to backend)
      console.log(formData);
    }
  }
}

