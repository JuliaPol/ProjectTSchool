import {Component, OnInit} from "@angular/core";
import "rxjs/add/operator/map";
import {Router} from "@angular/router";
import {ITariff} from "../../interfaces/tariff";
import {AppService} from "../../app.service";
import {isNullOrUndefined} from "util";

@Component({
  moduleId: module.id,
  selector: 'main-tariff',
  templateUrl: './main-tariff.component.html'
})
export class MainTariffComponent implements OnInit {

  allTariffs: ITariff[];
  rates: ITariff[];
  rate: ITariff;
  readonly internet: number = 2000;
  readonly sms: number = 100;
  readonly calls: number = 300;
  callsToggle: boolean = false;
  smsToggle: boolean = false;
  internetToggle: boolean = false;
  notFound: boolean = false;

  constructor(private appService: AppService,
              private router: Router) {
  }

  ngOnInit() {
    this.init();
  }

  init() {
    this.appService.getAllTariffs().then(data => {
        this.allTariffs = data.json() as ITariff[];
        this.filterChange();
      }
    )
  }

  buy() {
    this.router.navigate(['/customer']);
  }

  filterChange() {
    this.notFound = false;
    let filterTariffs = this.allTariffs.filter(item =>
      this.callsToggle ? item.calls >= this.calls : item.calls <= this.calls
    );
    filterTariffs = filterTariffs.filter(item =>
      this.smsToggle ? item.sms >= this.sms : item.sms<= this.sms
    );
    filterTariffs = filterTariffs.filter(item =>
      this.internetToggle ? item.internet >= this.internet : item.internet <= this.internet
    );
    this.rate = filterTariffs.sort((a, b) =>
          a.cost - b.cost).shift();
    if (isNullOrUndefined(this.rate)){
      this.rates = this.allTariffs;
      this.notFound = true;
    } else {
      this.rates = this.allTariffs.filter((item) => item.id !== this.rate.id);
    }
  }
}
