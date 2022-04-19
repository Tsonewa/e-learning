import {
  AsyncValidatorFn,
  AbstractControl,
  ValidationErrors,
} from '@angular/forms';
import { Observable, of } from 'rxjs';
import { AuthService } from '../service/auth.service';
import { map, tap } from 'rxjs/operators';

export function companyNameAsyncValidator(
  service: AuthService
): AsyncValidatorFn {
  return (control: AbstractControl): Observable<ValidationErrors | null> => {
    return service.findCompanyByName({ companyName: control.value }).pipe(
      map((v) => {
        return v == true ? { companyExist: true } : null;
      })
    );
  };
}
