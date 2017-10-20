import {Injectable} from "@angular/core";

@Injectable()
export class TariffSharedService {
  sharingData: number = 0;
  saveData(id){
    this.sharingData = id;
  }
  getData() {
    return this.sharingData;
  }
}
