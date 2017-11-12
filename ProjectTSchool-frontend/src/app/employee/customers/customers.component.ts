import {IContract, ICustomer} from "../../interfaces/customers";
import {Component, OnInit} from "@angular/core";
import {AppService} from "../../app.service";
import {Router} from "@angular/router";
import {CustomerSharedService} from "./customer-shared.service";
import {CustomerContractNumberFiler} from "./customer-contract-number.component";
import {ContractSharedService} from "../contract-list/contract-shared.service";

@Component({
  moduleId: module.id,
  selector: 'customers',
  templateUrl: './customers.component.html'
})
export class CustomersComponent implements OnInit {
  customers: ICustomer[] = [];
  contratNumber: number;

  constructor(private appService: AppService,
              private router: Router,
              private sharedService: CustomerSharedService,
              private contractSaredService: ContractSharedService) {

  }

  ngOnInit() {
    this.init()
  }

  init() {
    this.appService.getCustomersList().then(data =>
      this.customers = data.json() as ICustomer[]
    )
  }

  onToggle(customer: ICustomer) {
    customer.selected = !customer.selected;
  }

  addContract() {
    this.router.navigate(['/employee/contract-form']);
  }

  addToSharedService(id) {
    this.sharedService.saveData(id);
  }

  changeContract(contract: IContract) {
    this.contractSaredService.saveData(contract.id);
    this.router.navigate(['/employee/contract-info']);
  }
}
