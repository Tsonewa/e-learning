import {Component, Inject, Input, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../core/service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.scss']
})
export class EditProfileComponent implements OnInit {

  formEdit: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authservice: AuthService,
    private router: Router,
    private dialogRef: MatDialogRef<EditProfileComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { details: any }) {
    console.log('USER details' + data.details.fullName);
  }

  ngOnInit(): void {
    this.formEdit = this.fb.group({
      fullName: ['', [Validators.minLength(4)]],
      picture: [''],
      summary: ['', [Validators.min(10)]],
      password: ['', [Validators.min(8)]],
      confirmPassword: ['', [Validators.min(8)]]
    });
  }

  save(id: string): any {

    const payload = new FormData();
    payload.append('picture', this.formEdit.get('picture').value);
    payload.append('fullName', this.formEdit.get('fullName').value);
    payload.append('summary', this.formEdit.get('summary').value);
    payload.append('password', this.formEdit.get('password').value);
    payload.append('confirmPassword', this.formEdit.get('confirmPassword').value);
    this.authservice.editProfile(id, payload)
      .subscribe((data) => {
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigate(['home']);
      });
    this.dialogRef.close();
  }

  close(): void {
    this.dialogRef.close();
  }

  onFileSelected(event): void {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.formEdit.patchValue({
        picture: file
      });
    }
  }

}
