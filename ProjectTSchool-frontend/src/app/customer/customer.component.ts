import {Component, OnInit, ViewChild} from "@angular/core";
import "rxjs/add/operator/map";
import {AppService} from "../app.service";
import {IUser} from "../interfaces/user";
import {Router} from "@angular/router";
import {IContract, ICustomer} from "../interfaces/customers";
import {CustomerContractSharedService} from "./customer-contract-shared.service";
import {ContractTariffComponent} from "./contract-tariff/contract-tariff.component";

@Component({
  moduleId: module.id,
  selector: 'customer',
  templateUrl: './customer.component.html'
})
export class CustomerComponent implements OnInit {

  user: ICustomer = null;
  selectedContract: IContract;
  //hl@ViewChild(ContractTariffComponent) tariffForm: ContractTariffComponent;

  constructor(private appService: AppService,
              private router: Router,
              private sharedService: CustomerContractSharedService) {
  }

  ngOnInit() {
    this.init();
  }

  init() {
    this.appService.getCurrentUser().then(data => {
        this.user = data.json() as ICustomer;
        this.sharedService.saveData(this.user.contractList[0].id);
        this.selectedContract = this.user.contractList[0];
      }
    ).catch(res => this.router.navigate(['/login']))
  }


  signOut(link: string) {
    // window.location.href = link;
    this.appService.signOut().then(() => this.router.navigate(['/login']))
  }

  change(id: number) {
    this.sharedService.saveData(id);
    this.sharedService.emitChange(id);
    this.selectedContract = this.user.contractList
      .find(x => x.id === id);
    //this.tariffForm.init();
  }
}
