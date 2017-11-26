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
  selector: 'contract-options-customer',
  templateUrl: './contract-options-customer.component.html'
})
export class ContractOptionsCustomerComponent implements OnInit {

  contractId: number;
  contract: IContract;
  block: boolean = false;
  options: IOption[];
  open: boolean = false;

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
    this.contractId = this.sharedService.getData();
    this.appService.getContractById(this.contractId).then(data => {
      this.contract = data.json() as IContract;
      this.changeBlockStatus(this.contract.status);
      this.appService.getAllFreeOptions(this.contract.number).then(data => {
        this.options = data.json() as IOption[];
      })
    });
  }

  changeBlockStatus(status: string) {
    this.block = status === 'BLOCKED_BY_THE_CUSTOMER' || status === 'BLOCKED_BY_AN_EMPLOYEE';
  }

  onSubmit() {
    let warningCount = this.sharedServiceOptions.getWarningsCount();
    if (warningCount === 0) {
      let options = this.sharedServiceOptions.getData();
      if (options.length > 0) {
        this.appService.updateContractOptionsInBasket(this.contractId, options)
          .then(() => {
            this.init();
            this.sharedServiceOptions.clean();
          });
      } else {
        if (this.contract.optionList.length > 0) {
          this.open = true;
        }
      }
    } else {
      this.init();
    }
  }

  confirmDelete() {
    this.appService.updateContractOptions(this.contractId, this.sharedServiceOptions.getData())
      .then( () => {
        this.open = false;
        //this.sharedService.emitChange(this.contractId);
        this.router.navigate(['customer/contract-options-customer']);
        this.init();
      })
  }
}
