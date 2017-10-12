import {Component} from "@angular/core";
import {Http} from "@angular/http";

@Component({
  moduleId: module.id,
  selector: 'login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  public model ={username: "", password: ""};

  constructor(private http: Http) {

  }

  login() {
    this.http
      .post('http://localhost:8080/login', JSON.stringify({ username: this.model.username, password: this.model.password }));
  }
}
