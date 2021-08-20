import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ArticleRoutingModule } from './article-routing.module';
import { PagePrincipaleComponent } from './page-principale/page-principale.component';
import { PageArticleComponent } from './page-article/page-article.component';


@NgModule({
  declarations: [PagePrincipaleComponent, PageArticleComponent],
  imports: [
    CommonModule,
    ArticleRoutingModule
  ]
})
export class ArticleModule { }
