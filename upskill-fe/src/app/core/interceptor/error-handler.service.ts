import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { concat, Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root',
})
export class NotificationHandlerService implements HttpInterceptor {
  constructor(public toastr: ToastrService) {}
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((err) => {
        let msg = err['error']['message'];
        if (err['message'].includes('/auth/token')) {
          msg = 'Invalid credentials';
          this.toastr.error(msg, 'Error!');
        }
        return throwError(() => err);
      })
    );
  }
}
