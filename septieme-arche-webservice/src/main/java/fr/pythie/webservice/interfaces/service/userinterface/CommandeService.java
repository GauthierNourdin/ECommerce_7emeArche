package fr.pythie.webservice.interfaces.service.userinterface;

import fr.pythie.webservice.communication.CommandeAvecIdClient;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.exception.StockInsuffisantException;

public interface CommandeService {
	/**
	 * Interface pour définir les méthodes attendues pour le controller REST
	 * traîtant les requêtes concernant les commandes, provenant de l'interface
	 * utilisateur web.
	 */

	CommandeAvecIdClient enregistrementCommande(CommandeAvecIdClient commande) throws LectureBaseDonneesException,
			EcritureBaseDonneesException, StockInsuffisantException, ClientInconnuException;

}