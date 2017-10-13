import {Component, OnInit} from "@angular/core";
import {Http} from "@angular/http";
import "rxjs/add/operator/map";

export interface IUser {
  number: string,
  rate: IRate,
  optionList: IOption[],
}

export interface IRate {
  name: string,
  cost: number,
}

export interface IOption {
  description: string,
}

@Component({
  moduleId: module.id,
  selector: 'employee',
  templateUrl: './employee.component.html'
})
export class EmployeeComponent implements OnInit {

  user: IUser = null;

  constructor(private http: Http) {

  }

  ngOnInit() {
    this.get();
  }

  get() {
    return this.http.get('http://localhost:8080/contract/88005353')
      .map(res => res.json())
      .subscribe(data =>
      this.user = data as IUser
    )
  }
}
