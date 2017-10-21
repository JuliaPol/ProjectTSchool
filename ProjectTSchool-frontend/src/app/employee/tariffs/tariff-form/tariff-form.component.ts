import {Component} from "@angular/core";
import {AppService} from "../../../app.service";

@Component({
  moduleId: module.id,
  selector: 'tariff-form',
  templateUrl: './tariff-form.component.html'
})
export class TariffFormComponent {

  constructor(private appService: AppService) {
  }

  tariff: any = {};
  result: string = '';

  onSubmit() {
    this.appService.createTariff(this.tariff).then(() => this.result = 'Added');
  }
}
