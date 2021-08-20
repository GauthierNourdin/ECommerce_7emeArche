import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientRoutingModule } from './client-routing.module';
import { PopupConnexionComponent } from './popup-connexion/popup-connexion.component';
import { PageInscriptionComponent } from './page-inscription/page-inscription.component';
import { PageInformationsPersonnellesComponent } from './page-informations-personnelles/page-informations-personnelles.component';
import { PageCommandeComponent } from './page-commande/page-commande.component';


@NgModule({
  declarations: [PopupConnexionComponent, PageInscriptionComponent, PageInformationsPersonnellesComponent, PageCommandeComponent],
  imports: [
    CommonModule,
    ClientRoutingModule
  ]
})
export class ClientModule { }
