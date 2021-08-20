import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ErreurRoutingModule } from './erreur-routing.module';
import { PageErreurComponent } from './page-erreur/page-erreur.component';


@NgModule({
  declarations: [PageErreurComponent],
  imports: [
    CommonModule,
    ErreurRoutingModule
  ]
})
export class ErreurModule { }
