import {Component, OnInit} from "@angular/core";
import "rxjs/add/operator/map";
import {AppService} from "../app.service";
import {IUser} from "../interfaces/user";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'employee',
  templateUrl: './employee.component.html'
})
export class EmployeeComponent implements OnInit {
  user: IUser = null;
  buttons = [
    {
      name: 'Customers',
      isActive: true,
      path: '/employee/customers',
    },
    {
      name: 'Tariffs',
      isActive: false,
      path: '/employee/tariffs',
    },
    {
      name: 'Options',
      isActive: false,
      path: '/employee/options',
    }
  ];

  constructor(private appService: AppService, private router: Router) {

  }

  ngOnInit() {
    this.appService.getCurrentUser().then(data =>
      this.user = data.json() as IUser
    )
  }

  openTab(button) {
    this.router.navigate([button.path]);
    for (let butt of this.buttons) {
      butt.isActive = false;
    }
    button.isActive = true;
  }
}
