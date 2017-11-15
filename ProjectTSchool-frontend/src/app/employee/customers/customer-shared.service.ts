import {Injectable} from "@angular/core";

@Injectable()
export class CustomerSharedService {
  sharingData: number = 0;
  openModal: boolean = false;

  saveData(id) {
    this.sharingData = id;
  }

  getData() {
    return this.sharingData;
  }

  setModal(open) {
    this.openModal = open;
  }

  getModal() {
    return this.openModal;
  }
}
