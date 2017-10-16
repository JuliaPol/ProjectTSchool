import {Http, Response} from "@angular/http";
import {Injectable} from "@angular/core";
import 'rxjs/add/operator/toPromise';

@Injectable()
export class AppService {
  constructor(private http: Http) {
  }

  getCurrentUser(): Promise<Response> {
    return this.http.get('http://localhost:8080/user')
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
