import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { HomeModule } from './home/home.module';
import { AuthModule } from './auth/auth.module';
import { ProfileModule } from './profile/profile.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtModule } from '@auth0/angular-jwt';
import { CourseModule } from './course/course.module';
import { CoachModule } from './coach/coach.module';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { EmployeeRegisterEffect } from './+store/register-employee/effect';
import { AuthEffects } from './+store/login/effect';
import { reducers, metaReducers } from './+store/metareducer';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { ToastrModule } from 'ngx-toastr';
import { NotificationHandlerService } from './core/interceptor/error-handler.service';


@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    CoreModule,
    HomeModule,
    AuthModule,
    ProfileModule,
    AuthModule,
    CourseModule,
    CoachModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return localStorage.getItem('token');
        },
        allowedDomains: ['http://localhost:8080', 'http://localhost:9003'],
        skipWhenExpired: true,
      },
    }),
    BrowserAnimationsModule,
    StoreModule.forRoot(reducers, { metaReducers }),
    EffectsModule.forRoot([AuthEffects, EmployeeRegisterEffect]),
    StoreDevtoolsModule.instrument({}),
    ToastrModule.forRoot({}),
  ],
  providers: [  
    {
      provide: HTTP_INTERCEPTORS,
      useClass: NotificationHandlerService,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
