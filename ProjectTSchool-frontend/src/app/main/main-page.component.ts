import {Component, OnInit} from "@angular/core";
import "rxjs/add/operator/map";

@Component({
  moduleId: module.id,
  selector: 'main-page',
  templateUrl: './main-page.component.html'
})
export class MainPageComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
    this.init();
  }

  init() {

  }


}
