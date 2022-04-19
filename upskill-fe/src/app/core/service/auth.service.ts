import { HttpResponse, HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ILogin, ICheckCompanyName, ICurrentUser } from '../model';
import { JwtHelperService } from '@auth0/angular-jwt';
import { environment } from '../../../environments/environment';
import { authSelector } from '../../+store';
import { Store } from '@ngrx/store';
import { map, mergeMap, take,switchMap, tap } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';

const LOGIN = '/auth/token';
const CHECK_COMPANY = '/company/name';
const GET_CURRENT_USER = '/profile/user';
const editProfile = '/profile/profile/edit';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(
    private http: HttpClient,
    private jwtService: JwtHelperService,
    private store: Store,
    private toastr:ToastrService,
  ) {}

  login(user: ILogin): Observable<HttpResponse<ILogin>> {
    return this.http
      .post<any>(environment.apiUrlAuth + LOGIN, user, {
        observe: 'response',
      })
      .pipe(
        tap(() => this.toastr.success('Successful login!')))
  }

  getTokenData(token: string) {
    if (!token) {    
      return;
    }
    const decodeToken = this.jwtService.decodeToken(token);
    const role = decodeToken.role;
    const email = decodeToken.email;
    localStorage.setItem('token', token.substring(token.indexOf(' ')));
    return {
      email,
      role,
      token: token.substring(token.indexOf(' ')),
    };
  }

  findCompanyByName(companyName: ICheckCompanyName): Observable<any> {
    return this.http.post<any>(
      environment.apiUrlProfile + CHECK_COMPANY,
      companyName
    );
  }

  isAuthenticated$(): Observable<any> {
    return this.store.select(authSelector.getToken);
  }

  isEmployee$(): Observable<boolean> {
    return this.store
      .select(authSelector.getRole)
      .pipe(
        map((r) => (r == null || !r.includes('EMPLOYEE_ROLE') ? false : true))
      );
  }

  isBusinessOwner$(): Observable<boolean> {
    return this.store
      .select(authSelector.getRole)
      .pipe(
        map((r) => (r == null || !r.includes('COMPANY_ROLE') ? false : true))
      );
  }

  isAdmin$(): Observable<boolean> {
    return this.store
      .select(authSelector.getRole)
      .pipe(
        map((r) => (r == null || !r.includes('ADMIN_ROLE') ? false : true))
      );
  }

  getCurrentUser$(): Observable<ICurrentUser> {
    return this.http.get<ICurrentUser>(
      environment.apiUrlProfile + GET_CURRENT_USER
    );
  }

  editProfile(id , data): any {
    const edit = environment.apiUrl + editProfile;
    let searchParams = new HttpParams();
    searchParams = searchParams.append('id', id);

    return this.http.put(edit, data, {
      params: searchParams
    });
  }
}
