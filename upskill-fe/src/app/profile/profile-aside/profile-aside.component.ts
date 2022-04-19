import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {AuthService} from '../../core/service/auth.service';
import {Store} from '@ngrx/store';
import {logout} from '../../+store/login/action';
import {Router} from '@angular/router';
import {ICurrentUser} from 'src/app/core/model';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {EditProfileComponent} from '../edit-profile/edit-profile.component';
import { getEmployees,resetEmployeeState } from 'src/app/+store/register-employee/action';


@Component({
  selector: 'app-profile-aside',
  templateUrl: './profile-aside.component.html',
  styleUrls: ['./profile-aside.component.scss'],
})
export class ProfileAsideComponent implements OnInit {
  isBusinessOwner$: Observable<boolean> = this.service.isBusinessOwner$();
  isEmployee$: Observable<boolean> = this.service.isEmployee$();
  isAdmin$: Observable<boolean> = this.service.isAdmin$();
  getCurrentUser$: Observable<ICurrentUser> = this.service.getCurrentUser$();
  user;

  constructor(
    private store: Store,
    private router: Router,
    private service: AuthService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getCurrentUser$.subscribe((data) => (this.user = data));
  }

  logout(): void {
    this.store.dispatch(logout());
    localStorage.removeItem('token');
    this.router.navigate(['home']);
  }

  editProfile(): any {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    // dialogConfig.data = {
    //    id: this.user.id,
    //    fullName: this.user.fullName,
    //    email: this.user.email,
    //    picture: this.user.picture,
    // };
    const dialogRef = this.dialog.open(EditProfileComponent, {
      panelClass: 'profile-mat-dialog-container',
      hasBackdrop: true,
      data: {
        details: {
          id: this.user.id,
          fullName: this.user.fullName,
          email: this.user.email,
          picture: this.user.picture,
          summary: this.user.summary,
        },
      },
    });

    dialogRef
      .afterClosed()
      .subscribe((data) => console.log('Dialog output:', data));
  }

  stateHandler() {
    this.store.dispatch(resetEmployeeState());
    this.store.dispatch(getEmployees({ page: 1, limit: 3 }));
  }
}
