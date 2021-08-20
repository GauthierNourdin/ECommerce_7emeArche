import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PanierRoutingModule } from './panier-routing.module';
import { PagePanierComponent } from './page-panier/page-panier.component';
import { PopupPaiementComponent } from './popup-paiement/popup-paiement.component';


@NgModule({
  declarations: [PagePanierComponent, PopupPaiementComponent],
  imports: [
    CommonModule,
    PanierRoutingModule
  ]
})
export class PanierModule { }
