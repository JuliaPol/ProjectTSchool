import {Component, OnInit, ViewChild} from "@angular/core";
import {AppService} from "../../app.service";
import {Router} from "@angular/router";
import {ITariff} from "../../interfaces/tariff";
import {TariffSharedService} from "./tariff-shared.service";
import {TariffFormComponent} from "./tariff-form/tariff-form.component";

@Component({
  moduleId: module.id,
  selector: 'tariffs',
  templateUrl: './tariffs.component.html'
})
export class TariffsComponent implements OnInit {

  rates: ITariff = null;
  tariffName: string;

  @ViewChild(TariffFormComponent) modal: TariffFormComponent;

  constructor(private sharedService: TariffSharedService,
              private appService: AppService,
              private router: Router) {

  }

  ngOnInit() {
    this.init()
  }

  init() {
    this.rates = null;
    this.appService.getAllTariffs().then(data =>
      this.rates = data.json() as ITariff);
  }

  openTab(link: string) {
    this.router.navigate([link]);
  }

  change(id: number, link: string) {
    this.sharedService.saveData(id);
    this.openTab(link);
  }

  addRate() {
    this.modal.init();
    this.modal.open = true;
  }

  deleteRate(id: number) {
    this.appService.deleteTariff(id)
      .then(() => this.init());
  }
}
