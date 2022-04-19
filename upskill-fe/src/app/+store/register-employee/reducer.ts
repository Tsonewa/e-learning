import { createReducer, on, Action } from '@ngrx/store';
import { IEmployeeRegisterState } from '../interface/state';
import * as registerAction from './action';

const initialState: IEmployeeRegisterState = {
  employees: [],
  countEmployees: 0,
};

const registerReducer = createReducer(
  initialState,
  on(registerAction.registerSuccess, (state, users) => {
    return { ...state, employees: [...state.employees, ...users.employees] };
  }),
  on(registerAction.getEmployeesSuccess, (state, { employees }) => {
    return { ...state, employees: [...employees, ...state.employees] };
  }),
  on(registerAction.resetEmployeeState, (state) => ({
    ...state,
    employees: [],
  })),
  on(
    registerAction.getEmployeesCountByCompanySuccess,
    (state, { countEmployees }) => {     
      return { ...state, countEmployees };
    }
  )
);

export const featureKey = 'register';

export function reducer(
  state: IEmployeeRegisterState,
  action: Action
): IEmployeeRegisterState {
  return registerReducer(state, action);
}
