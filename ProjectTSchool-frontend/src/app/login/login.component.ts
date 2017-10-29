import {Component} from "@angular/core";
import {Http, Headers, RequestOptions} from "@angular/http";
import 'rxjs/add/operator/toPromise';
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  public model ={username: "", password: ""};

  constructor(private http: Http, private router: Router) {

  }

  login() {
    let headers = new Headers();
    headers.set('content-type', 'application/json');
    const options = new RequestOptions({
      headers: headers
    });
    return this.http.post('http://localhost:8080/login',
      JSON.stringify({ username: this.model.username, password: this.model.password }), options)
      .toPromise();
  }

  openTab(link: string) {
    this.router.navigate([link]);
  }
}
