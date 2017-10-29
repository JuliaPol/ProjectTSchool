import {Injectable} from "@angular/core";

@Injectable()
export class OptionListSharedService {
  sharingData: any[] = [];
  warningsCount: number = 0;

  saveData(options) {
    this.sharingData = options;
  }

  saveWarningsCount(count) {
    this.warningsCount = count;
  }

  clean() {
    this.sharingData = [];
    this.warningsCount = 0;
  }

  getWarningsCount() {
    return this.warningsCount;
  }

  getData() {
    return this.sharingData;
  }
}
