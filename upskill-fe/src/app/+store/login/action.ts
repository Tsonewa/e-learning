import {HttpErrorResponse} from '@angular/common/http';
import {createAction, props} from '@ngrx/store';
import {ILoginState} from '../interface/state';

export const namespace = '[AUTH]';

export const ActionTypes = {
  login: '[Login]',
  loginSuccess: '[Login] Success',
  loginFailed: '[Login] Failed',
  logout: '[Logout]'
};

export const login = createAction(`${namespace} ${ActionTypes.login}`, props<{ email: string; password: string }>());
export const loginSuccess = createAction(`${namespace} ${ActionTypes.loginSuccess}`, props<{ user: ILoginState }>());
export const loginFailed = createAction(`${namespace} ${ActionTypes.loginFailed}`, props<{ error: HttpErrorResponse }>());

export const logout = createAction(`${namespace} ${ActionTypes.logout}`);
