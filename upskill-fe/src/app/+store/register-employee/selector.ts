import {IEmployeeRegisterState} from '../interface/state'
export const getAllEmployeeByCompany = (state: IEmployeeRegisterState) => state.employees;
export const getEmployeesCountByCompany = (state: IEmployeeRegisterState) => state.countEmployees;