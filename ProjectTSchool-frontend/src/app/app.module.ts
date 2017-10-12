import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";

const appRoutes: Routes =[
  { path: 'login', component: LoginComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(
      appRoutes
    )
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
