import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ICoachesEdit} from '../../core/model/coach-edit';
import {CoachService} from '../../core/service/coach.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-coach-edit',
  templateUrl: './coach-edit.component.html',
  styleUrls: ['./coach-edit.component.scss']
})
export class CoachEditComponent implements OnInit {

  formEdit: FormGroup;
  coachId: ICoachesEdit;

  constructor(private coachService: CoachService, 
    private route: ActivatedRoute, 
    private router: Router, 
    private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(data => {
      const id = data.id;

      this.coachId = this.coachService.getCoachById(id).subscribe((res) => {
        this.coachId = res;
      });

    });

    this.formEdit = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(4)]],
      email: ['', [Validators.required, Validators.email]],
      company: ['', [Validators.required, Validators.minLength(4)]],
      picture: ['', [Validators.required]],
      price: ['', [Validators.required, Validators.min(0)]],
      topic: ['', [Validators.required]],
      startDate: ['', [Validators.required]],
      endDate: ['', [Validators.required]],
    });
  }

  editCoach(id: string): any {
    const payload = new FormData();
    payload.append('picture', this.formEdit.get('picture').value);
    payload.append('email', this.formEdit.get('email').value);
    payload.append('name', this.formEdit.get('name').value);
    payload.append('company', this.formEdit.get('company').value);
    payload.append('price', this.formEdit.get('price').value);
    payload.append('topic', this.formEdit.get('topic').value);
    payload.append('startDate', this.formEdit.get('startDate').value);
    payload.append('endDate', this.formEdit.get('endDate').value);
    this.coachService.editCoach(id, payload)
      .subscribe((data) => {
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigate(['/coach/all-admin']);
      });
  }

  get c(): any {
    return this.formEdit.controls;
  }

  onFileSelected(event): void {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.formEdit.patchValue({
        picture: file
      });
    }
  }

  get invalid(): boolean {
    return this.formEdit.invalid;
  }
}
