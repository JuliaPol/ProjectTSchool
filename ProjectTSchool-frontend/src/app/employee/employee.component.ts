import {Component, OnInit} from "@angular/core";
import "rxjs/add/operator/map";
import {AppService} from "../app.service";
import {IUser} from "../interfaces/user";

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
      isActive: true
    },
    {
      name: 'Tariffs',
      isActive: false,
    },
    {
      name: 'Options',
      isActive: false,
    }
  ];

  constructor(private appService: AppService) {

  }

  ngOnInit() {
    this.appService.getCurrentUser().then(data =>
      this.user = data.json() as IUser
    )
  }
}
