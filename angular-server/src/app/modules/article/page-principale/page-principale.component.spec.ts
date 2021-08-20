import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PagePrincipaleComponent } from './page-principale.component';

describe('PagePrincipaleComponent', () => {
  let component: PagePrincipaleComponent;
  let fixture: ComponentFixture<PagePrincipaleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PagePrincipaleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PagePrincipaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
