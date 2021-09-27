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

/**
 * Classe de controller REST pour traîter les requêtes concernant les paiements.
 * Définit les requêtes et leurs réponses possibles pour le site web. Appelle les méthodes appropriées du service.
 * Les requêtes et les réponses sont transmises au format JSON.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@RestController
@RequestMapping("/userinterface/paiement")
public class PaiementController {

	@Autowired
	PaiementService paiementService;
	
	/**
	 * Traîte une requête POST d'enregistrement d'informations bancaires d'un client.
	 * Les informations bancaires sont inclues dans le corps avec l'identifiant du client.
	 * La réponse standard est la même que l'entrée.
	 * Retourne une erreur 503 si la base de données est inaccessible à la lecture ou à l'écriture.
	 * Retourne une erreur 403 si le client n'est pas identifié ou reconnu.
	 * Retourne une erreur 500 en cas de problème inconnu.
	 * 
	 * @param informationsPaiement Les informations client requises pour le paiement.
	 * 
	 * @return paiement Les informations enregistrées.
	 * 
	 * @since 1.0
	 */
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
			// En cas d'erreur inattendue on envoie un status HTTP 500
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur non traîtée.");
		}

		return paiement;
	}
	
}
