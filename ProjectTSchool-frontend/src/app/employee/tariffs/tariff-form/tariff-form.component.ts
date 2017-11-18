import {Component, EventEmitter, OnInit, Output} from "@angular/core";
import {AppService} from "../../../app.service";

@Component({
  moduleId: module.id,
  selector: 'tariff-form',
  templateUrl: './tariff-form.component.html'
})
export class TariffFormComponent implements OnInit{

  open: boolean = false;
  loading: boolean = false;
  finish: boolean = false;
  errorFlag: boolean = false;
  @Output() onChanged = new EventEmitter<boolean>();

  constructor(private appService: AppService) {
  }

  tariff: any = {};
  result: string = '';

  ngOnInit() {
    this.init();
  }

  init() {
    this.reset();
  }

  onSubmit() {
    this.loading = true;
    this.appService.createTariff(this.tariff).then(() => {
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
      this.tariff = {};
      this.loading = false;
      this.finish = false;
      this.errorFlag = false;
    } else {
      this.reset();
      this.onChanged.emit();
    }
  }

  reset() {
    this.tariff = {};
    this.open = false;
    this.loading = false;
    this.finish = false;
    this.errorFlag = false;
  }
}
