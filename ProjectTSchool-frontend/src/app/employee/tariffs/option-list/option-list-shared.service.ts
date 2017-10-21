import {Injectable} from "@angular/core";
import {IOptionItem} from "./option-list.component";
import {IOption} from "../../../interfaces/options";

@Injectable()
export class OptionListSharedService {
  sharingData: any[] = [];

  saveData(options){
    this.sharingData = options;
  }

  clean() {
    this.sharingData = [];
  }

  getData() {
    return this.sharingData;
  }
}
