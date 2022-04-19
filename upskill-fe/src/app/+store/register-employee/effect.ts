import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { Router } from '@angular/router';
import { catchError, map, switchMap, takeUntil, tap } from 'rxjs/operators';
import { BusinessOwnerService } from '../../core/service';
import {
  ActionTypes,
  register,
  registerSuccess,
  registerFailed,
  getEmployees,
  getEmployeesCancel,
  getEmployeesCountByCompany,
  getEmployeesCountByCompanyCancel,
} from './action';
import { of } from 'rxjs';
import { IEmployeeRegisterListView } from 'src/app/core/model';

@Injectable()
export class EmployeeRegisterEffect {
  constructor(
    private actions$: Actions,
    private service: BusinessOwnerService,
    private router: Router
  ) {}

  register$ = createEffect(() =>
    this.actions$.pipe(
      ofType(register),
      switchMap(({ employees }) => {
        return this.service.employeeRegister({ employees }).pipe(
          switchMap((payload) => {
            this.router.navigate(['home']);
            return [registerSuccess(payload as IEmployeeRegisterListView)];
          }),
          catchError((err) => of({ type: ActionTypes.registerFailed, ...err }))
        );
      })
    )
  );

  getEmployeesByCompany = createEffect(() =>
    this.actions$.pipe(
      ofType(getEmployees),
      switchMap(({ page, limit }) => {
        return this.service.getAllEmployeeByCompany({ page, limit }).pipe(
          takeUntil(this.actions$.pipe(ofType(getEmployeesCancel))),
          map(({ employees }) => ({
            type: ActionTypes.getEmployeesSuccess,
            employees,
          }))
        );
      }),
      catchError((err) =>
        of({ type: ActionTypes.getEmployeesFailed, ...err })
      )
    )
  );

  getEmployeeCountByCompany$ = createEffect(() =>
    this.actions$.pipe(
      ofType(getEmployeesCountByCompany),
      switchMap(() => {
        return this.service.getCountEmployeesByCompany().pipe(
          takeUntil(
            this.actions$.pipe(ofType(getEmployeesCountByCompanyCancel))
          ),
          switchMap(({ countEmployees }) => {
            return [
              {
                type: ActionTypes.getEmployeesCountByCompanySuccess,
                countEmployees,
              },
            ];
          })
        );
      }),
      catchError((err) =>
        of({ type: ActionTypes.getEmployeesCountByCompanyFailed, ...err })
      )
    )
  );
}
