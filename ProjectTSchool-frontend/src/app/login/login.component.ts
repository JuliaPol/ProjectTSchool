import {Component} from "@angular/core";
import 'rxjs/add/operator/toPromise';
import {Router} from "@angular/router";
import {AppService} from "../app.service";

@Component({
  moduleId: module.id,
  selector: 'login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  public model = {username: "", password: ""};

  errorFlag: boolean = false;

  constructor(private service: AppService, private router: Router) {

  }

  login() {
    this.errorFlag = false;
    // let params = new URLSearchParams();
    // params.append('username', this.model.username);
    // params.append('password', this.model.password);
    // params.append('grant_type','password');
    // params.append('client_id','fooClientIdPassword');
    // let headers = new Headers();
    // headers.set('content-type', 'application/x-www-form-urlencoded; charset=utf-8');
    // headers.set('Authorization', 'Basic '+btoa("fooClientIdPassword:secret"));
    //
    // const options = new RequestOptions({
    //   headers: headers
    // });
    // return this.http.post('http://localhost:8080/check', params.toString(), options)
    //   .map(res => this.router.navigate(['/' + res.json().url.split('/')[1]]))

    this.service.login(this.model.username, this.model.password)
      .then((response) => {
        var str = response.url;
        var newstr = str.slice(21);
        console.log(newstr);
        if (newstr === '/login-error')
          this.errorFlag = true;
        else
          this.router.navigate([str.slice(21)]);
      })
      .catch((response) => {
      this.errorFlag = true;
      console.log(response.url);
      }
    )
  }

  openTab(link: string) {
    this.router.navigate([link]);
  }
}
