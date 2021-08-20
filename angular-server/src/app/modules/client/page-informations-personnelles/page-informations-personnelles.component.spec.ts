import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageInformationsPersonnellesComponent } from './page-informations-personnelles.component';

describe('PageInformationsPersonnellesComponent', () => {
  let component: PageInformationsPersonnellesComponent;
  let fixture: ComponentFixture<PageInformationsPersonnellesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageInformationsPersonnellesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageInformationsPersonnellesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
