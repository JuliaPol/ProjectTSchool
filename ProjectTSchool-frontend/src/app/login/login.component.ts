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

    this.service.login(this.model.username, this.model.password)
      .then((response) => {
        let str = response.url;
        let newstr = str.slice(21);
        if (newstr === '/login-error')
          this.errorFlag = true;
        else
          this.router.navigate([newstr]);
      })
      .catch((response) => {
          this.errorFlag = true;
        }
      )
  }

  openTab(link: string) {
    this.router.navigate([link]);
  }
}
