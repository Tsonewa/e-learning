import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, switchMap } from 'rxjs/operators';
import {ActionTypes, login, loginFailed, loginSuccess,  } from './action';
import { AuthService } from '../../core/service/index';
import { Router } from '@angular/router';
import { of } from 'rxjs';

@Injectable()
export class AuthEffects {
  constructor(private actions$: Actions, private authService: AuthService, private router: Router) {
  }

  login$ = createEffect(() =>
    this.actions$.pipe(
      ofType(login),
      switchMap(({email, password}) =>
        this.authService.login({email, password}).pipe(
          switchMap((data) => {
            const token = data.headers.get('Authorization');
            const user = this.authService.getTokenData(token);
            this.router.navigate(['home']);
            return [loginSuccess({user})];
          }),
          catchError((error) => {
            return of({ type: ActionTypes.loginFailed, ...error });
          })
        )
      )
    )
  );
}
