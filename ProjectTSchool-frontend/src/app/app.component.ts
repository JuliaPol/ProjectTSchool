import {Component, OnInit} from '@angular/core';
import {IUser} from "./interfaces/user";
import {AppService} from "./app.service";
import 'clarity-icons';
import 'clarity-icons/shapes/all-shapes';

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
