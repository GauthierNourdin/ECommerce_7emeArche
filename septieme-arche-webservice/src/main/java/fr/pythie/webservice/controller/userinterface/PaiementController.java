package fr.pythie.webservice.controller.userinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.pythie.webservice.communication.InformationsPaiement;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.interfaces.service.userinterface.PaiementService;

@RestController
@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
@RequestMapping("/userinterface/paiement")
public class PaiementController {
	/**
	 * Classe définissant toutes les requêtes possibles, ainsi que les réponses
	 * appropriées, concernant les paiements, venant de l'interface utilisateur web.
	 * Distribue les requêtes aux classes services implémentant l'interface dédiée
	 * via Spring.
	 */

	@Autowired
	PaiementService paiementService;
	
	// Enregistre les informations bancaires d'un client
	@PostMapping("/enregistrementInformationsBancaires")
	@ResponseStatus(HttpStatus.OK)
	public InformationsPaiement enregistrementInformationsBancaires(@RequestBody InformationsPaiement informationsPaiement) {

		InformationsPaiement paiement;

		// On essaie d'exécuter le service pour vérifier et enregistrer une commande
		try {
			paiement = paiementService.informationsBancaires(informationsPaiement);
		} catch (LectureBaseDonneesException exception) {
			// En cas d'erreur lors de la lecture de la base de données (pour vérifier
			// la présence du client) on envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
					"Impossible de vérifier le client.");
		} catch (EcritureBaseDonneesException exception) {
			// En cas d'erreur lors de l'écriture en base de données (pour enregistrer
			// les informations bancaires du client) on envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
					"Impossible d'enregistrer les informations.");
		} catch (ClientInconnuException exception) {
			// Dans le cas où le client n'est pas reconnu on envoie un status HTTP 403
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,
					"Le client n'est pas reconnu.");
		} catch (Exception exception) {
			// En cas d'erreur inattendue on envoie un status HTTP 501
			throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Erreur non traîtée.");
		}

		return paiement;
	}
	
	
}
