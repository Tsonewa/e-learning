import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EmployeeRegisterComponent } from './business-owner/employee-register/employee-register.component';
import { NgxCsvParserModule } from 'ngx-csv-parser';
import { DashboardComponent } from './business-owner/dashboard/dashboard.component';
import { EmployeesComponent } from './business-owner/employees/employees.component';
import { DashboardEmployeeComponent } from './employee/dashboard-employee/dashboard-employee.component';
import { DashboardAdminComponent } from './admin/dashboard-admin/dashboard-admin.component';
import { ProfileAsideComponent } from './profile-aside/profile-aside.component';
import { ClientsComponent } from './admin/clients/clients.component';
import { RevenueComponent } from './admin/revenue/revenue.component';
import { InvoiceComponent } from './business-owner/invoice/invoice.component';
import { AchievementsComponent } from './employee/achievements/achievements.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatCardModule } from '@angular/material/card'; 
import { MatToolbarModule } from '@angular/material/toolbar'; 
import { MatListModule } from '@angular/material/list'; 
 import { MatDividerModule } from '@angular/material/divider';
 import { MatInputModule } from '@angular/material/input';  
 import { MatButtonModule } from '@angular/material/button';
 import { NgrxDispatcherModule } from 'ngrx-dispatcher';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import {MatIconModule} from "@angular/material/icon";
import { MatFormFieldModule } from '@angular/material/form-field';


const materialModules = [
  MatDialogModule,
  MatSidenavModule,
  MatCardModule,
  MatToolbarModule,
  MatListModule,
  MatDividerModule,
  MatInputModule,
  MatButtonModule,
];


@NgModule({
  declarations: [
    EmployeeRegisterComponent,
    DashboardComponent,
    EmployeesComponent,
    DashboardAdminComponent,
    DashboardEmployeeComponent,
    ProfileAsideComponent,
    ClientsComponent,
    RevenueComponent,
    InvoiceComponent,
    AchievementsComponent,
    EditProfileComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgxCsvParserModule,   
    NgrxDispatcherModule,
    NgxCsvParserModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule,
    MatIconModule,
    ...materialModules,
    RouterModule.forChild([
      {path: 'employee-register', component: EmployeeRegisterComponent},
      {path: 'dashboard-company', component: DashboardComponent},
      {path: 'dashboard-employee', component: DashboardEmployeeComponent},
      {path: 'dashboard-admin', component: DashboardAdminComponent},
      {path: 'clients', component: ClientsComponent},
      {path: 'revenue', component: RevenueComponent},
      {path: 'employees', component: EmployeesComponent},
      {path: 'invoice', component: InvoiceComponent},
      {path: 'achievements', component: AchievementsComponent},
    ]),
  ],
  exports: [
    EmployeeRegisterComponent,
    DashboardComponent,
    EmployeesComponent,
    DashboardAdminComponent,
    DashboardEmployeeComponent,
    ProfileAsideComponent,
  ],
  entryComponents: [EditProfileComponent]
})
export class ProfileModule {}
