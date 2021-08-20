import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ArticleModule } from './modules/article/article.module';
import { ClientModule } from './modules/client/client.module';
import { PanierModule } from './modules/panier/panier.module';
import { ErreurModule } from './modules/erreur/erreur.module';
import { CommunModule } from './modules/commun/commun.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ArticleModule,
    ClientModule,
    PanierModule,
    ErreurModule,
    CommunModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
