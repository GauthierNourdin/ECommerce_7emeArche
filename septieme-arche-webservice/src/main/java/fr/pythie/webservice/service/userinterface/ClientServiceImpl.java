package fr.pythie.webservice.service.userinterface;

import org.springframework.stereotype.Component;

import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.interfaces.service.userinterface.ClientService;
import fr.pythie.webservice.model.Client;

@Component
public class ClientServiceImpl implements ClientService {
	/** 
	 * Classe service implémentant les méthodes demandées par ClientService afin de
	 * traîter les demandes concernant les clients faites par l'interface
	 * utilisateur
	 */


	public Client creationCompteClient(Client nouveauClient) throws EcritureBaseDonneesException {
		// A faire avant la page d'inscription client Angular
		return null;
	}


	public Client authentificationClient(String email, String motDePasse)
			throws LectureBaseDonneesException, ClientInconnuException {
		// A faire avant le popup de connexion Angular
		return null;
	}


	public Client modificationCompteClient(Client clientAModifier)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException {
		// A faire avant la page d'informations personnelles
		return null;
	}

}
