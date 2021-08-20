import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupPaiementComponent } from './popup-paiement.component';

describe('PopupPaiementComponent', () => {
  let component: PopupPaiementComponent;
  let fixture: ComponentFixture<PopupPaiementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopupPaiementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupPaiementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
