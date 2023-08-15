import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-card-list',
  templateUrl: './card-list.component.html',
  styleUrls: ['./card-list.component.css']
})
export class CardListComponent {
  creditCards: any[] = [{ cardNumber: '**** **** **** 1234', cardholderName: 'John Doe', expirationDate: '12/23' },
  { cardNumber: '**** **** **** 1234', cardholderName: 'John Doe', expirationDate: '12/23' },
  { cardNumber: '**** **** **** 5678', cardholderName: 'Jane Smith', expirationDate: '06/25' }]; // Replace with your actual data structure

  constructor() { }
}

