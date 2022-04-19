import { createSelector,createFeatureSelector } from "@ngrx/store";
import {featureKey as authFeatureKey} from './login/reducer';
import {featureKey as registerEmployeeFeatureKey} from './register-employee/reducer'
import {getToken,getRole,getEmail} from './login/selector';
import {getAllEmployeeByCompany,getEmployeesCountByCompany} from './register-employee/selector';
import {ILoginState,IEmployeeRegisterState} from './interface/state';

const authFeatureSelector = createFeatureSelector<ILoginState>(authFeatureKey);

export const authSelector={
    getToken:createSelector(authFeatureSelector,getToken),
    getEmail:createSelector(authFeatureSelector,getEmail),
    getRole:createSelector(authFeatureSelector,getRole),

}

const employeeRegisterFeatureSelector = createFeatureSelector<IEmployeeRegisterState>(registerEmployeeFeatureKey);
export const employeeSelector = {
  getAllEmployeeByCompany: createSelector(employeeRegisterFeatureSelector,getAllEmployeeByCompany),
  getEmployeesCountByCompany:createSelector(employeeRegisterFeatureSelector,getEmployeesCountByCompany),
};
