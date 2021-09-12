package fr.pythie.webservice.interfaces.service.userinterface;

import fr.pythie.webservice.communication.DemandeAuthentification;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.IdentifiantsUtilisesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.model.Client;

public interface ClientService {
	/**
	 * Interface pour définir les méthodes attendues pour le controller REST
	 * traîtant les requêtes concernant les clients, provenant de l'interface
	 * utilisateur web.
	 */
	
	Client creationCompteClient(Client nouveauClient) throws LectureBaseDonneesException, EcritureBaseDonneesException, IdentifiantsUtilisesException;

	Client authentificationClient(DemandeAuthentification demandeAuthentification) throws LectureBaseDonneesException, ClientInconnuException;

	Client modificationCompteClient(Client clientAModifier) throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException, IdentifiantsUtilisesException; 
	
}
