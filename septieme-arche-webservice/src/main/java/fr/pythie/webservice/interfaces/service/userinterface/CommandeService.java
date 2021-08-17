package fr.pythie.webservice.interfaces.service.userinterface;

import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.exception.StockInsuffisantException;
import fr.pythie.webservice.model.Commande;

public interface CommandeService {
	/**
	 * Interface pour définir les méthodes attendues pour le controller REST
	 * traîtant les requêtes concernant les commandes, provenant de l'interface
	 * utilisateur web.
	 */
	
	Commande enregistrementCommande(Commande commande) throws LectureBaseDonneesException, EcritureBaseDonneesException, StockInsuffisantException;

}