import {Component, OnInit} from '@angular/core';
import {IUser} from "./interfaces/user";
import {AppService} from "./app.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  signOut(link: string) {
    window.location.href = link;
  }
}
