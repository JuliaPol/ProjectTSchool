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
import {CustomerFormComponent} from "./employee/customers/customer-form/customer-form.component";
import {OptionsSharedService} from "./employee/options/options-shared.service";
import {TariffInfoComponent} from "./employee/tariffs/tariff-info/tariff-info.component";
import {TariffSharedService} from "./employee/tariffs/tariff-shared.service";
import {OptionListComponent} from "./employee/tariffs/option-list/option-list.component";

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
    TariffFormComponent,
    CustomerFormComponent,
    TariffInfoComponent,
    OptionListComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule,
    AppRoutingModule,
  ],
  providers: [
    AppService,
    OptionsSharedService,
    TariffSharedService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
