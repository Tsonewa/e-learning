import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service';
import { NavigationStart, Router } from '@angular/router';
import { Observable } from 'rxjs';
import{ICurrentUser} from '../../model';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  isAuthenticated$:Observable<any>;
  isAdmin$: Observable<boolean> = this.authService.isAdmin$();
  hideButtonsForLogin: boolean;
  currentUser$:Observable<ICurrentUser>;

  constructor(private authService: AuthService, private router: Router) {
     this.currentUser$=this.authService.getCurrentUser$();
  }

  ngOnInit(): void {
    this.isAuthenticated$ = this.authService.isAuthenticated$();
    this.router.events.forEach((event) => {
      if (event instanceof NavigationStart) {
        if (event.url === '/login') {
          this.hideButtonsForLogin = false;
        } else {
          this.hideButtonsForLogin = true;
        }
      }
    });
  }
}
