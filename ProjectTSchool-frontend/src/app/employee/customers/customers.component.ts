import {ICustomer} from "../../interfaces/customers";
import {Component, OnInit} from "@angular/core";
import {AppService} from "../../app.service";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'customers',
  templateUrl: './customers.component.html'
})
export class CustomersComponent implements OnInit {
  customers: ICustomer[] = [];

  constructor(private appService: AppService, private router: Router) {

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


  openTab(link: string) {
    this.router.navigate([link]);
  }
}
