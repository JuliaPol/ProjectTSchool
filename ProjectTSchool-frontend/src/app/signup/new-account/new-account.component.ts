import {Component, OnInit} from "@angular/core";
import {AppService} from "../../app.service";
import {Router} from "@angular/router";
import {SignupSharedService} from "../signup-shared.service";

@Component({
  moduleId: module.id,
  selector: 'new-account',
  templateUrl: './new-account.component.html'
})
export class NewAccountComponent implements OnInit {
  customer: any = {};
  address: any = {};
  result: string = '';
  model: any = {};

  constructor(private appService: AppService,
              private router: Router,
              private sharedService: SignupSharedService) {

  }

  ngOnInit(): void {
    this.init();
  }

  init() {
    this.model = this.sharedService.getData();
    this.customer.email = this.model.email;
    this.customer.login = this.model.username;
    this.customer.password = this.model.password;
  }

  onSubmit() {
    this.customer.address = this.address;
    this.appService.createUser(this.customer).then(() => this.openTab("/login"));
    this.openTab("/login");
  }

  openTab(link: string) {
    this.router.navigate([link]);
  }
}
