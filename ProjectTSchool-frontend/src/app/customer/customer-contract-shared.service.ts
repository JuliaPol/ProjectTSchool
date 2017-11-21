import {Injectable} from "@angular/core";
import {Subject} from "rxjs/Subject";

@Injectable()
export class CustomerContractSharedService {
  private emitChangeSource = new Subject<any>();

  changeEmitted$ = this.emitChangeSource.asObservable();

  contractNumber: number = 0;

  saveData(id) {
    this.contractNumber = id;
  }

  getData() {
    return this.contractNumber;
  }


  emitChange(change: any) {
    this.emitChangeSource.next(change);
  }
}
