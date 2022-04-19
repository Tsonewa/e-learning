import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgxCsvParser } from 'ngx-csv-parser';
import { NgxCSVParserError } from 'ngx-csv-parser';
import { IEmployeeRegister } from 'src/app/core/model/employee-register';
import { Store } from '@ngrx/store';
import { register } from '../../../+store/register-employee/action';

@Component({
  selector: 'app-employee-register',
  templateUrl: './employee-register.component.html',
  styleUrls: ['./employee-register.component.scss'],
})
export class EmployeeRegisterComponent implements OnInit {
  registerForm: FormGroup;

  constructor(
    private router: Router, 
    private fb: FormBuilder,
    private ngxCsvParser: NgxCsvParser,
    private store: Store
  ) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      employees: this.fb.array([]),
    });
    this.addEmployee();
  }

  register() {
    if (this.registerForm.invalid) {
      return;
    }    
    const { employees } = this.registerForm.value;
    this.store.dispatch(register({ employees }));
  }

  get employees(): FormArray {
    return this.registerForm.controls['employees'] as FormArray;
  }

  addEmployee() {
    const employee = this.fb.group({
      fullName: ['', [Validators.required]],
      email: ['', [Validators.required]],
    });
    this.employees.push(employee);   
  }

  removeEmployee(employeeIndex: number) {
    this.employees.removeAt(employeeIndex);
  }

  fileChangeListener($event: any): void {
    const files = $event.srcElement.files;
    this.ngxCsvParser
      .parse(files[0], { header: true, delimiter: ',' })
      .pipe()
      .subscribe(
        (result: Array<IEmployeeRegister>) => {
          console.log("result", result)
          this.store.dispatch(register({ employees: [...result] }));
          this.router.navigate(['/home']);         
        },
        (error: NgxCSVParserError) => {
          console.log('Error', error);
        }
      );
  }

  cancel() {
    this.registerForm.reset();
    this.router.navigate(['home']);
  }
}
