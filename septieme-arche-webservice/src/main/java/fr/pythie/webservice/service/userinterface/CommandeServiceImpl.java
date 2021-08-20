package fr.pythie.webservice.service.userinterface;

import org.springframework.stereotype.Component;

import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.exception.StockInsuffisantException;
import fr.pythie.webservice.interfaces.service.userinterface.CommandeService;
import fr.pythie.webservice.model.Commande;

@Component
public class CommandeServiceImpl implements CommandeService {
	/** 
	 * Classe service implémentant les méthodes demandées par CommandeService afin de
	 * traîter les demandes concernant les commandes faites par l'interface
	 * utilisateur
	 */
	

	public Commande enregistrementCommande(Commande commande)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, StockInsuffisantException {
		// A faire avant la page de panier Angular
		return null;
	}

}
