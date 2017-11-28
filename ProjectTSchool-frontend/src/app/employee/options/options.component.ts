import {Component, OnInit, ViewChild} from "@angular/core";
import {AppService} from "../../app.service";
import {IOption} from "../../interfaces/options";
import {Router} from "@angular/router";
import {OptionsSharedService} from "./options-shared.service";
import {OptionFormComponent} from "./option-form/option-form.component";

@Component({
  moduleId: module.id,
  selector: 'options',
  templateUrl: './options.component.html'
})
export class OptionsComponent implements OnInit {
  options: IOption = null;
  optionName: string;

  @ViewChild(OptionFormComponent) modal: OptionFormComponent;

  constructor(private appService: AppService,
              private router: Router,
              private sharedService: OptionsSharedService) {

  }

  ngOnInit() {
    this.init()
  }

  init() {
    this.options = null;
    this.appService.getAllOptions().then(data =>
      this.options = data.json() as IOption);
  }

  change(id: number, link: string) {
    this.sharedService.saveData(id);
    this.openTab(link);
  }

  addOption() {
    this.modal.init();
    this.modal.open = true;
  }

  openTab(link: string) {
    this.router.navigate([link]);
  }

  deleteOption(id: number) {
    this.appService.deleteOption(id)
      .then(() => this.init());
  }
}
