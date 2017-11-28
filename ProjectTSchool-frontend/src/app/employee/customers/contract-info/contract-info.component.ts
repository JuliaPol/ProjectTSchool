import {Component, OnInit} from "@angular/core";
import {IContract} from "../../../interfaces/customers";
import {AppService} from "../../../app.service";
import {ITariff} from "../../../interfaces/tariff";
import {ContractSharedService} from "../contract-shared.service";

@Component({
  moduleId: module.id,
  selector: 'contract-info',
  templateUrl: './contract-info.component.html'
})
export class ContractInfoComponent implements OnInit {
  selectedTariffId: number;
  selectedTariff: ITariff;
  selectedStatus: string;
  contractId: number;
  contract: IContract;
  rates: ITariff[] = [];
  result: string;

  constructor(private appService: AppService,
              private sharedService: ContractSharedService) {

  }

  ngOnInit() {
    this.init();
  }

  init() {
    this.contractId = this.sharedService.getData();
    this.appService.getContractById(this.contractId).then(data => {
      this.contract = data.json() as IContract;
    }).then(() => {
      this.setTariff();
    });
    this.appService.getAllTariffs().then(data => {
      this.rates = data.json() as ITariff[];
    });
  }

  setTariff() {
    this.selectedTariff = this.contract.rate;
    this.selectedTariffId = this.contract.rate.id;
    this.selectedStatus = this.contract.status;
  }

  statusSelected(status) {
    this.selectedStatus = status;
  }

  rateSelected(id) {
    this.selectedTariffId = parseInt(id);
    this.selectedTariff = this.rates[this.myIndexOf(this.rates
      .filter((item) => item.id === this.selectedTariffId).pop())];
  }

  onSubmit() {
    this.contract.rate = this.selectedTariff;
    this.contract.status = this.selectedStatus;
    this.appService.updateContract(this.contract).then(() => {
      this.appService.getContractById(this.contractId).then(data => this.contract = data.json() as IContract);
      this.result = 'Changed';
    });
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
