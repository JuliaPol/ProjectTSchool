import {Component} from "@angular/core";
import {AppService} from "../../../app.service";

@Component({
  moduleId: module.id,
  selector: 'option-info',
  templateUrl: './option-info.component.html'
})

export class OptionInfoComponent {
  constructor(private appService: AppService) {
  }
  option: any = {};
  result: string = '';

  onSubmit() {
    this.appService.editOption(this.option).then(() => this.result = 'Changed');
  }
}
