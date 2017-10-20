import {Http, Response} from "@angular/http";
import {Injectable} from "@angular/core";
import 'rxjs/add/operator/toPromise';
import {IOption} from "./interfaces/options";

@Injectable()
export class AppService {
  constructor(private http: Http) {
  }

  getCurrentUser(): Promise<Response> {
    return this.http.get('http://localhost:8080/user')
      .toPromise();
  }

  getAllOptions(): Promise<Response> {
    return this.http.get('http://localhost:8080/options/all')
      .toPromise();
  }

  getOptionById(id: number): Promise<Response> {
    return this.http.get('http://localhost:8080/options?id=' + id)
      .toPromise();
  }

  addRule(current: number, optionList: string[], isCoppatible: boolean) {
    return this.http.post('http://localhost:8080/options/addRule?optionId='
      + current + '&isCompatible=' + isCoppatible, optionList).toPromise();
  }

  createOption(option) {
    return this.http.post('http://localhost:8080/options/create', option).toPromise();
  }

  deleteOption(id) {
    return this.http.post('http://localhost:8080/options/delete?id=' + id, {}).toPromise();
  }

  deleteTariff(id) {
    return this.http.post('http://localhost:8080/tariffs/delete?id=' + id, {}).toPromise();
  }

  getTariffById(id: number): Promise<Response> {
    return this.http.get('http://localhost:8080/tariffs/' + id)
      .toPromise();
  }

  createTariff(rate) {
    return this.http.post('http://localhost:8080/tariff/create', rate).toPromise();
  }

  createUser(user) {
    return this.http.post('http://localhost:8080/customers/create', user).toPromise();
  }

  editTariff(option) {
    return this.http.post('http://localhost:8080/tariffs/edit', option).toPromise();
  }

  editOption(option) {
    return this.http.post('http://localhost:8080/options/edit', option).toPromise();
  }

  getAllTariffs(): Promise<Response> {
    return this.http.get('http://localhost:8080/tariffs/all')
      .toPromise();
  }

  getCustomersList(): Promise<Response> {
    return this.http.get('http://localhost:8080/customers/allCustomers')
      .toPromise();
  }

  updateContractStatus(number: string) {
    return this.http.post('http://localhost:8080/contract/' + number, {}).toPromise();
  }
}
