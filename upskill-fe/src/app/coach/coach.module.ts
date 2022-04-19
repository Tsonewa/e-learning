import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {RouterModule} from '@angular/router';
import {DeleteDialogComponent} from './coach-item/delete-dialog/delete-dialog.component';
import {DialogElementsComponent} from './coach-item/dialog-elements/dialog-elements.component';
import {MatDialogModule, MAT_DIALOG_DEFAULT_OPTIONS} from '@angular/material/dialog';
import {MatCommonModule} from '@angular/material/core';
import {CoachAllComponent} from './coach-all/coach-all.component';
import {CoachEditComponent} from './coach-edit/coach-edit.component';
import {CoachDetailsComponent} from './coach-details/coach-details.component';
import {CoachFeedbackComponent} from './coach-feedback/coach-feedback.component';
import {CoachCatalogComponent} from './coach-catalog/coach-catalog.component';
import {CoachProfileBOComponent} from './coach-profile-bo/coach-profile-bo.component';
import {CoachDescriptionComponent} from './coach-description/coach-description.component';
import {CoachService} from '../core/service/coach.service';
import {ReactiveFormsModule} from '@angular/forms';
import {CoachItemComponent} from './coach-item/coach-item.component';
import {CoachItemBoComponent} from './coach-item-bo/coach-item-bo.component';
import {SpinnerComponent} from './spinner/spinner.component';
import {CoachSearchComponent} from './coach-search/coach-search.component';
import {CalenderComponent} from './calender/calender.component';
import {ProfileModule} from '../profile/profile.module';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {EmployeeDashboardComponent} from "../course/employee-dashboard/employee-dashboard.component";
import {CourseModule} from "../course/course.module";

@NgModule({
  declarations: [
    CoachAllComponent,
    CoachEditComponent,
    CoachDetailsComponent,
    CoachFeedbackComponent,
    CoachCatalogComponent,
    CoachProfileBOComponent,
    CoachDescriptionComponent,
    CoachItemComponent,
    CoachItemBoComponent,
    SpinnerComponent,
    CoachSearchComponent,
    CalenderComponent,
    DeleteDialogComponent,
    DialogElementsComponent, CoachItemComponent,
    DialogElementsComponent,
    EmployeeDashboardComponent,
    ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild([
      {path: 'all-admin', component: CoachAllComponent},
      {path: 'coach/catalog', component: CoachCatalogComponent},
      {path: 'bo-profile', component: CoachProfileBOComponent},
      {path: 'employee-profile', component: CoachDescriptionComponent},
      {path: 'edit/:id', component: CoachEditComponent},
      {path: 'calender/:id', component: CalenderComponent},
      {path: 'feedback/:id', component: CoachFeedbackComponent},
    ]),
    ProfileModule,
    MatDialogModule,
    MatCommonModule,
    MatIconModule,
    MatButtonModule,
    CourseModule,
  ],
  providers: [
    CoachService,
    DatePipe,
    {provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}},
    DialogElementsComponent
  ],
  entryComponents: [DeleteDialogComponent]
})
export class CoachModule {
}
