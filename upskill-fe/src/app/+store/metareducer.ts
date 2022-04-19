import { ActionReducer, ActionReducerMap, MetaReducer } from '@ngrx/store';
import { localStorageSync } from 'ngrx-store-localstorage';
import {reducer} from './login/reducer';
import {reducer as employeeRegisterReducer} from './register-employee/reducer';
import {IState} from './interface/state';

export const reducers: ActionReducerMap<IState> = { auth:reducer, register: employeeRegisterReducer};

export function localStorageSyncReducer(
  reducer: ActionReducer<any>
): ActionReducer<any> {
  return localStorageSync({ keys: ['auth'],rehydrate:true})(reducer);
}
export const metaReducers: Array<MetaReducer<any, any>> = [localStorageSyncReducer];
