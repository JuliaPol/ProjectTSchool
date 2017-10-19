import {Component, OnInit} from "@angular/core";
import {AppService} from "../../app.service";
import {Router} from "@angular/router";
import {ITariff} from "../../interfaces/tariff";

@Component({
  moduleId: module.id,
  selector: 'tariffs',
  templateUrl: './tariffs.component.html'
})
export class TariffsComponent implements OnInit {

  rates: ITariff = null;

  constructor(private appService: AppService, private router: Router) {

  }

  ngOnInit() {
    this.init()
  }

  init() {
    this.appService.getAllTariffs().then(data =>
      this.rates = data.json() as ITariff);
  }

  openTab(link: string) {
    this.router.navigate([link]);
  }

}
