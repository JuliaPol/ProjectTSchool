import {Component, Input, OnInit} from "@angular/core";
import {IOption} from "../../../interfaces/options";
import {AppService} from "../../../app.service";
import {OptionListSharedService} from "./option-list-shared.service";
import {Util} from "../../../util/Util";
import {CustomerContractSharedService} from "../../../customer/customer-contract-shared.service";
import {isNullOrUndefined, isUndefined} from "util";


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
  @Input() showSelect: boolean;
  purposeList: IOptionItem[] = [];
  options: IOption[];
  anotherOptions: IOption[] = [];
  selectedOption: IOption;
  warnings: IWarnings[] = [];
  purposeListEntity: any[] = [];
  incompatibleOptions: IOption[] = [];

  constructor(private appService: AppService, private sharedService: OptionListSharedService,
              private sharedServiceCust: CustomerContractSharedService) {
    sharedServiceCust.changeEmitted$.subscribe(
      () => this.init());
  }

  ngOnInit() {
    this.init()
  }

  init() {
    this.purposeList = [];
    this.warnings = [];
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
      if (this.rateOptions && this.rateOptions.length !== 0) {
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
    let index = Util.myIndexOf(
      this.anotherOptions.filter(value => value.name === item).pop(),
      this.anotherOptions,
      'name', 'name');
    if (index || index === 0) {
      return this.anotherOptions.splice(index, 1);
    }
  }

  private cleanIncomp() {
    if (this.purposeList.length !== 0) {
      this.purposeList.forEach((item) => {
        if (item.selected) {
          item.incompatibleOptions.forEach((i) => {
            this.addToArrayOfIncompatibleAndDeleteFromSelectorOptions(i);
          })
        }
      });
    }
  }

  private addToArrayOfIncompatibleAndDeleteFromSelectorOptions(item) {
    let incOption = this.cleanByName(item);
    if (incOption) {
      if (!Util.myIndexOf(
          this.incompatibleOptions.filter(value => value.name === item).pop(),
          this.incompatibleOptions,
          'name', 'name')) {
        this.incompatibleOptions.push(incOption.pop());
      }
    }
  }

  private createOptionItem(item) {
    return {
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
  }

  addOption() {
    if (!isNullOrUndefined(this.selectedOption)) {
      this.purposeList.forEach((item) => {
        if (!item.selected) {
          this.anotherOptions.push(this.convertToEntity(item));
        }
      });
      this.purposeList = this.purposeList.filter((item) => item.selected);
      this.incompatibleOptions.forEach((d, i) => {
        this.anotherOptions.push(d);
      });
      this.incompatibleOptions = [];
      this.purposeList.push(this.createOptionItem(this.selectedOption));
      this.clean(this.selectedOption);
      this.cleanIncomp();
      this.checkPurposeListByCompabilities();
      this.warnings.forEach((item) => {
        if (this.arrayContainsArray(item.optionName)) {
          this.cleanWarnings(item);
        }
      });
      this.selectedOption = null;
      this.sharedService.saveData(this.convert());
      this.sharedService.saveWarningsCount(this.warnings.length);
    }
  }

  private checkPurposeListByCompabilities() {
    this.warnings = [];
    this.purposeList.forEach((item) => {
      if (item.selected && item.compatibleOptions.length !== 0) {
        if (!this.arrayContainsArray(item.compatibleOptions)) {
          let desc = item.name + ' can not be added without ';
          let optName: string[] = [];
          item.compatibleOptions.forEach((d, i) => {
            desc = desc + d + (i === item.compatibleOptions.length - 1 ? '; ' : ', ');
            optName.push(d);
          });
          this.warnings.push({purposeOption: item.name, description: desc, optionName: optName});
        }
      }
    });
  }

  changeSelected(option: IOptionItem) {
    if (!option.selected && option.incompatibleOptions.length !== 0) {
      this.refreshOptionsInSelector(option);
    }
    this.cleanIncomp();
    this.checkPurposeListByCompabilities();
    this.sharedService.saveData(this.convert());
    this.sharedService.saveWarningsCount(this.warnings.length);
  }

  private refreshOptionsInSelector(option) {
    option.incompatibleOptions.forEach((i) => {
      let incOption = Util.myIndexOf(i, this.incompatibleOptions, null, 'name');
      if (!(isUndefined(incOption) || incOption === -1 || incOption === null)) {
        this.anotherOptions.push(this.incompatibleOptions.splice(incOption, 1).pop());
      }
    });
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
      if (this.purposeList[i].name === o
        && this.purposeList[i].selected) {
        return i;
      }
    }
    return -1;
  }
}
