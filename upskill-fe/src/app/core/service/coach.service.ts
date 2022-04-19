import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ICoaches} from '../model/coaches-view';
import {ICoachesCreate} from '../model/coaches-create';
import {environment} from '../../../environments/environment';
import {Router} from '@angular/router';
import {ICoachesEdit} from '../model/coach-edit';
import { ICoachesDetails } from '../model/coaches-details';
import {catchError} from "rxjs/operators";
import {ATTENTION, ERROR_MESSAGE} from "../../coach/constants/constants";
import {ToastrService} from "ngx-toastr";

const allCoachesByAdmin = '/coaches/coaches/all-admin';
const createCoach = '/coaches/coaches/create';
const allCoachesByBo = '/coaches/coaches/bo-profile';
const addCoach = '/coaches/coaches/bo-add';
const allCoachesByAdminBo = '/coaches/coaches/all-bo-admin';
const deleteCoach = '/coaches/coaches/delete-by-admin';
const removeCoach = '/coaches/coaches/bo-delete';
const search = '/coaches/coaches/search';
const createPicture = '/coaches/coaches/create/picture';
const getCoach = '/coaches/coaches/coach';
const editCoach = '/coaches/coaches/edit';
const allCoachesByEmployee = '/coaches/coaches/coaches-employee';
const getCompanyOwnerEmail = '/profile/profile/employee/companyOwner';
const getSession = '/coaches/coaches/coach-session';
const postBookCoachSession = '/coaches/coaches/employee-book-session';
const getCoachDetails = '/coaches/coaches/details-by-employee';
const postBookCoach = '/coaches/coaches/employee-book';
const sendFeedback = '/coaches/coaches/feedback';
const randomCoaches = '/coaches/coaches/employee/dashboard';

@Injectable({
  providedIn: 'root',
})
export class CoachService {

  constructor(private http: HttpClient,
              private router: Router,  private toastr: ToastrService) {
  }

  getEmployeeRandomCoaches(boEmail): Observable<Array<ICoachesCreate>> {
    let searchParams = new HttpParams();
    searchParams = searchParams.append('boEmail', boEmail);

    return this.http.get<Array<ICoachesCreate>>(environment.apiUrl + randomCoaches, {params: searchParams});
  }

  getAllCoachesByAdmin(): Observable<Array<ICoaches>> {
    return this.http.get<Array<ICoaches>>(
      environment.apiUrl + allCoachesByAdmin
    );
  }

  getAllCoachesByBo(): Observable<Array<ICoachesCreate>> {
    return this.http.get<Array<ICoachesCreate>>(
      environment.apiUrl + allCoachesByBo
    );
  }

  getAllCoachesByAdminByBo(): Observable<Array<ICoaches>> {
    return this.http.get<Array<ICoaches>>(
      environment.apiUrl + allCoachesByAdminBo
    );
  }

  getAllCoachesByEmployee(
    emailCompanyOwner
  ): Observable<Array<ICoachesCreate>> {
    const get = environment.apiUrl + allCoachesByEmployee;
    let searchParams = new HttpParams();
    searchParams = searchParams.append('emailCompanyOwner', emailCompanyOwner);

    return this.http.get<Array<ICoachesCreate>>(get, {
      params: searchParams,
    });
  }

  getAllCoachSession(id: string): any {
    const get = environment.apiUrl + getSession;
    let searchParams = new HttpParams();
    searchParams = searchParams.append('id', id);

    return this.http.get(get, {
      params: searchParams,
    });
  }

  getCompanyOwnerEmail(): any {
    return this.http.get(environment.apiUrl + getCompanyOwnerEmail, {
      responseType: 'text',
    });
  }

  createCoach(data, payload): any {
    return this.http
      .post(environment.apiUrl + createCoach, data, {
        observe: 'response',
        responseType: 'text',
      })
      .subscribe(
        (result) => {
          if (result.status === 201) {
            const filename = JSON.parse(result.body).id;
            payload.append('id', filename);
            this.http
              .put(environment.apiUrl + createPicture, payload, {
                observe: 'response',
              })
              .subscribe((res) => {});
          }
        }, error => {
          this.toastr.error(ERROR_MESSAGE, ATTENTION, {timeOut: 5000000});
        });
  }

  addCoachToBo(id: string): any {
    const add = environment.apiUrl + addCoach;
    let searchParams = new HttpParams();
    searchParams = searchParams.append('id', id);

    return this.http.get(add, {
      params: searchParams,
    });
  }

  deleteCoachByAdmin(id: string): any {
    const add = environment.apiUrl + deleteCoach;
    let searchParams = new HttpParams();
    searchParams = searchParams.append('id', id);

    return this.http.delete(add, {
      params: searchParams,
    });
  }

  deleteCoachByBo(id: string): any {
    const add = environment.apiUrl + removeCoach;
    let searchParams = new HttpParams();
    searchParams = searchParams.append('id', id);

    return this.http.delete(add, {
      params: searchParams,
    });
  }

  searchByCriteria(data): any {
    return this.http.post<Array<ICoachesCreate>>(
      environment.apiUrl + search,
      data
    );
  }

  editCoach(id, data): any {
    const edit = environment.apiUrl + editCoach;
    let searchParams = new HttpParams();
    searchParams = searchParams.append('id', id);

    return this.http.put(edit, data, {
      params: searchParams,
    });
  }

  getCoachById(id: string): any {
    const get = environment.apiUrl + getCoach;
    let searchParams = new HttpParams();
    searchParams = searchParams.append('id', id);

    return this.http.get<ICoachesEdit>(get, {
      params: searchParams,
    });
  }

  bookCoachSession(idCoach: string, id: string): any {
    const post = environment.apiUrl + postBookCoachSession;
    // const params = new HttpParams().set('idCoach', idCoach).set('id', id);
    return this.http.post(post, { idCoach, id });
  }

  getCoachDetails(id: string): Observable<ICoachesDetails> {
    const get = environment.apiUrl + getCoachDetails;
    let searchParams = new HttpParams();
    searchParams = searchParams.append('id', id);
    return this.http.get<ICoachesDetails>(get, {
      params: searchParams,
    });
  }

  bookCoach(id: string): any {
    const post = environment.apiUrl + postBookCoach;
    return this.http.post(post, { id });
  }

  sendFeedback(id, data): any {
    const send = environment.apiUrl + sendFeedback;
    let searchParams = new HttpParams();
    searchParams = searchParams.append('id', id);

    return this.http.post(send, data, {
      params: searchParams,
    });
  }
}
