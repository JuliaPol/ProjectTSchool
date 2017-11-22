import {Component, OnInit} from "@angular/core";
import "rxjs/add/operator/map";
import {Router} from "@angular/router";
import {AppService} from "../../app.service";
import {IContract, ICustomer} from "../../interfaces/customers";
import {CustomerContractSharedService} from "../customer-contract-shared.service";
import {OptionListSharedService} from "../../employee/tariffs/option-list/option-list-shared.service";
import {IOption} from "../../interfaces/options";

@Component({
  moduleId: module.id,
  selector: 'basket',
  templateUrl: './basket.component.html'
})
export class BasketComponent implements OnInit {

  contractId: number;
  contract: IContract;
  options: IOption[];
  emptyBasket: boolean = false;
  load: boolean = false;

  constructor(private appService: AppService, private router: Router,
              private sharedService: CustomerContractSharedService,
              private sharedServiceOptions: OptionListSharedService) {
    sharedService.changeEmitted$.subscribe(
      () => this.init());
  }

  ngOnInit() {
    this.init();
  }

  init() {
    this.emptyBasket = false;
    this.contractId = this.sharedService.getData();
    this.appService.getContractById(this.contractId).then(data => {
      this.contract = data.json() as IContract;
    });
    this.appService.getAllOptionsInBasket(this.contractId).then(data => {
      this.options = data.json() as IOption[];
      if (this.options.length === 0)
        this.emptyBasket = true;
      else
        this.load = true;
    });
  }

  onSubmit() {
    let warningCount = this.sharedServiceOptions.getWarningsCount();
    if (warningCount === 0) {
      this.appService.updateContractOptions(this.contractId, this.sharedServiceOptions.getData()).then(() => {
        this.appService.clearBasket(this.contractId).then(() => {
          this.init();
          this.sharedServiceOptions.clean();
        });
      });
    } else {
      this.init();
    }
  }
}
