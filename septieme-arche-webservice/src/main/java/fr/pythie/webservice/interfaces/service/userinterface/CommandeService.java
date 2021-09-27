package fr.pythie.webservice.interfaces.service.userinterface;

import java.util.List;

import fr.pythie.webservice.communication.CommandeAvecIdClient;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.exception.StockInsuffisantException;
import fr.pythie.webservice.service.userinterface.CommandeServiceImpl;

/**
 * Interface pour définir les méthodes traîtant les requêtes concernant les commandes.
 * Les requêtes proviennent des controllers REST répondant au site web.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public interface CommandeService {

	/**
	 * Enregistre une commande et modifie les stocks des articles ayant un stock.
	 * L'entrée et la sortie sont de type CommandeAvecIdClient.
	 * 
	 * @param commande Commande à valider et à enregistrer.
	 * 
	 * @return nouvelleCommandeAvecIdClient Commande enregistrée.
	 * 
	 * @throws LectureBaseDonneesException Si la lecture de la base de données échoue.
	 * @throws EcritureBaseDonneesException Si l'écriture dans une base de données échoue.
	 * @throws StockInsuffisantException Si le stock d'un article est insuffisant.
	 * @throws ClientInconnuException Si l'identifiant client est invalide.
	 * 
	 * @see fr.pythie.webservice.communication.CommandeAvecIdClient
	 * @see CommandeServiceImpl#annulationDiminutionStocks(List)
	 * 
	 * @since 1.0
	 */
	CommandeAvecIdClient enregistrementCommande(CommandeAvecIdClient commande) throws LectureBaseDonneesException,
			EcritureBaseDonneesException, StockInsuffisantException, ClientInconnuException;

}