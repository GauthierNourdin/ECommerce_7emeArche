package fr.pythie.webservice.interfaces.service.userinterface;

import fr.pythie.webservice.communication.DemandeAuthentification;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.IdentifiantsUtilisesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.model.Client;

/**
 * Interface pour définir les méthodes traîtant les requêtes concernant les clients.
 * Les requêtes proviennent des controllers REST répondant au site web.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public interface ClientService {

	/**
	 * Enregistre un nouveau client.
	 * 
	 * @param nouveauClient Client a enregistrer.
	 * 
	 * @return clientEnregistre Client enregistré.
	 * 
	 * @throws LectureBaseDonneesException Si la lecture de la base de données échoue.
	 * @throws EcritureBaseDonneesException Si l'écriture dans la base de données échoue.
	 * @throws IdentifiantsUtilisesException Si les identifiants demandés sont déjà utilisés par un autre client.
	 * 
	 * @since 1.0
	 */
	Client creationCompteClient(Client nouveauClient) throws LectureBaseDonneesException, EcritureBaseDonneesException, IdentifiantsUtilisesException;

	/**
	 * Permet d'authentifier un client à partir de son email et de son mot de passe.
	 * L'entrée est de type DemandeAuthentification.
	 * 
	 * @param demandeAuthentification Couple email / mot de passe.
	 * 
	 * @return clientIdentifie Client correspondant aux identifiants.
	 * 
	 * @throws LectureBaseDonneesException Si la lecture de la base de données échoue.
	 * @throws ClientInconnuException Si les identifiants ne correspondent à aucun client.
	 * 
	 * @see fr.pythie.webservice.communication.DemandeAuthentification
	 * 
	 * @since 1.0
	 */
	Client authentificationClient(DemandeAuthentification demandeAuthentification) throws LectureBaseDonneesException, ClientInconnuException;

	/**
	 * Modifie les informations personnelles d'un client.
	 * 
	 * @param clientAModifier Client avec nouvelles informations.
	 *  
	 * @return clientModifie Client modifié.
	 * 
	 * @throws LectureBaseDonneesException Si la lecture de la base de données échoue.
	 * @throws EcritureBaseDonneesException Si l'écriture dans la base de données échoue.
	 * @throws ClientInconnuException Si le client n'est connu dans la base de données.
	 * @throws IdentifiantsUtilisesException Si les nouveaux identifiants sont déjà
	 * utilisés par un autre client.
	 * 
	 * @since 1.0
	 */
	Client modificationCompteClient(Client clientAModifier) throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException, IdentifiantsUtilisesException; 
	
}
