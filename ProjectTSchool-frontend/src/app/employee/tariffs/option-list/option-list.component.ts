import {Component, Input, OnInit} from "@angular/core";
import {IOption} from "../../../interfaces/options";
import {AppService} from "../../../app.service";
import {OptionListSharedService} from "./option-list-shared.service";
import {Util} from "../../../util/Util";


export interface IOptionItem {
  id: number,
  description: string,
  cost: number,
  costOfConnection: number,
  name: string,
  compatibleOptions: string[],
  incompatibleOptions: string[],
  compatibleOptionsOf: string[],
  selected: boolean,
}
interface IWarnings {
  description: string,
  optionName: string[],
  purposeOption: string,
}
@Component({
  moduleId: module.id,
  selector: 'option-list',
  templateUrl: './option-list.component.html'
})
export class OptionListComponent implements OnInit {
  @Input() optionList: IOption[];
  @Input() rateOptions: IOption[];
  purposeList: IOptionItem[] = [];
  options: IOption[];
  anotherOptions: IOption[] = [];
  selectedOption: IOption;
  warnings: IWarnings[] = [];
  purposeListEntity: any[] = [];

  constructor(private appService: AppService, private sharedService: OptionListSharedService) {

  }

  ngOnInit() {
    this.init()
  }

  init() {
    this.sharedService.clean();
    if (this.optionList.length !== 0) {
      this.optionList.forEach((item) => {
        this.purposeList.push(this.createOptionItem(item));
      });
    }

    this.appService.getAllOptions().then(data =>
      this.options = data.json() as IOption[]).then(() => {
      this.anotherOptions = this.options.slice();
      if (this.optionList.length !== 0) {
        this.purposeList.forEach((item) => {
          this.clean(item);
        });
      }
      if (this.rateOptions && this.rateOptions.length !== 0 ) {
        this.rateOptions.forEach((item) => {
          this.clean(item);
        });
      }
      this.cleanIncomp();
      if (this.rateOptions && this.rateOptions.length !== 0) {
        this.rateOptions.forEach((item) => {
          item.incompatibleOptions.forEach((i) => {
            this.cleanByName(i);
          })
        })
      }
      this.sharedService.saveData(this.convert());
      this.sharedService.saveWarningsCount(this.warnings.length);
    });
  }

  private clean(item) {
    this.anotherOptions
      .splice(Util.myIndexOf(this.anotherOptions
        .filter(value => value.name === item.name).pop(),
        this.anotherOptions,
        'id', 'id'), 1);
  }

  private cleanByName(item) {
    this.anotherOptions
      .splice(Util.myIndexOf(
        this.anotherOptions.filter(value => value.name === item).pop(),
        this.anotherOptions,
        'name', 'name'), 1);
  }

  private cleanIncomp() {
    if (this.purposeList.length !== 0) {
      this.purposeList.forEach((item) => {
        item.incompatibleOptions.forEach((i) => {
          this.cleanByName(i);
        })
      });
    }

  }

  private createOptionItem(item) {
    let optionItem = {
      id: item.id,
      description: item.description,
      cost: item.cost,
      costOfConnection: item.costOfConnection,
      name: item.name,
      compatibleOptions: item.compatibleOptions,
      incompatibleOptions: item.incompatibleOptions,
      compatibleOptionsOf: item.compatibleOptionsOf,
      selected: true,
    } as IOptionItem;

    return optionItem;
  }

  addOption() {
    this.purposeList.push(this.createOptionItem(this.selectedOption));
    this.clean(this.selectedOption);
    this.cleanIncomp();
    this.checkPurposeListByCompabilities();
    this.warnings.forEach((item) => {
      if(this.arrayContainsArray(item.optionName)) {
        this.cleanWarnings(item);
      }
    });
    this.selectedOption = null;
    this.sharedService.saveData(this.convert());
    this.sharedService.saveWarningsCount(this.warnings.length);
  }

  private checkPurposeListByCompabilities() {
    this.warnings= [];
    this.purposeList.forEach((item) => {
      if (item.selected && item.compatibleOptions.length !== 0) {
        if (!this.arrayContainsArray(item.compatibleOptions)) {
          let desc = item.name + ' can not be added without ';
          let optName: string[] = [];
          item.compatibleOptions.forEach((i) => {
            desc = desc + i;
            optName.push(i);
          });
          this.warnings.push({purposeOption: item.name, description: desc, optionName: optName});
        }
      }
    });
  }

  changeSelected() {
    this.checkPurposeListByCompabilities();
    this.sharedService.saveData(this.convert());
    this.sharedService.saveWarningsCount(this.warnings.length);
  }

  private cleanWarnings(item) {
    this.warnings
      .splice(Util.myIndexOf(
        this.warnings.filter(value => value.description === item.description).pop(),
        this.warnings, null, 'description'), 1);
  }

  arrayContainsArray(subset) {
    return subset.every((value) => {
      return (this.myIndexPurposeList(value) >= 0);
    });
  }

  convert() {
    this.purposeListEntity = [];
    if (this.purposeList.length !== 0) {
      this.purposeList.filter((item) => item.selected)
        .forEach((item) => {
        this.purposeListEntity.push(this.convertToEntity(item))
      });
      return this.purposeListEntity;
    }
  }

  convertToEntity(item) {
    return {
      id: item.id,
      description: item.description,
      cost: item.cost,
      costOfConnection: item.costOfConnection,
      name: item.name,
      compatibleOptions: item.compatibleOptions,
      incompatibleOptions: item.incompatibleOptions,
      compatibleOptionsOf: item.compatibleOptionsOf,
    } as IOption;
  }

  isOptionInWarningsList(option) {
    if (Util.myIndexOf(option, this.warnings, 'name', 'purposeOption') !== -1) {
      return 'label-warning';
    } else {
      return '';
    }
  }

  private myIndexPurposeList(o) {
    if (!o) {
      return;
    }
    for (let i = 0; i < this.purposeList.length; i++) {
      if (!this.purposeList[i].selected) {
        return -1;
      }
      if (this.purposeList[i].name === o) {
        return i;
      }
    }
    return -1;
  }

}
