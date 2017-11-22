import {Component, OnInit} from "@angular/core";
import "rxjs/add/operator/map";
import {AppService} from "../app.service";
import {Router} from "@angular/router";
import {IContract, ICustomer} from "../interfaces/customers";

@Component({
  moduleId: module.id,
  selector: 'main-page',
  templateUrl: './main-page.component.html'
})
export class MainPageComponent implements OnInit {

  activeTariff: boolean = true;
  activeOption: boolean = false;

  constructor(private appService: AppService,
              private router: Router) {
  }

  ngOnInit() {
    this.init();
  }

  init() {

  }


}
