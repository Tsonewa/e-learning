import { createReducer, on, Action } from '@ngrx/store';
import * as authAction from './action';
import {ILoginState} from '../interface/state';

const initialState:ILoginState ={
  email:'',
  role: '',
  token: '',
}

 const authReducer = createReducer(
   initialState,
   on(authAction.loginSuccess, (state, { user }) => ({ ...state, ...user })),
   on(authAction.logout, () => ({email:null,role: null,token:null }))  
 );


export const featureKey = 'auth';

export function reducer(state: ILoginState, action: Action): ILoginState {
  return authReducer(state, action);
}
