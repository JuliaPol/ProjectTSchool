import {Component, OnInit} from "@angular/core";
import {ITariff} from "../../interfaces/tariff";
import {AppService} from "../../app.service";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'contract-form',
  templateUrl: './contract-form.component.html'
})
export class ContractFormComponent implements OnInit{

  rates: ITariff = null;
  numbers: any[] = [];
  result: string ='';

  constructor(private appService: AppService) {

  }

  ngOnInit() {
    this.init();
  }

  init() {
    this.appService.getAllTariffs().then(data =>
      this.rates = data.json() as ITariff);
    this.numbers.push(new Date().getTime()-1);
    this.numbers.push(new Date().getTime());
    this.numbers.push(new Date().getTime()+1);
  }

  randomIntFromInterval(min,max)
  {
    return Math.floor(Math.random()*(max-min+1)+min);
  }

  onSubmit() {
    // this.appService.createContract(this.contract).then(() => this.result = 'Added');
  }
}
