import { HttpErrorResponse } from '@angular/common/http';
import { createAction, props } from '@ngrx/store';
import {
  ICountEmployeesByCompany,
  IEmployeeRegisterList,
  IEmployeeRegisterListView,
} from '../../core/model';

export const namespace = '[REGISTER]'; 

export const ActionTypes = {
  register: `${namespace} Register`,
  registerSuccess: `${namespace} Register Success`,
  registerFailed: `${namespace} Register Failed`,

  getEmployees: `${namespace} Get Employees By Company Loading`,
  getEmployeesSuccess: `${namespace} Get Employees By Company Success`,
  getEmployeesFailed: `${namespace} Get Employees By Company Failed`,

  resetEmployeeState: `${namespace} Reset Employee State`,

  getEmployeesCancel: `${namespace} Get Employees By Company Cancel`,

  getEmployeesCountByCompany: `${namespace} Get Employee Count Loading`,
  getEmployeesCountByCompanySuccess: `${namespace} Get Employee Count Success`,
  getEmployeesCountByCompanyFailed: `${namespace} Get Employee Count Failed`,

  getEmployeesCountByCompanyCancel: `${namespace} Get Employees Count Cancel`,
};

export const register = createAction(
  ActionTypes.register,
  props<IEmployeeRegisterList>()
);
export const registerSuccess = createAction(
  ActionTypes.registerSuccess,
  props<IEmployeeRegisterListView>()
);
export const registerFailed = createAction(
  ActionTypes.registerFailed,
  props<{ error: HttpErrorResponse }>()
);

export const getEmployees = createAction(
  ActionTypes.getEmployees,
  props<{ page: number; limit: number }>()
);
export const getEmployeesSuccess = createAction(
  ActionTypes.getEmployeesSuccess,
  props<IEmployeeRegisterListView>()
);
export const getEmployeesFailed = createAction(
  ActionTypes.getEmployeesFailed,
  props<{ error: HttpErrorResponse }>()
);

export const resetEmployeeState = createAction(ActionTypes.resetEmployeeState);

export const getEmployeesCancel = createAction(
  ActionTypes.getEmployeesCancel
);

export const getEmployeesCountByCompany = createAction(
  ActionTypes.getEmployeesCountByCompany
);

export const getEmployeesCountByCompanySuccess = createAction(
  ActionTypes.getEmployeesCountByCompanySuccess,
  props<ICountEmployeesByCompany>()
);

export const getEmployeesCountByCompanyFailed = createAction(
  ActionTypes.getEmployeesCountByCompanyFailed,
  props<{ error: HttpErrorResponse }>()
);

export const getEmployeesCountByCompanyCancel = createAction(ActionTypes.getEmployeesCountByCompanyCancel);


