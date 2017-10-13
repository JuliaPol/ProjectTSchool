import {RouterModule, Routes} from "@angular/router";
import {LoginErrorComponent} from "./login-error/login-error.component";
import {NgModule} from "@angular/core";
import {EmployeeComponent} from "./employee/employee.component";

const appRoutes: Routes =[
  { path: 'employee', component: EmployeeComponent},
  { path: 'login-error', component: LoginErrorComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
