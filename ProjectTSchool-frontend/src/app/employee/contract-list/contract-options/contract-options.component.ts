import {Component, OnInit} from "@angular/core";
import {AppService} from "../../../app.service";
import {ContractSharedService} from "../contract-shared.service";
import {IContract} from "../../../interfaces/customers";
import {OptionListSharedService} from "../../tariffs/option-list/option-list-shared.service";

@Component({
  moduleId: module.id,
  selector: 'contract-options',
  templateUrl: './contract-options.component.html'
})
export class ContractOptionsComponent implements OnInit {
  contractId: number;
  contract: IContract;
  result: string;

  constructor(private appService: AppService,
              private sharedService: ContractSharedService,
              private sharedServiceOptions: OptionListSharedService) {

  }

  ngOnInit() {
    this.init();
  }

  init() {
    this.contractId = this.sharedService.getData();
    this.appService.getContractById(this.contractId).then(data =>  {
      this.contract = data.json() as IContract;
    })
  }

  onSubmit() {
    this.appService.updateContractOptions(this.contractId, this.sharedServiceOptions.getData()).then(() => {
      this.init();
      this.result = 'Changed';
      this.sharedServiceOptions.clean();
    });
  }
}
