import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {AppService} from "../../../app.service";
import {TariffSharedService} from "../tariff-shared.service";
import {ITariff} from "../../../interfaces/tariff";
import {OptionListSharedService} from "../option-list/option-list-shared.service";

@Component({
  moduleId: module.id,
  selector: 'tariff-info',
  templateUrl: './tariff-info.component.html'
})
export class TariffInfoComponent implements OnInit {
  tariff: ITariff = {} as ITariff;
  tariffId: number;
  loading: boolean = false;
  finish: boolean = false;
  errorFlag: boolean = false;

  constructor(private router: Router,
              private appService: AppService,
              private sharedService: TariffSharedService,
              private sharedServiceOptions: OptionListSharedService) {

  }

  ngOnInit(): void {
    this.init();
  }

  init() {
    this.tariffId = this.sharedService.getData();
    this.appService.getTariffById(this.tariffId)
      .then((data) => this.tariff = data.json() as ITariff);
  }

  onSubmit() {
    this.finish = false;
    let warningCount = this.sharedServiceOptions.getWarningsCount();
    if (warningCount === 0) {
      this.loading = true;
      this.tariff.optionList = this.sharedServiceOptions.getData();
      this.appService.editTariff(this.tariff).then(() => {
        this.init();
        this.loading = false;
        this.finish = true;
        this.errorFlag = false;
        this.sharedServiceOptions.clean();
      }).catch(() => {
        this.loading = false;
        this.finish = true;
        this.errorFlag = true;
      });
    } else {
      this.init();
      this.errorFlag = true;
    }
  }

  delete() {
    this.appService.deleteTariff(this.tariff.id)
      .then(() => this.router.navigate(['/employee/tariffs']));
  }
}
