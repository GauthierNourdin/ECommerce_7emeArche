package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Article;

/**
 * 
 * @author Gauthier Nourdin.
 *
 */
public interface ArticleRepository extends JpaRepository<Article, Long>{

}
