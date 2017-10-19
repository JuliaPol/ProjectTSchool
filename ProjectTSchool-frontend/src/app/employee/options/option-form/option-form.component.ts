import {Component} from "@angular/core";
import {AppService} from "../../../app.service";
import {IOption} from "../../../interfaces/options";

@Component({
  moduleId: module.id,
  selector: 'option-form',
  templateUrl: './option-form.component.html'
})
export class OptionFormComponent {
  option: any = {};
  result: string = '';

  constructor(private appService: AppService) {

  }

  onSubmit() {
    this.appService.createOption(this.option).then(() => this.result = 'Added');
  }
}
