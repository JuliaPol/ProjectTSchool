import {RouterModule, Routes} from "@angular/router";
import {LoginErrorComponent} from "./login-error/login-error.component";
import {NgModule} from "@angular/core";
import {EmployeeComponent} from "./employee/employee.component";
import {ContractFormComponent} from "./employee/contract-form/contract-form.component";
import {CustomersComponent} from "./employee/customers/customers.component";

const appRoutes: Routes =[
  {path: '', redirectTo: 'employee', pathMatch: 'full'},
  {path: 'employee',  component: EmployeeComponent,
    children: [
      {path: '', redirectTo: 'customers', pathMatch: 'full'},
      {path: 'customers', component: CustomersComponent},
      {path: 'contract-form', component: ContractFormComponent}
    ]},
  { path: 'login-error', component: LoginErrorComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
