import {Injectable} from "@angular/core";

@Injectable()
export class CustomerSharedService {
  sharingData: number = 0;

  saveData(id) {
    this.sharingData = id;
  }

  getData() {
    return this.sharingData;
  }
}
