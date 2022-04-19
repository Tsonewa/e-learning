import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoachItemComponent } from './coach-item.component';

describe('CoachItemComponent', () => {
  let component: CoachItemComponent;
  let fixture: ComponentFixture<CoachItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoachItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoachItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
