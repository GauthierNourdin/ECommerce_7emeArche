package fr.pythie.webservice.interfaces.service.userinterface;

import fr.pythie.webservice.communication.DemandeAuthentification;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.IdentifiantsUtilisesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.model.Client;

/**
 * Interface pour définir les méthodes attendues pour le controller REST
 * traîtant les requêtes concernant les clients, provenant de l'interface
 * utilisateur web.
 */
public interface ClientService {

	/**
	 * 
	 * @param nouveauClient
	 * @return
	 * @throws LectureBaseDonneesException
	 * @throws EcritureBaseDonneesException
	 * @throws IdentifiantsUtilisesException
	 */
	Client creationCompteClient(Client nouveauClient) throws LectureBaseDonneesException, EcritureBaseDonneesException, IdentifiantsUtilisesException;

	/**
	 * 
	 * @param demandeAuthentification
	 * @return
	 * @throws LectureBaseDonneesException
	 * @throws ClientInconnuException
	 */
	Client authentificationClient(DemandeAuthentification demandeAuthentification) throws LectureBaseDonneesException, ClientInconnuException;

	/**
	 * 
	 * @param clientAModifier
	 * @return
	 * @throws LectureBaseDonneesException
	 * @throws EcritureBaseDonneesException
	 * @throws ClientInconnuException
	 * @throws IdentifiantsUtilisesException
	 */
	Client modificationCompteClient(Client clientAModifier) throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException, IdentifiantsUtilisesException; 
	
}
