import {Component, Input, OnInit} from "@angular/core";
import {IOption} from "../../../interfaces/options";
import {AppService} from "../../../app.service";
import {IItem} from "../../options/options-rules/options-rules.component";

@Component({
  moduleId: module.id,
  selector: 'option-list',
  templateUrl: './option-list.component.html'
})
export class OptionListComponent implements OnInit {
  @Input() optionList: IOption[];
  purposeList: IItem[] = [];
  options: IOption[];
  anotherOptions: IOption[] = [];
  selectedOption: IOption;
  warning: IOption[];

  constructor(private appService: AppService) {

  }

  ngOnInit() {
    this.init()
  }

  init() {
    this.optionList.forEach((item) => {
      this.purposeList.push({name: item.name, selected: true});
    });
    this.appService.getAllOptions().then(data =>
      this.options = data.json() as IOption[]).then(() => {
      this.anotherOptions = this.options.slice();
      this.purposeList.forEach((item) => {
       this.clean(item);
      });
    });

  }

  private clean(item) {
    this.anotherOptions
      .splice(this.myIndexOf(this.anotherOptions
        .filter(value => value.name === item.name).pop()), 1);
  }

  addOption() {
    this.purposeList.push({name: this.selectedOption.name, selected: true});
    this.clean(this.selectedOption);
    this.selectedOption = null;
    this.purposeList.forEach((item) => {

    });
  }

  private myIndexByName(o) {
    if (!o) {
      return;
    }
    for (let i = 0; i < this.anotherOptions.length; i++) {
      if (this.anotherOptions[i].name === o.name) {
        return i;
      }
    }
    return -1;
  }

  private myIndexOf(o) {
    if (!o) {
      return;
    }
    for (let i = 0; i < this.anotherOptions.length; i++) {
      if (this.anotherOptions[i].id === o.id) {
        return i;
      }
    }
    return -1;
  }
}
