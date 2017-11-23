import {Http, Response, Headers, RequestOptions} from "@angular/http";
import {Injectable} from "@angular/core";
import 'rxjs/add/operator/toPromise';
import {Router} from "@angular/router";

@Injectable()
export class AppService {
  constructor(private http: Http, private router: Router) {
  }

  login(username, password): Promise<Response> {
    let url = ''
    let params = new URLSearchParams();
    params.append('username', username);
    params.append('password', password);
    params.append('grant_type','password');
    params.append('client_id','fooClientIdPassword');
    let headers = new Headers();
    headers.set('content-type', 'application/x-www-form-urlencoded; charset=utf-8');
    headers.set('Authorization', 'Basic '+btoa("fooClientIdPassword:secret"));

    const options = new RequestOptions({
      headers: headers
    });
    return this.http.post('http://localhost:8080/check', params.toString(), options)
        .toPromise()
  }

  getCurrentUser(): Promise<Response> {
    return this.http.get('http://localhost:8080/user')
      .toPromise();
  }

  getAllOptions(): Promise<Response> {
    return this.http.get('http://localhost:8080/options/all')
      .toPromise();
  }

  getAllOptionsInBasket(id: number): Promise<Response> {
    return this.http.get('http://localhost:8080/basket/get/options/' + id)
      .toPromise();
  }

  getOptionById(id: number): Promise<Response> {
    return this.http.get('http://localhost:8080/options?id=' + id)
      .toPromise();
  }

  getAllFreeOptions(number: string): Promise<Response> {
    return this.http.get('http://localhost:8080/options/free/' + number)
      .toPromise();
  }


  getContractById(id: number): Promise<Response> {
    return this.http.get('http://localhost:8080/contract?id=' + id)
      .toPromise();
  }

  deleteContract(id: number): Promise<Response> {
    return this.http.post('http://localhost:8080/contract/delete/' + id, {})
      .toPromise();
  }

  signOut(): Promise<Response> {
    return this.http.post('http://localhost:8080/logout',{})
      .toPromise();
  }

  changeContractByCustomer(id: number): Promise<Response> {
    return this.http.post('http://localhost:8080/contract/customer/' + id, {})
      .toPromise();
  }

  updateContract(contract): Promise<Response> {
    return this.http.post('http://localhost:8080/contract/update', contract)
      .toPromise();
  }

  updateContractOptions(id, options): Promise<Response> {
    return this.http.post('http://localhost:8080/contract/update/options/' + id, options)
      .toPromise();
  }

  updateContractOptionsInBasket(id, options): Promise<Response> {
    return this.http.post('http://localhost:8080/basket/add/options/' + id, options)
      .toPromise();
  }

  clearBasket(id): Promise<Response> {
    return this.http.post('http://localhost:8080/basket/clear/' + id, {})
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

  deleteTariff(tariff) {
    return this.http.post('http://localhost:8080/tariffs/delete/' + tariff, {}).toPromise();
  }

  getTariffById(id: number): Promise<Response> {
    return this.http.get('http://localhost:8080/tariffs/' + id)
      .toPromise();
  }

  createUser(user) {
    return this.http.post('http://localhost:8080/user/createNewAccount', user).toPromise();
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
}
