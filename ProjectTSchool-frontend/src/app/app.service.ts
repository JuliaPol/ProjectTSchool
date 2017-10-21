import {Http, Response, Headers, RequestOptions} from "@angular/http";
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

  createUser(user) {
    return this.http.post('http://localhost:8080/customers/create', user).toPromise();
  }

  createContract(id, contract) {
    return this.http.post('http://localhost:8080/contract/create/' + id, contract).toPromise();
  }

  createTariff(rate) {
    return this.http.post('http://localhost:8080/tariffs/create', rate).toPromise();
  }

  editTariff(rate) {
    let headers = new Headers();
    headers.set('content-type', 'application/json');

    // let headers = new Headers({ 'Content-Type': 'application/json' });
    const options = new RequestOptions({
      headers: headers
    });
    return this.http.post('http://localhost:8080/tariffs/edit', JSON.stringify(rate), options).toPromise();
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
