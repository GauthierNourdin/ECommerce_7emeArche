package fr.pythie.webservice.service.userinterface;

import org.springframework.stereotype.Component;

import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.interfaces.service.userinterface.PaiementService;
import fr.pythie.webservice.model.Client;

@Component
public class PaiementServiceImpl implements PaiementService {
	/** 
	 * Classe service implémentant les méthodes demandées par PaiementService afin de
	 * traîter les demandes concernant les paiements faites par l'interface
	 * utilisateur
	 */
	

	public Client informationsBancaires(Client clientAvecInformationsBancaires)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException {
		// A faire avant la page de panier Angular
		return null;
	}

}
