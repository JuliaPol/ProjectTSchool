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

  open: boolean = false;
  selectedTariffId: number;
  selectedTariff: ITariff;
  selectedStatus: string;
  contractId: number;
  contract: IContract;
  rates: ITariff[] = [];
  allTariffsActive: boolean = false;
  yourTariffActive: boolean = true;
  block: boolean = false;

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
      this.changeBlockStatus(this.contract.status);
    }).then(() => this.appService.getAllTariffs().then(data => {
      this.rates = data.json() as ITariff[];
    }).then(() => {
      this.selectedTariffId = this.contract.rate.id;
      this.rates = this.rates.filter((item) => item.id !== this.selectedTariffId);
    }));
  }

  showAllRates() {
    this.yourTariffActive = false;
    this.allTariffsActive = true;
  }

  changeRate(id: number) {
    this.selectedTariff = this.rates
      .find(x => x.id === id);
    this.open = true;
  }

  changeBlockStatus(status: string) {
    this.block = status === 'BLOCKED_BY_THE_CUSTOMER' || status === 'BLOCKED_BY_AN_EMPLOYEE';
  }

  onSubmit() {
    this.contract.rate = this.selectedTariff;
    this.appService.updateContract(this.contract).then(() => {
      this.init();
      this.open = false;
    });
  }

}
