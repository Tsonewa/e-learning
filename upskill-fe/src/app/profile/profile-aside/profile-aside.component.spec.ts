import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileAsideComponent } from './profile-aside.component';

describe('ProfileAsideComponent', () => {
  let component: ProfileAsideComponent;
  let fixture: ComponentFixture<ProfileAsideComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileAsideComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileAsideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
