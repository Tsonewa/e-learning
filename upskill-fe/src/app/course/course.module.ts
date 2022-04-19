import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AdminAllComponent} from './admin-all/admin-all.component';
import {RouterModule} from '@angular/router';
import {CourseItemComponent} from './course-item/course-item.component';
import {CreateCourseComponent} from './create-course/create-course.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {CourseDetailsComponent} from './course-details/course-details.component';
import {CourseCatalogComponent} from './course-catalog/course-catalog.component';
import {EditCourseComponent} from './edit-course/edit-course.component';
import {CompanyProfileComponent} from './bo-profile/company-profile.component';
import {DeleteDialogComponent} from './course-item/delete-dialog/delete-dialog.component';
import {DialogElementsComponent} from './course-item/dialog-elements/dialog-elements.component';
import {MatDialogModule, MAT_DIALOG_DEFAULT_OPTIONS} from '@angular/material/dialog';
import {MatCommonModule} from '@angular/material/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ProfileModule} from '../profile/profile.module';
import {SpinnerComponent} from './spinner/spinner.component';
import {MatIconModule} from '@angular/material/icon';
import {CourseStreamComponent} from './course-stream/course-stream.component';
import {RemoveDialogComponent} from './course-item/remove-dialog/remove-dialog.component';
import {EmployeeCatalogComponent} from './employee-catalog/employee-catalog.component';
import {EmployeeDashboardComponent} from './employee-dashboard/employee-dashboard.component';


const routes = [
  {path: 'admin/all', component: AdminAllComponent, CreateCourseComponent, EditCourseComponent, CourseDetailsComponent},
  {path: 'course/edit/:id', component: EditCourseComponent},
  {path: 'catalog', component: CourseCatalogComponent, CourseItemComponent, CreateCourseComponent},
  {path: 'company/catalog', component: CompanyProfileComponent, DialogElementsComponent},
  {path: 'stream/:id', component: CourseStreamComponent},
  {path: 'employee/catalog', component: EmployeeCatalogComponent},
  {path: 'employee/dashboard', component: EmployeeDashboardComponent}

];

@NgModule({
  declarations: [AdminAllComponent, CourseItemComponent, CreateCourseComponent,
    CourseDetailsComponent, CourseCatalogComponent, CompanyProfileComponent,
    EditCourseComponent, DeleteDialogComponent, DialogElementsComponent, SpinnerComponent,
    CourseStreamComponent, RemoveDialogComponent, EmployeeCatalogComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    MatDialogModule,
    MatCommonModule,
    MatIconModule,
    BrowserAnimationsModule,
    // NgbModule,
    ProfileModule,
    RouterModule.forChild(routes)
  ],
  providers: [{provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}}, DialogElementsComponent],
  exports: [
    CourseItemComponent
  ],
  entryComponents: [DeleteDialogComponent]
})
export class CourseModule {
}
