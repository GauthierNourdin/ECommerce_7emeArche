package fr.pythie.webservice.interfaces.service.userinterface;

import fr.pythie.webservice.communication.InformationsPaiement;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;

/**
 * Interface pour définir les méthodes attendues pour le controller REST
 * traîtant les requêtes concernant les paiements, provenant de l'interface
 * utilisateur web.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public interface PaiementService {

	/**
	 * 
	 * @param informationsPaiement
	 * @return
	 * @throws LectureBaseDonneesException
	 * @throws EcritureBaseDonneesException
	 * @throws ClientInconnuException
	 */
	InformationsPaiement informationsBancaires(InformationsPaiement informationsPaiement)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException;

}