package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{

}
