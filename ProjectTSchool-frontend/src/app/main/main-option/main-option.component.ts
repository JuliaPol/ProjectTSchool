import {Component, OnInit} from "@angular/core";
import "rxjs/add/operator/map";
import {Router} from "@angular/router";
import {IOption} from "../../interfaces/options";
import {AppService} from "../../app.service";

@Component({
  moduleId: module.id,
  selector: 'main-option',
  templateUrl: './main-option.component.html'
})
export class MainOptionComponent implements OnInit {

  options: IOption[];

  constructor(private appService: AppService,
              private router: Router) {
  }

  ngOnInit() {
    this.init();
  }

  init() {
    this.appService.getAllOptions().then(data => {
        this.options = data.json() as IOption[];
      }
    )
  }

  buy() {
    this.router.navigate(['/customer']);
  }
}
