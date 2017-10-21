import {Component, OnInit} from "@angular/core";
import {ITariff} from "../../interfaces/tariff";
import {AppService} from "../../app.service";
import {IContract, ICustomer} from "../../interfaces/customers";
import {CustomerSharedService} from "../customers/customer-shared.service";

@Component({
  moduleId: module.id,
  selector: 'contract-form',
  templateUrl: './contract-form.component.html'
})
export class ContractFormComponent implements OnInit {

  rates: ITariff[] = [];
  customers: ICustomer[] = [];
  numbers: any[] = [];
  result: string = '';
  selectedTariff: ITariff;
  customerId: number;
  contract: IContract;
  selectedNumber: string;

  constructor(private appService: AppService, private sharedService: CustomerSharedService) {

  }

  ngOnInit() {
    this.init();
  }

  init() {
    this.customerId = this.sharedService.getData();
    this.appService.getAllTariffs().then(data => {
      this.rates = data.json() as ITariff[];
      this.selectedTariff = this.rates[0];
    });
    this.appService.getCustomersList().then(data => {
      this.customers = data.json() as ICustomer[];
    });
    this.numbers.push(new Date().getTime() - 1);
    this.numbers.push(new Date().getTime());
    this.numbers.push(new Date().getTime() + 1);
  }

  onSubmit() {
    this.contract = {
      rate: this.selectedTariff,
      status: 'AVAILABLE',
      number: this.selectedNumber,
    };
    this.appService.createContract(this.customerId, this.contract).then(() =>
    this.result = 'Added');
  }
}
