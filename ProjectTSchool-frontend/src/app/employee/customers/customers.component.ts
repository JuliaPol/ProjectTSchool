import {IContract, ICustomer} from "../../interfaces/customers";
import {Component, OnInit, ViewChild} from "@angular/core";
import {AppService} from "../../app.service";
import {Router} from "@angular/router";
import {CustomerSharedService} from "./customer-shared.service";
import {ContractSharedService} from "./contract-shared.service";
import {Wizard} from "clarity-angular";
import {ContractFormComponent} from "./contract-form/contract-form.component";

@Component({
  moduleId: module.id,
  selector: 'customers',
  templateUrl: './customers.component.html'
})
export class CustomersComponent implements OnInit {
  customers: ICustomer[] = [];
  contratNumber: number;
  newCustomers: ICustomer[] = [];
  countCustomer: number;

  constructor(private appService: AppService,
              private router: Router,
              private sharedService: CustomerSharedService,
              private contractSharedService: ContractSharedService) {
  }

  @ViewChild(ContractFormComponent) modal: ContractFormComponent;

  ngOnInit() {
    this.init()
  }

  init() {
    this.customers = null;
    this.newCustomers = null;
    this.countCustomer = 0;
    this.appService.getCustomersList().then(data => {
        this.customers = data.json() as ICustomer[];
        this.newCustomers = this.customers.filter(elem => {
          if (elem.contractList.length == 0)
            return elem;
        });
        this.countCustomer = this.newCustomers.length;
      }
    )
  }

  onToggle(customer: ICustomer) {
    customer.selected = !customer.selected;
  }

  addContract(id) {
    this.sharedService.saveData(id);
    this.modal.init();
    this.modal.open = true;
  }

  changeContract(contract: IContract) {
    this.contractSharedService.saveData(contract.id);
    this.router.navigate(['/employee/contract-info']);
  }

  @ViewChild("wizard") wizard: Wizard;

  untouched: boolean = true;
  loading: boolean = false;
  errorFlag: boolean = false;
  progress: number = 0;
  open: boolean = false;

  customer: any = {};
  address: any = {};

  get readyToFinish(): boolean {
    return !this.untouched && !this.loading;
  }

  doCancel(): void {
    this.wizard.close();
    this.resetWizard();
  }

  resetWizard(): void {
    this.wizard.reset();
    this.customer = {};
    this.address = {};
    this.progress = 0;
    this.errorFlag = false;
    this.untouched = true;
    this.loading = false;
  }

  onCommit(): void {
    if (this.untouched) {
      this.untouched = false;
      this.loading = true;
      this.customer.address = this.address;
      this.appService.createUser(this.customer)
        .then(() => {
          this.errorFlag = false;
          this.loading = false
        })
        .catch(() => {
          this.errorFlag = true;
          this.loading = false
        });
    } else if (this.errorFlag) {
      this.wizard.forceFinish();
      this.resetWizard();
    } else {
      this.wizard.forceFinish();
      this.resetWizard();
      this.init();
    }
  }

  change() {
    this.open = true;
  }

  deleteContract(contract: number) {
    this.appService.deleteContract(contract).then(() =>
      this.init());
  }
}
