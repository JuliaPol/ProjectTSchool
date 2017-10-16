import {Component, Input} from "@angular/core";
import {IContract} from "../../interfaces/customers";
import {AppService} from "../../app.service";
import {CustomersComponent} from "../customers/customers.component";

@Component({
  moduleId: module.id,
  selector: 'contract-list',
  templateUrl: './contract-list.component.html'
})
export class ContractListComponent {
  @Input() contractList: IContract[];

  constructor(private appService: AppService,
              private customersComponent: CustomersComponent) {

  }

  getButtonName(item: IContract) {
    if (!item) return;
    if (item.status === 'AVAILABLE') {
      return 'Deactivate';
    } else {
      return 'Activate';
    }
  }

  updateStatus(number) {
    this.appService.updateContractStatus(number)
      .then(() => this.customersComponent.init());
  }
}
