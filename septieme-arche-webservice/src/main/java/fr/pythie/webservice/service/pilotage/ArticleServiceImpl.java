package fr.pythie.webservice.service.pilotage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pythie.webservice.exception.ListeArticlesParDefautRequestException;
import fr.pythie.webservice.interfaces.service.userinterface.ArticleService;
import fr.pythie.webservice.model.Article;

@Component
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private CommandeRepository commandeRepository;
	
	@Override
	public List<Article> obtenirListeArticleParDefaut(long nombre) throws ListeArticlesParDefautRequestException {
		try {
		
		
		
		return null;
	}

}
