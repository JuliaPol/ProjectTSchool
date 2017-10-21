import {ICustomer} from "../../interfaces/customers";
import {Component, OnInit} from "@angular/core";
import {AppService} from "../../app.service";
import {Router} from "@angular/router";
import {CustomerSharedService} from "./customer-shared.service";

@Component({
  moduleId: module.id,
  selector: 'customers',
  templateUrl: './customers.component.html'
})
export class CustomersComponent implements OnInit {
  customers: ICustomer[] = [];

  constructor(private appService: AppService,
              private router: Router,
              private sharedService: CustomerSharedService) {

  }

  ngOnInit() {
    this.init()
  }

  init() {
    this.appService.getCustomersList().then(data =>
      this.customers = data.json() as ICustomer[]
    )
  }

  onToggle(customer: ICustomer) {
    customer.selected = !customer.selected;
  }

  addContract() {
    this.router.navigate(['/employee/contract-form']);
  }

  addToSharedService(id) {
    this.sharedService.saveData(id);
  }
}
