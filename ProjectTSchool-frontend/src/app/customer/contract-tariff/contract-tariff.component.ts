import {Component, Input, OnInit} from "@angular/core";
import "rxjs/add/operator/map";
import {Router} from "@angular/router";
import {AppService} from "../../app.service";
import {IContract, ICustomer} from "../../interfaces/customers";
import {CustomerContractSharedService} from "../customer-contract-shared.service";
import {ITariff} from "../../interfaces/tariff";

@Component({
  moduleId: module.id,
  selector: 'contract-tariff',
  templateUrl: './contract-tariff.component.html'
})
export class ContractTariffComponent implements OnInit {

  selectedTariffId: number;
  selectedTariff: ITariff;
  selectedStatus: string;
  contractId: number;
  contract: IContract;
  rates: ITariff[] = [];
  allTariffsActive: boolean = false;
  yourTariffActive: boolean = true;

  constructor(private appService: AppService, private router: Router,
              private sharedService: CustomerContractSharedService) {
    sharedService.changeEmitted$.subscribe(
      () => this.init());
  }

  ngOnInit() {
    this.init();
  }

  init() {
    this.contractId = this.sharedService.getData();
    this.appService.getContractById(this.contractId).then(data => {
      this.contract = data.json() as IContract;
    }).then(() => this.appService.getAllTariffs().then(data => {
      this.rates = data.json() as ITariff[];
    }).then(() => {
      this.selectedTariffId = this.contract.rate.id;
      this.rates.filter((item) => item.id === this.selectedTariffId).pop();
    }));
  }

  showAllRates() {
    this.yourTariffActive = false;
    this.allTariffsActive = true;
  }

  changeRate(id: number) {
    this.selectedTariff = this.rates
      .find(x => x.id === id);
    this.contract.rate = this.selectedTariff;
    this.appService.updateContract(this.contract).then(() => {
      this.appService.getContractById(this.contractId).then(data => this.contract = data.json() as IContract);
    });
  }

}
