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

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

const routes: Routes = [
  { path: 'list', component: CardListComponent },
  { path: 'add', component: AddCardComponent },
  { path: 'edit', component: EditCardComponent },
  { path: 'delete', component: DeleteCardComponent },
  { path: 'customer', component: CustomersComponent },
  { path: 'transaction', component: TransactionsComponent }

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
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes), 
    FormsModule,
     
  ],
  exports: [
    RouterModule, 
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
