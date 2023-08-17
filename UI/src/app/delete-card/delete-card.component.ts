import { Component } from '@angular/core';

@Component({
  selector: 'app-delete-card',
  templateUrl: './delete-card.component.html',
  styleUrls: ['./delete-card.component.css']
})
export class DeleteCardComponent {
  cardNumberToDelete: string = ''; // Property to store the entered card number

  delete(): void {
    if (this.cardNumberToDelete === '') {
      alert('Please enter a card number.');
      return;
    }
    
    // Implement delete card functionality based on this.cardNumberToDelete
  }
}