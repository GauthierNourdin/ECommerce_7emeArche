package fr.pythie.webservice.interfaces.service.userinterface;

import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.model.Client;

public interface PaiementService {
	/**
	 * Interface pour définir les méthodes attendues pour le controller REST
	 * traîtant les requêtes concernant les paiements, provenant de l'interface
	 * utilisateur web.
	 */
	
	Client informationsBancaires(Client clientAvecInformationsBancaires) throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException;

}