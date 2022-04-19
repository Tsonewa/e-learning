import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../../core/service/auth.service';

@Component({
  selector: 'app-home-authenticated',
  templateUrl: './home-authenticated.component.html',
  styleUrls: ['./home-authenticated.component.scss'],
})
export class HomeAuthenticatedComponent implements OnInit {
  isBusinessOwner$: Observable<boolean> = this.service.isBusinessOwner$();
  isEmployee$: Observable<boolean> = this.service.isEmployee$();
  isAdmin$: Observable<boolean> = this.service.isAdmin$();

  constructor(private service: AuthService) {}

  ngOnInit(): void {}
}
