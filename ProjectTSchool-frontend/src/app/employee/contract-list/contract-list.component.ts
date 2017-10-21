import {Component, Input} from "@angular/core";
import {IContract} from "../../interfaces/customers";
import {ContractSharedService} from "./contract-shared.service";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'contract-list',
  templateUrl: './contract-list.component.html'
})
export class ContractListComponent {
  @Input() contractList: IContract[];

  constructor(private sharedService: ContractSharedService,
              private router: Router) {

  }

  changeContract(contract: IContract) {
    this.sharedService.saveData(contract.id);
    this.router.navigate(['/employee/contract-info']);
  }
}
