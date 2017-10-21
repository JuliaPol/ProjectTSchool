import {Component} from "@angular/core";
import {AppService} from "../../../app.service";
import {Router} from "@angular/router";
import {ICustomer} from "../../../interfaces/customers";

@Component({
  moduleId: module.id,
  selector: 'customer-form',
  templateUrl: './customer-form.component.html'
})
export class CustomerFormComponent {
  customer: any = {};
  address: any = {};
  result: string = '';

  constructor(private appService: AppService) {

  }

  onSubmit() {
    this.customer.address = this.address;
    this.appService.createUser(this.customer).then(() => this.result = 'Added');
  }
}
