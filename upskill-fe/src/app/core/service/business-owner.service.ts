import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import {
  IBusinessOwnerRegister,
  ICountEmployeesByCompany,
  IEmployeeRegisterList,
  IEmployeeRegisterListView,
} from '../model';
import { environment } from '../../../environments/environment';

const REGISTER = '/auth/register';
const COMPANY_NAME = '/company/name';
const EMPLOYEE_CREATE = '/profile/employee/create';
const EMPLOYEES_BY_COMPANY = '/profile/get/employees-by-company';
const COUNT_EMPLOYEES_BY_COMPANY = '/profile/count/employees-by-company';
@Injectable({
  providedIn: 'root',
})
export class BusinessOwnerService {
  constructor(private http: HttpClient) {}

  initRegister(
    user: IBusinessOwnerRegister
  ): Observable<IBusinessOwnerRegister> {
    return this.http.post<IBusinessOwnerRegister>(
      environment.apiUrlAuth + REGISTER,
      user
    );
  }

  finishRegister(companyName: string): Observable<any> {
    return this.http.post<any>(environment.apiUrlProfile + COMPANY_NAME, {
      companyName,
    });
  }

  employeeRegister(
    employees: IEmployeeRegisterList
  ): Observable<IEmployeeRegisterList> {
    return this.http.post<IEmployeeRegisterList>(
      environment.apiUrlProfile + EMPLOYEE_CREATE,
      employees
    );
  }

  getAllEmployeeByCompany({page,limit}): Observable<IEmployeeRegisterListView> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append('page', page);
    queryParams = queryParams.append('limit', limit);
    return this.http.get<IEmployeeRegisterListView>(
      environment.apiUrlProfile + EMPLOYEES_BY_COMPANY,
      { params: queryParams }
    );
  }

  getCountEmployeesByCompany():Observable<ICountEmployeesByCompany>{
      return this.http.get<ICountEmployeesByCompany>(environment.apiUrlProfile+COUNT_EMPLOYEES_BY_COMPANY);  
  }
}
