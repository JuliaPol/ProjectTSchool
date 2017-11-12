import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {EmployeeComponent} from "./employee/employee.component";
import {LoginErrorComponent} from "./login-error/login-error.component";
import {LoginComponent} from "./login/login.component"
import {AppRoutingModule} from "./app-routing.module";
import {AppService} from "./app.service";
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
import {OptionListSharedService} from "./employee/tariffs/option-list/option-list-shared.service";
import {CustomerSharedService} from "./employee/customers/customer-shared.service";
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import {CustomerContractNumberFiler} from "./employee/customers/customer-contract-number.component";
import {ContractInfoComponent} from "./employee/contract-list/contarct-info/contract-info.component";
import {ContractSharedService} from "./employee/contract-list/contract-shared.service";
import {ContractOptionsComponent} from "./employee/contract-list/contract-options/contract-options.component";
import {SignupComponent} from "./signup/signup.component"
import {NewAccountComponent} from "./signup/new-account/new-account.component"
import {SignupSharedService} from "./signup/signup-shared.service";
import {ClarityModule} from "clarity-angular";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent,
    LoginComponent,
    LoginErrorComponent,
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
    CustomerContractNumberFiler,
    ContractInfoComponent,
    ContractOptionsComponent,
    SignupComponent,
    NewAccountComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule,
    AppRoutingModule,
    ClarityModule.forRoot(),
    BrowserAnimationsModule
  ],
  providers: [
    AppService,
    OptionsSharedService,
    TariffSharedService,
    OptionListSharedService,
    CustomerSharedService,
    ContractSharedService,
    SignupSharedService,
    {provide: LocationStrategy, useClass: HashLocationStrategy}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
