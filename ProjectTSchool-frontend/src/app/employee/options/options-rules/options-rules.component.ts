import {IOption} from "../../../interfaces/options";
import {Component, OnInit} from "@angular/core";
import {AppService} from "../../../app.service";

interface IItem {
  name: string,
  selected: boolean,
}
@Component({
  moduleId: module.id,
  selector: 'options-rules',
  templateUrl: './options-rules.component.html'
})
export class OptionsRulesComponent implements OnInit {
  options: IOption[];
  optionsForSelection: IOption[];
  selectedOption: IOption;
  purposeOption: IOption;
  purposeList: IItem[] = [];
  selectedRule: boolean = true;

  constructor(private appService: AppService) {

  }

  ngOnInit() {
    this.init()
  }

  init() {
    this.appService.getAllOptions().then(data => {
      this.optionsForSelection = data.json() as IOption[];
      this.options = data.json() as IOption[];
      this.selectedOption = null;
      this.purposeList = [];
      this.purposeOption = null;
    });
  }

  optionSelected(id: any) {
    this.purposeList = [];
    this.optionsForSelection = this.options.slice();
    let option: IOption = this.options.filter(value => value.id === parseInt(id)).pop();
    if (id) {
      this.selectedOption = option;
    }
    if (this.selectedOption) {
      this.optionsForSelection.splice(this.myIndexOf(option), 1);
    }
    if (this.purposeOption) {
      this.optionsForSelection
        .splice(this.myIndexOf(this.purposeOption), 1);
      this.purposeOption = null;
    }
    if (this.selectedOption && this.selectedRule) {
      if (this.selectedOption.compatibleOptions !== null) {
        this.selectedOption.compatibleOptions.forEach((optName) => {
          this.filterOptionsForSelection(optName);
        });
      }
    } else {
      if (this.selectedOption.incompatibleOptions !== null) {
        this.selectedOption.incompatibleOptions.forEach((optName) => {
          this.filterOptionsForSelection(optName);
        });
      }
    }
  }

  private filterOptionsForSelection(name: string) {
    this.optionsForSelection
      .splice(this.myIndexOf(this.optionsForSelection
          .filter(value => value.name === name).pop()), 1);
    this.purposeList.push({name: name, selected: true});
  }

  purposeOptionSelected(id: any) {
   let option: IOption = this.optionsForSelection
      .filter(value => value.id === parseInt(id)).pop();
   if (option) {
     this.purposeOption = option;
   }
  }

  addToPurposeList() {
    if (this.selectedOption && this.purposeOption) {
      this.purposeList.push({name: this.purposeOption.name, selected: true});
      this.optionsForSelection
        .splice(this.myIndexOf(this.purposeOption), 1);
      this.purposeOption = null;
    }
  }

  addRule() {
    let list: string[] = [];
    this.purposeList.filter((item) => item.selected)
      .forEach((item) => list.push(item.name));
    if (list.length !== 0 && this.selectedOption) {
      this.appService.addRule(this.selectedOption.id, list, this.selectedRule)
        .then(() => this.init());
    }
  }

  private myIndexOf(o) {
    if (!o) {
      return;
    }
    for (let i = 0; i < this.optionsForSelection.length; i++) {
      if (this.optionsForSelection[i].id === o.id) {
        return i;
      }
    }
    return -1;
  }
}
