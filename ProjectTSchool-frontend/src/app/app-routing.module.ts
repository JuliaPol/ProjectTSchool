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
import {CustomerFormComponent} from "./employee/customers/customer-form/customer-form.component";
import {TariffInfoComponent} from "./employee/tariffs/tariff-info/tariff-info.component";

const appRoutes: Routes =[
  {path: '', redirectTo: 'employee', pathMatch: 'full'},
  {path: 'employee',  component: EmployeeComponent,
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
      {path: 'customer-form', component: CustomerFormComponent}
    ]},
  { path: 'login-error', component: LoginErrorComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
