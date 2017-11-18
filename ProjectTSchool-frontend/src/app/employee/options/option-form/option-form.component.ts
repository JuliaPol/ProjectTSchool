import {Component, EventEmitter, OnInit, Output} from "@angular/core";
import {AppService} from "../../../app.service";
import {IOption} from "../../../interfaces/options";

@Component({
  moduleId: module.id,
  selector: 'option-form',
  templateUrl: './option-form.component.html'
})
export class OptionFormComponent implements OnInit{
  option: any = {};
  result: string = '';
  open: boolean = false;
  loading: boolean = false;
  finish: boolean = false;
  errorFlag: boolean = false;
  @Output() onChanged = new EventEmitter<boolean>();

  constructor(private appService: AppService) {

  }

  ngOnInit() {
    this.init();
  }

  init() {
    this.reset();
  }

  onSubmit() {
    this.loading = true;
    this.appService.createOption(this.option).then(() => {
      this.loading = false;
      this.errorFlag = false;
      this.finish = true;
    }).catch(() => {
      this.errorFlag = true;
      this.loading = false;
      this.finish = true;
    });
  }

  exit() {
    if (this.errorFlag === true) {
      this.option = {};
      this.loading = false;
      this.finish = false;
      this.errorFlag = false;
    } else {
      this.reset();
      this.onChanged.emit();
    }
  }

  reset() {
    this.option = {};
    this.open = false;
    this.loading = false;
    this.finish = false;
    this.errorFlag = false;
  }
}
