import {Component, OnInit} from "@angular/core";
import "rxjs/add/operator/map";
import {Router} from "@angular/router";
import {AppService} from "../../app.service";
import {ICustomer} from "../../interfaces/customers";

@Component({
  moduleId: module.id,
  selector: 'contract',
  templateUrl: './customer-contract.component.html'
})
export class CustomerContractComponent implements OnInit {

  user: ICustomer = null;
  status: string = '';

  constructor(private appService: AppService, private router: Router) {
  }

  ngOnInit() {
    this.init();
  }

  init() {
    this.appService.getCurrentUser().then(data =>
      this.user = data.json() as ICustomer
    )
  }

  changeStatus(id: number) {
    this.appService.changeContractByCustomer(id).then(() => this.init())
  }
}
