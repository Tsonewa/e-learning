import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Observable, pipe} from 'rxjs';
import {ICoaches} from '../../core/model/coaches-view';
import {CoachService} from '../../core/service/coach.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import {catchError} from 'rxjs/operators';
import {ICoachesCreate} from '../../core/model/coaches-create';
import {ToastrService} from "ngx-toastr";
import {ATTENTION, ERROR_MESSAGE} from "../constants/constants";

@Component({
  selector: 'app-coach-catalog',
  templateUrl: './coach-catalog.component.html',
  styleUrls: ['./coach-catalog.component.scss']
})
export class CoachCatalogComponent implements OnInit {
  adCoaches$: Observable<Array<ICoaches>>;
  boCoaches$: Observable<Array<ICoachesCreate>>;
  searchs;
  interval;
  loading = true;
  categories: string[] = [];
  languages: string[] = [];
  form: FormGroup;
  data: Observable<Array<ICoaches>>;

  constructor(private coachService: CoachService, private fb: FormBuilder, private router: Router,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    if (this.loading) {
      this.interval = setTimeout(() => {
        this.adCoaches$ = this.coachService.getAllCoachesByAdminByBo();
        this.boCoaches$ = this.coachService.getAllCoachesByBo();
        this.loading = false;
      }, 2000);
    }
    this.form = this.getForm();
  }

  getForm(data: any = null): any {
    data = data || {categories: [], languages: []};
    return this.fb.group({
      categories: [data.categories[0]],
      languages: [data.languages[0]]
    });
  }

  select(event): void {
    const search = event.target.value;
    if (event.target.checked) {
      this.categories.push(search);
    } else {
      const index = this.categories.findIndex(c => c === search);

      this.categories.splice(index, 1);
    }
    this.form.patchValue({
      categories: this.categories
    });
    this.getData();
  }

  secondSelect(event): void {
    const search = event.target.value;

    if (event.target.checked) {
      this.languages.push(search);
    } else {
      const index = this.languages.findIndex(c => c === search);

      this.languages.splice(index, 1);
    }
    this.form.patchValue({
      languages: this.languages
    });
    this.getData();
  }

  getData(): void {
    this.coachService.searchByCriteria(this.form.value)
      .subscribe(res => {
        this.searchs = res;
      }, error => {
        this.toastr.error(ERROR_MESSAGE, ATTENTION, {timeOut: 5000000});
      });
  }

  backCatalog(): void {
    const currentUrl = this.router.url;
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate([currentUrl]);
  }

  reloadChange($event): void {
    this.adCoaches$ = this.coachService.getAllCoachesByAdminByBo();
    this.boCoaches$ = this.coachService.getAllCoachesByBo();
  }

  reloadSearchChange($event): void {
    this.coachService.searchByCriteria(this.form.value)
      .subscribe(res => {
        this.searchs = res;
      });
  }
}
