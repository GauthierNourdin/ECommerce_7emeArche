package fr.pythie.webservice.interfaces.service.userinterface;

import java.util.List;

import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.model.Article;

public interface ArticleService {

	public List<Article> obtenirListeArticleParDefaut() throws LectureBaseDonneesException;
	
}
