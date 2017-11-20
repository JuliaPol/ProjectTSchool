import {Component} from "@angular/core";
import {Http, Headers, RequestOptions} from "@angular/http";
import 'rxjs/add/operator/toPromise';
import {Router} from "@angular/router";
import {AppService} from "../app.service";
import {IRole} from "../interfaces/customers";

@Component({
  moduleId: module.id,
  selector: 'login',
  templateUrl: './login.component.html'
})
export class LoginComponent {

  public model = {username: "", password: ""};

  role: IRole = null;

  errorFlag: boolean = false;

  constructor(private http: Http, private router: Router, private appService: AppService) {

  }

  login() {
    this.errorFlag = false;
    let params = new URLSearchParams();
    params.append('username', this.model.username);
    params.append('password', this.model.password);
    params.append('grant_type', 'password');
    params.append('client_id', 'fooClientIdPassword');
    let headers = new Headers();
    headers.set('content-type', 'application/x-www-form-urlencoded; charset=utf-8');
    headers.set('Authorization', 'Basic ' + btoa("fooClientIdPassword:secret"));


    const options = new RequestOptions({
      headers: headers
    });
    return this.http.post('http://localhost:8080/check', params.toString(), options)
      .toPromise().then(res => {
        this.appService.getCurrentRole().then(data => {
          this.role = data.json() as IRole;
          if (this.role.role === 'ROLE_MANAGER')
            this.openTab('/employee');
          if (this.role.role === 'ROLE_CUSTOMER')
            this.openTab('/customer');
          else
            this.openTab('/login');
        })
      }).catch(() => this.errorFlag = true);
  }

  openTab(link: string) {
    this.router.navigate([link]);
  }
}
