import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeNotAuthenticatedComponent } from './home-not-authenticated.component';

describe('HomeNotAuthenticatedComponent', () => {
  let component: HomeNotAuthenticatedComponent;
  let fixture: ComponentFixture<HomeNotAuthenticatedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeNotAuthenticatedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeNotAuthenticatedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
