import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeCatalogComponent } from './employee-catalog.component';

describe('EmployeeCatalogComponent', () => {
  let component: EmployeeCatalogComponent;
  let fixture: ComponentFixture<EmployeeCatalogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeeCatalogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeCatalogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
