import {Component, OnInit} from "@angular/core";
import {AppService} from "../../../app.service";
import {OptionsSharedService} from "../options-shared.service";
import {IOption} from "../../../interfaces/options";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'option-info',
  templateUrl: './option-info.component.html'
})

export class OptionInfoComponent implements OnInit {
  option: IOption = {} as IOption;
  result: string = '';
  optionId: number;
  loading: boolean = false;
  finish: boolean = false;
  errorFlag: boolean = false;

  constructor(private router: Router,
              private appService: AppService,
              private sharedService: OptionsSharedService) {

  }

  ngOnInit(): void {
    this.init();
  }

  init() {
    this.optionId = this.sharedService.getData();
    this.appService.getOptionById(this.optionId).then((data) => {
      this.option = data.json() as IOption;
    });
  }

  onSubmit() {
    this.finish = false;
    this.loading = true;
    this.appService.editOption(this.option).then(() => {
      this.init();
      this.loading = false;
      this.finish = true;
      this.errorFlag = false;
    }).catch(() => {
      this.loading = false;
      this.finish = true;
      this.errorFlag = true;
    });
  }

  delete() {
    this.appService.deleteOption(this.optionId)
      .then(() => this.router.navigate(['/employee/options']));
  }
}
