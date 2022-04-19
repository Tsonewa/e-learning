import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../service/index';

@Injectable({
  providedIn: 'root',
})
export class JwtInterceptorService {

  constructor( private authService: AuthService) {
  }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const cloneReq = req.clone({
      setHeaders: {
        Authorization: localStorage.getItem('token'),
      },
    });
    return next.handle(cloneReq);
  }
}
