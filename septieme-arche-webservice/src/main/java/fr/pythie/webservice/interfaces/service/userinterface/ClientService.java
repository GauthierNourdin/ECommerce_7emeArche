package fr.pythie.webservice.interfaces.service.userinterface;

import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.model.Client;

public interface ClientService {
	/**
	 * Interface pour définir les méthodes attendues pour le controller REST
	 * traîtant les requêtes concernant les clients, provenant de l'interface
	 * utilisateur web.
	 */
	
	Client creationCompteClient(Client nouveauClient) throws EcritureBaseDonneesException;

	Client authentificationClient(String email, String motDePasse) throws LectureBaseDonneesException, ClientInconnuException;

	Client modificationCompteClient(Client clientAModifier) throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException; 
	
}
