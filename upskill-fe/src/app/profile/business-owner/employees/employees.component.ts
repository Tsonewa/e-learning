import { Component, OnInit, OnDestroy } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EmployeeRegisterComponent } from '../employee-register/employee-register.component';
import {
  getEmployees,
  getEmployeesCancel,
  getEmployeesCountByCompany,
  getEmployeesCountByCompanyCancel,
  getEmployeesCountByCompanyFailed,
  getEmployeesCountByCompanySuccess,
  getEmployeesFailed,
  getEmployeesSuccess,
  resetEmployeeState,
} from '../../../+store/register-employee/action';
import { employeeSelector } from '../../../+store';
import { Store, ActionsSubject } from '@ngrx/store';
import { filter, map, switchMap, take, tap } from 'rxjs';
import { Dispatcher } from 'ngrx-dispatcher';
import { ofType } from '@ngrx/effects';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-bo-profile-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.scss'],
})
export class EmployeesComponent implements OnInit, OnDestroy {
  employees$ = this.store.select(employeeSelector.getAllEmployeeByCompany);

  currentPage$ = this.route.queryParams.pipe(
    filter((qp) => !!qp['page']),
    map((qp) => Number(qp['page']))
  );
  countEmployees$ = this.store.select(
    employeeSelector.getEmployeesCountByCompany
  );

  ngrxDispatcher: Dispatcher = {
    dispatch: ([page]: [number]) => {
      this.store.dispatch(getEmployees({ page: Number(page), limit: 3 }));
    },
    cancel: () => this.store.dispatch(getEmployeesCancel()),
    success$: this.actions.pipe(ofType(getEmployeesSuccess)),
    failed$: this.actions.pipe(ofType(getEmployeesFailed)),
    dependencies: [this.currentPage$],
  };

  countDispatcher: Dispatcher = {
    dispatch: () => {
      this.store.dispatch(getEmployeesCountByCompany());
    },
    cancel: () => this.store.dispatch(getEmployeesCountByCompanyCancel()),
    success$: this.actions.pipe(ofType(getEmployeesCountByCompanySuccess)),
    failed$: this.actions.pipe(ofType(getEmployeesCountByCompanyFailed)),
  };

  constructor(
    public dialog: MatDialog,
    private store: Store,
    private actions: ActionsSubject,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {   
    this.router.navigate([], {
      queryParams: { page: 1 },
    });
  }

  openDialog() {
    let dialogRef = this.dialog.open(EmployeeRegisterComponent, {
      panelClass: 'test-panel-class',
    });
  }

  clickViewMore() {
    this.currentPage$
      .pipe(
        take(1),
        switchMap((page) =>
          this.router.navigate([], { queryParams: { page: Number(page) + 1 } })
        )
      )
      .subscribe();
  }

  ngOnDestroy(): void {
    this.store.dispatch(resetEmployeeState());
  }
}
