import {Component, OnInit} from "@angular/core";
import {AppService} from "../../app.service";
import {IOption} from "../../interfaces/options";
import {Router} from "@angular/router";
import {OptionsSharedService} from "./options-shared.service";

@Component({
  moduleId: module.id,
  selector: 'options',
  templateUrl: './options.component.html'
})
export class OptionsComponent implements OnInit {
  options: IOption = null;

  constructor(private appService: AppService,
              private router: Router,
              private sharedService: OptionsSharedService) {

  }

  ngOnInit() {
    this.init()
  }

  init() {
    this.appService.getAllOptions().then(data =>
    this.options = data.json() as IOption);
  }

  change(id: number, link: string) {
    this.sharedService.saveData(id);
    this.openTab(link);
  }

  openTab(link: string) {
    this.router.navigate([link]);
  }
}
