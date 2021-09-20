package fr.pythie.webservice.service.userinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pythie.webservice.communication.InformationsPaiement;
import fr.pythie.webservice.dao.ClientRepository;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.interfaces.service.userinterface.PaiementService;
import fr.pythie.webservice.model.Client;

/**
 * Classe service implémentant les méthodes demandées par PaiementService afin
 * de traîter les demandes concernant les paiements faites par l'interface
 * utilisateur
 */
@Component
public class PaiementServiceImpl implements PaiementService {

	@Autowired
	private ClientRepository clientRepository;

	/**
	 * Enregistre les informations de la carte bancaire du client.
	 */
	@Override
	public InformationsPaiement informationsBancaires(InformationsPaiement informationsPaiement)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException {

		// Client à modifier
		Client client;

		// On essaie d'extraire les informations du client de la base de données
		try {
			client = clientRepository.findById(informationsPaiement.getIdClient()).orElse(null);
		} catch (Exception exception) {
			// En cas d'erreur de lecture on lève une exception
			throw new LectureBaseDonneesException();
		}

		// On vérifie que l'identifiant envoyé correspond à un client de la base de
		// données.
		if (client == null) {
			// Dans le cas contraire on lève une exception.
			throw new ClientInconnuException();
		}

		// On ajoute les données bancaires envoyés aux informations client.
		client.setCvc(informationsPaiement.getCvc());
		client.setNumeroCarte(informationsPaiement.getNumeroCarte());
		client.setDateDeValidite(informationsPaiement.getDateDeValidite());
		
		// On essaie d'enregistrer le client modifié dans la base de données
		try { 
			client = clientRepository.save(client);
		} catch (Exception exception) {
			// En cas d'erreur d'écriture on lève une exception
			throw new EcritureBaseDonneesException();
		}

		return informationsPaiement;
	}

}
