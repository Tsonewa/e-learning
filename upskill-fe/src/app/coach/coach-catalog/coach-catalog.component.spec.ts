import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoachCatalogComponent } from './coach-catalog.component';

describe('CoachCatalogComponent', () => {
  let component: CoachCatalogComponent;
  let fixture: ComponentFixture<CoachCatalogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoachCatalogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoachCatalogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
