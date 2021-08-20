import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageCommandeComponent } from './page-commande.component';

describe('PageCommandeComponent', () => {
  let component: PageCommandeComponent;
  let fixture: ComponentFixture<PageCommandeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageCommandeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageCommandeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
