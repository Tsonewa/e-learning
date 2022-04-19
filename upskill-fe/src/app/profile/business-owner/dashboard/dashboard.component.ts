import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { logout } from '../../../+store/login/action';
import { Router } from '@angular/router';


@Component({
  selector: 'app-business-owner-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  constructor(private store: Store, private router: Router) {}

  ngOnInit(): void {}

  logout() {
    this.store.dispatch(logout());
    localStorage.removeItem('token');
    this.router.navigate(['home']);
  }
}
