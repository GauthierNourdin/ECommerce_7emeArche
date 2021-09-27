package fr.pythie.webservice.interfaces.service.userinterface;

import fr.pythie.webservice.communication.InformationsPaiement;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;

/**
 * Interface pour définir les méthodes traîtant les requêtes concernant les paiements.
 * Les requêtes proviennent des controllers REST répondant au site web.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public interface PaiementService {

	/**
	 * Enregistre les informations de la carte bancaire du client. L'entrée et la 
	 * sortie sont de type InformationsPaiement.
	 * 
	 * @param informationsPaiement Informations bancaires et identifiant client.
	 * 
	 * @return informationsPaiement Informations bancaires enregistrées.
	 * 
	 * @throws LectureBaseDonneesException Si la lecture de la base de données échoue.
	 * @throws EcritureBaseDonneesException Si l'écriture dans la base de données échoue.
	 * @throws ClientInconnuException Si l'identifiant client est invalide.
	 * 
	 * @see fr.pythie.webservice.communication.InformationsPaiement
	 * 
	 * @since 1.0
	 */
	InformationsPaiement informationsBancaires(InformationsPaiement informationsPaiement)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException;

}