import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.css']
})
export class CardDetailsComponent {
  @Input() card: any; // Input property to receive the selected card's data

  constructor() { }

  editCard(): void {
    // Implement edit card functionality
  }

  deleteCard(): void {
    // Implement delete card functionality
  }
}
