package fr.pythie.webservice.service.userinterface;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.message.config.AuthConfigFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pythie.webservice.dao.CommandeRepository;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.interfaces.service.userinterface.ArticleService;
import fr.pythie.webservice.model.Article;
import fr.pythie.webservice.model.Commande;
import fr.pythie.webservice.model.LigneCommande;

@Component
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private CommandeRepository commandeRepository;

	@Override
	public List<Article> obtenirListeArticleParDefaut() throws LectureBaseDonneesException {
		// On détermine d'abord la date servant à trier les lignes de commandes (quatre
		// semaines)
		LocalDateTime dateDepartRecherche = LocalDateTime.now().minusDays(28);
		
		ArrayList<Commande> commandesMois = new ArrayList<Commande>();

		// On récupère les données des commandes de la base de données. On lève une
		// exception en cas d'échec.
		try {
			commandesMois = (ArrayList<Commande>) commandeRepository
					.findByDateAfter(dateDepartRecherche);
		} catch (Exception exception) {
			throw new LectureBaseDonneesException();
		}

		ArrayList<LigneCommande> lignesCommandesResultat = new ArrayList<LigneCommande>();

		/*
		 * Pour trouver les articles les plus vendus, il faut déjà compter les ventes
		 * liés à cet article dans les quatre dernières semaines. Pour cela on commence
		 * par examiner chaque ligne de commande de chaque commande puis on recherche si
		 * une ligne de commande dans la liste résultat ne se réfère pas à cet article.
		 * Si c'est le cas on rajoute à la quantité commandée de la ligne concernée dans
		 * la liste résultat la quantité commandée dans la ligne de commande examinée.
		 * Dans le cas contraire on créé une nouvelle ligne de commande dans la liste
		 * résultat.
		 */
		
		// Boucle sur les commandes
		for (Commande comm : commandesMois) {
			
			// Boucle sur les lignes de commandes d'une commande
			for (LigneCommande ligneComm : comm.getLignesCommande()) {
			
				// Indicateur de correspondance
				boolean correspondance = false; 
				
				for (LigneCommande ligneCommRes : lignesCommandesResultat) {
					
					if (ligneComm.getArticle().equals(ligneCommRes.getArticle())) {
						
						correspondance = true;
					}
					
				}
				
			}
			
		}
		
		
		return null;
	}

}