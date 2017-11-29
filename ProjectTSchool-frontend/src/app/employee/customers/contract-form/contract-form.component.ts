import {Component, EventEmitter, OnInit, Output} from "@angular/core";
import {Router} from "@angular/router";
import {isNullOrUndefined} from "util";
import {ITariff} from "../../../interfaces/tariff";
import {IContract} from "../../../interfaces/customers";
import {AppService} from "../../../app.service";
import {CustomerSharedService} from "../customer-shared.service";

@Component({
  moduleId: module.id,
  selector: 'contract-form',
  templateUrl: './contract-form.component.html'
})
export class ContractFormComponent implements OnInit {

  rates: ITariff[] = [];
  //customers: ICustomer[] = [];
  numbers: any[] = [];
  result: string = '';
  selectedTariff: number;
  customerId: number;
  contract: IContract;
  selectedNumber: string;
  open: boolean = false;
  @Output() onChanged = new EventEmitter<boolean>();

  constructor(private appService: AppService,
              private sharedService: CustomerSharedService, private router: Router) {

  }

  ngOnInit() {
    this.init();
  }

  reset() {
    this.customerId = 0;
    this.selectedNumber = null;
    this.selectedTariff = null;
    this.rates = [];
    this.numbers = [];
    this.open = false;
  }

  init() {
    this.reset();
    this.customerId = this.sharedService.getData();
    this.appService.getAllTariffs().then(data => {
      this.rates = data.json() as ITariff[];
    });
    // this.appService.getCustomersList().then(data => {
    //   this.customers = data.json() as ICustomer[];
    // });
    this.numbers.push((new Date().getTime() - 1).toString());
    this.numbers.push((new Date().getTime()).toString());
    this.numbers.push((new Date().getTime() + 1).toString());
  }

  onSubmit() {
    if (isNullOrUndefined(this.selectedTariff))
      this.selectedTariff = this.rates[0].id;
    if (isNullOrUndefined(this.selectedNumber))
      this.selectedNumber = this.numbers[0];
    this.contract = {
      id: null,
      creationDate: null,
      rate: this.rates[this.myIndexOf(this.rates.filter((item) => item.id === this.selectedTariff).pop())],
      status: 'AVAILABLE',
      number: this.selectedNumber,
      optionList: [],
    };
    this.appService.createContract(this.customerId, this.contract).then(() => {
        this.reset();
        this.onChanged.emit();
      }
    )
  }

  rateSelected(id) {
    if (id === "")
      this.selectedTariff = this.rates[0].id;
    else
      this.selectedTariff = parseInt(id);
  }

  numberSelected(number) {
    if (number === "")
      this.selectedNumber = this.numbers[0];
    else
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
