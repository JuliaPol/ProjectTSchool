import {Injectable} from "@angular/core";

@Injectable()
export class SignupSharedService {
  sharedData = {};

  saveData(model) {
    this.sharedData = model;
  }

  getData() {
    return this.sharedData;
  }
}
