import {Component, OnInit} from "@angular/core";
import "rxjs/add/operator/map";
import {AppService} from "../app.service";
import {IUser} from "../interfaces/user";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'customer',
  templateUrl: './customer.component.html'
})
export class CustomerComponent implements OnInit {

  user: IUser = null;

  constructor(private appService: AppService, private router: Router) {
  }

  ngOnInit() {
    this.init();
  }

  init() {
    this.appService.getCurrentUser().then(data =>
      this.user = data.json() as IUser
    )
  }
}
