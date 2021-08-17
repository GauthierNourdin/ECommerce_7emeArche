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

	@Override
	public Client creationCompteClient(Client nouveauClient) throws EcritureBaseDonneesException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client authentificationClient(String email, String motDePasse)
			throws LectureBaseDonneesException, ClientInconnuException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client modificationCompteClient(Client clientAModifier)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException {
		// TODO Auto-generated method stub
		return null;
	}

}
