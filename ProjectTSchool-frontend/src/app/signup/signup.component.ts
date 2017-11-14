import {Component, ViewChild} from "@angular/core";
import {Http} from "@angular/http";
import {AppService} from "../app.service";
import {Router} from "@angular/router";
import {Wizard} from "clarity-angular";
import {ITariff} from "../interfaces/tariff";

@Component({
  moduleId: module.id,
  selector: 'signup',
  templateUrl: './signup.component.html'
})
export class SignupComponent {

  result: string = '';
  customer: any = {};
  address: any = {};
  rates: ITariff[] = [];
  selectedRate: ITariff;

  @ViewChild("wizard") wizard: Wizard;

  untouched: boolean = true;
  loading: boolean = false;
  errorFlag: boolean = false;
  progress: number = 0;
  open: boolean = false;

  constructor(private appService: AppService,
              private router: Router) {
  }

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
    this.loading  = false;
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
    } else if(this.errorFlag) {
      this.wizard.forceFinish();
      this.resetWizard();
    } else {
      this.wizard.forceFinish();
      this.signOut('/logout');
    }
  }

  change() {
    this.open = true;
  }

    signOut(link: string) : void {
    window.location.href = link;
  }

  ngOnInit(): void {
    this.init();
  }

  init() {
    this.appService.getAllTariffs().then(data => {
      this.rates = data.json() as ITariff[];
    });
  }
}
