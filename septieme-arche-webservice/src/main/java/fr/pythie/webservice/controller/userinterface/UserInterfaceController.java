package fr.pythie.webservice.controller.userinterface;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.interfaces.service.userinterface.ArticleService;
import fr.pythie.webservice.model.Article;

@Component
@RestController
@RequestMapping("/userinterface")
public class UserInterfaceController {
	/** 
	 * Classe définissant tous les appels possibles ainsi que les réponses appropriées
	 * pour l'interface utilisateur web. Distribue les requêtes aux classes services
	 * implémentant les interfaces spécialisées par type de requête via Spring.
	 */
	
	@Autowired
	ArticleService articleService;
	
	// Répond à la demande de la liste par défaut d'articles
	@GetMapping("/listeParDefautLivres")
	public List<Article> listeParDefautLivres() {
		
		try {
			listeArticles = articleService.obtenirListeArticleParDefaut();
		} catch (ListeArticlesParDefautRequestException exception) {
			throw new ResponseStatusException(
	    	          HttpStatus.SERVICE_UNAVAILABLE, "Les données sont inaccessibles.");
		} catch (ListeArticlesParDefautRequestException exception) {
			
		}
		return listeArticles;
	}
	
	
	
	
}
