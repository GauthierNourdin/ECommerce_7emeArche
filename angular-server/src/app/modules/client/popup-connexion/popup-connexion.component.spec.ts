import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupConnexionComponent } from './popup-connexion.component';

describe('PopupConnexionComponent', () => {
  let component: PopupConnexionComponent;
  let fixture: ComponentFixture<PopupConnexionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopupConnexionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupConnexionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
