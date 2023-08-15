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


const routes: Routes = [
  { path: 'list', component: CardListComponent },
  { path: 'add', component: AddCardComponent },
  { path: 'edit', component: EditCardComponent }
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
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes), 
     
  ],
  exports: [
    RouterModule, 
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
