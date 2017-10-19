import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {EmployeeComponent} from "./employee/employee.component";
import {LoginErrorComponent} from "./login-error/login-error.component";
import {AppRoutingModule} from "./app-routing.module";
import {AppService} from "./app.service";
import {ContractListComponent} from "./employee/contract-list/contract-list.component";
import {ContractFormComponent} from "./employee/contract-form/contract-form.component";
import {CustomersComponent} from "./employee/customers/customers.component";
import {OptionsComponent} from "./employee/options/options.component";
import {TariffsComponent} from "./employee/tariffs/tariffs.component";
import {OptionsRulesComponent} from "./employee/options/options-rules/options-rules.component";
import {OptionFormComponent} from "./employee/options/option-form/option-form.component";
import {OptionInfoComponent} from "./employee/options/option-info/option-info.component";
import {TariffFormComponent} from "./employee/tariffs/tariff-form/tariff-form.component";

@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent,
    LoginErrorComponent,
    ContractListComponent,
    ContractFormComponent,
    CustomersComponent,
    OptionsComponent,
    TariffsComponent,
    OptionsRulesComponent,
    OptionFormComponent,
    OptionInfoComponent,
    TariffFormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule,
    AppRoutingModule,
  ],
  providers: [AppService],
  bootstrap: [AppComponent]
})
export class AppModule { }
