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
  selectedTariff: number;
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
    });
    this.appService.getCustomersList().then(data => {
      this.customers = data.json() as ICustomer[];
    });
    this.numbers.push((new Date().getTime() - 1).toString());
    this.numbers.push((new Date().getTime()).toString());
    this.numbers.push((new Date().getTime() + 1).toString());
  }

  onSubmit() {
    this.contract = {
      id: null,
      rate: this.rates[this.myIndexOf(this.rates.filter((item) => item.id === this.selectedTariff).pop())],
      status: 'AVAILABLE',
      number: this.selectedNumber,
      optionList: [],
    };
    this.appService.createContract(this.customerId, this.contract).then(() =>
    this.result = 'Added');
  }

  rateSelected(id) {
      this.selectedTariff = parseInt(id);
  }
  numberSelected(number) {
    this.selectedNumber = number;
  }

  private myIndexOf(o) {
    if (!o) {
      return;
    }
    for (let i = 0; i < this.rates.length; i++) {
      if (this.rates[i].id === o.id) {
        return i;
      }
    }
    return -1;
  }
}
