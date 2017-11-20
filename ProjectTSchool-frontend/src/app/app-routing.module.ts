import {RouterModule, Routes} from "@angular/router";
import {LoginErrorComponent} from "./login-error/login-error.component";
import {NgModule} from "@angular/core";
import {EmployeeComponent} from "./employee/employee.component";
import {ContractFormComponent} from "./employee/contract-form/contract-form.component";
import {CustomersComponent} from "./employee/customers/customers.component";
import {OptionsComponent} from "./employee/options/options.component";
import {TariffsComponent} from "./employee/tariffs/tariffs.component";
import {OptionsRulesComponent} from "./employee/options/options-rules/options-rules.component";
import {OptionFormComponent} from "./employee/options/option-form/option-form.component";
import {OptionInfoComponent} from "./employee/options/option-info/option-info.component";
import {TariffFormComponent} from "./employee/tariffs/tariff-form/tariff-form.component";
import {TariffInfoComponent} from "./employee/tariffs/tariff-info/tariff-info.component";
import {ContractInfoComponent} from "./employee/contract-list/contarct-info/contract-info.component";
import {ContractOptionsComponent} from "./employee/contract-list/contract-options/contract-options.component";
import {LoginComponent} from "./login/login.component";
import {SignupComponent} from "./signup/signup.component";
import {CustomerComponent} from "./customer/customer.component";
import {CustomerContractComponent} from "./customer/customer-contract/customer-contract.component";

const appRoutes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {
    path: 'employee', component: EmployeeComponent,
    children: [
      {path: '', redirectTo: 'customers', pathMatch: 'full'},
      {path: 'customers', component: CustomersComponent},
      {path: 'contract-form', component: ContractFormComponent},
      {path: 'options', component: OptionsComponent},
      {path: 'tariffs', component: TariffsComponent},
      {path: 'tariff-form', component: TariffFormComponent},
      {path: 'tariff-info', component: TariffInfoComponent},
      {path: 'options-rules', component: OptionsRulesComponent},
      {path: 'option-form', component: OptionFormComponent},
      {path: 'option-info', component: OptionInfoComponent},
      {path: 'contract-info', component: ContractInfoComponent},
      {path: 'contract-options', component: ContractOptionsComponent},
    ]
  },
  {path: 'login-error', component: LoginErrorComponent},
  {path: 'login', component: LoginComponent},
  {path: 'signUp', component: SignupComponent},
  {
    path: 'customer', component: CustomerComponent,
    children: [
      {path: '', redirectTo: 'contract', pathMatch: 'full'},
      {path: 'contract', component: CustomerContractComponent}
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
