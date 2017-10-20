import {Component} from "@angular/core";
import {AppService} from "../../../app.service";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'customer-form',
  templateUrl: './customer-form.component.html'
})
export class CustomerFormComponent {
  customer: any = {};
  result: string = '';

  constructor(private appService: AppService) {

  }

  onSubmit() {
    console.log(this.customer.address.street);
    this.appService.createUser(this.customer).then(() => this.result = 'Added');
  }
}
