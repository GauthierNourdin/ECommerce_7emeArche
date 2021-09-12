package fr.pythie.webservice.interfaces.service.userinterface;

import fr.pythie.webservice.communication.InformationsPaiement;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;

public interface PaiementService {
	/**
	 * Interface pour définir les méthodes attendues pour le controller REST
	 * traîtant les requêtes concernant les paiements, provenant de l'interface
	 * utilisateur web.
	 */

	InformationsPaiement informationsBancaires(InformationsPaiement informationsPaiement)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException;

}