import {Component} from "@angular/core";
import {Http} from "@angular/http";
import {AppService} from "../app.service";
import {SignupSharedService} from "./signup-shared.service";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'signup',
  templateUrl: './signup.component.html'
})
export class SignupComponent {

  model : any = {};
  result: string = '';

  constructor(private appService: AppService,
              private router: Router,
              private sharedService: SignupSharedService) {

  }

  change(link: string) {
    this.sharedService.saveData(this.model);
    this.openTab(link);
  }

  openTab(link: string) {
    this.router.navigate([link]);
  }
}
