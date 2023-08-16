import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CardListComponent } from './card-list/card-list.component';
import { CardDetailsComponent } from './card-details/card-details.component';
import { AddCardComponent } from './add-card/add-card.component';
import { EditCardComponent } from './edit-card/edit-card.component';
import { NavbarComponent } from './navbar/navbar.component';
import { RouterModule, Routes } from '@angular/router';
import { DeleteCardComponent } from './delete-card/delete-card.component';
import { CustomersComponent } from './customers/customers.component';
import { TransactionsComponent } from './transactions/transactions.component';
import {HttpClientModule} from '@angular/common/http';
import { AddCustomerComponent } from './add-customer/add-customer.component';
import { DeleteCustomerComponent } from './delete-customer/delete-customer.component';
import { AddTransactionComponent } from './add-transaction/add-transaction.component';
import { DeleteTransactionComponent } from './delete-transaction/delete-transaction.component';
import { SpendingHistoryComponent } from './spending-history/spending-history.component'
import { NgChartsModule } from 'ng2-charts';


const routes: Routes = [
  { path: 'card-list', component: CardListComponent },
  { path: 'add-card', component: AddCardComponent },
  { path: 'edit', component: EditCardComponent },
  { path: 'delete-card', component: DeleteCardComponent },
  { path: 'customer-list', component: CustomersComponent },
  { path: 'transaction', component: TransactionsComponent },
  { path: 'add-customer', component: AddCustomerComponent },
  { path: 'delete-customer', component: DeleteCustomerComponent },
  { path: 'add-transaction', component: AddTransactionComponent },
  { path: 'delete-transaction', component: DeleteTransactionComponent },
  { path: 'spending-history', component: SpendingHistoryComponent }
];
@NgModule({
  declarations: [
    AppComponent,
    CardListComponent,
    CardDetailsComponent,
    AddCardComponent,
    EditCardComponent,
    AppComponent,
    NavbarComponent,
    DeleteCardComponent,
    CustomersComponent,
    TransactionsComponent,
    AddCustomerComponent,
    DeleteCustomerComponent,
    AddTransactionComponent,
    DeleteTransactionComponent,
    SpendingHistoryComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes), 
    FormsModule,
    HttpClientModule,
    NgChartsModule,
  ],
  exports: [
    RouterModule, 
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
