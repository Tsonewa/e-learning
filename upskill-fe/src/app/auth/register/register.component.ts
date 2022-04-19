import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../core/service/auth.service';
import { BusinessOwnerService } from '../../core/service';
import { IBusinessOwnerRegister } from '../../core/model/business-owner-register';
import * as custom from '../../core/validator/index';
import { switchMap, map, tap, take } from 'rxjs/operators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    public service: AuthService,
    private BOservice: BusinessOwnerService
  ) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group(
      {
        fullName: ['', [Validators.required]],
        companyName: [
          '',
          [Validators.required],
          custom.companyNameAsyncValidator(this.service),
          { updateOn: 'blur' },
        ],
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required]],
        confirmPassword: [''],
      },
      {
        validators: custom.confirmCustomValidator(
          'password',
          'confirmPassword'
        ),
      }
    );
 }

  register() {
    if (this.registerForm.invalid) {
      return;
    }

    const user: IBusinessOwnerRegister = { ...this.registerForm.value };

    this.BOservice.initRegister(user)
      .pipe(
        take(1),
        switchMap(({ companyName }) => {
          return this.BOservice.finishRegister(companyName).pipe(take(1));
        })
      )
      .subscribe((v) => {
        if (!v) {
          this.router.navigate(['login']);
          return;
        }
        this.router.navigate(['register']);
      });
  }

  get f() {
    return this.registerForm.controls;
  }

  get av() {
    return this.registerForm.get('companyName');
  }
}
