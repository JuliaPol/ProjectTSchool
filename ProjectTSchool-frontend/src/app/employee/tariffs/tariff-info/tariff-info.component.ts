import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {AppService} from "../../../app.service";
import {TariffSharedService} from "../tariff-shared.service";
import {ITariff} from "../../../interfaces/tariff";

@Component({
  moduleId: module.id,
  selector: 'tariff-info',
  templateUrl: './tariff-info.component.html'
})
export class TariffInfoComponent implements OnInit {
  tariff: ITariff;
  tariffId: number;
  result: string;

  constructor(private router: Router,
              private appService: AppService,
              private sharedService: TariffSharedService) {

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
    this.appService.editTariff(this.tariff).then(() => {
      this.init();
      this.result = 'Changed';
    });
  }

  delete() {
    this.appService.deleteTariff(this.tariffId)
      .then(() => this.router.navigate(['/employee/tariffs']));
  }
}
