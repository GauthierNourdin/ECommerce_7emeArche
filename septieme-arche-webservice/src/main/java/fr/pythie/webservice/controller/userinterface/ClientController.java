package fr.pythie.webservice.controller.userinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.pythie.webservice.communication.DemandeAuthentification;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.IdentifiantsUtilisesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.interfaces.service.userinterface.ClientService;
import fr.pythie.webservice.model.Client;

@RestController
@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
@RequestMapping("/userinterface/client")
public class ClientController {
	/**
	 * Classe définissant toutes les requêtes possibles, ainsi que les réponses
	 * appropriées, concernant les clients, venant de l'interface utilisateur web.
	 * Distribue les requêtes aux classes services implémentant l'interface dédiée
	 * via Spring.
	 */

	@Autowired
	ClientService clientService;

	// Enregistre un nouveau compte client
	@PostMapping("/creationCompteClient")
	@ResponseStatus(HttpStatus.CREATED)
	public Client creationCompteClient(@RequestBody Client nouveauClient) {

		Client clientEnregistre;

		// On essaie d'exécuter le service pour enregistrer le client
		try {
			clientEnregistre = clientService.creationCompteClient(nouveauClient);
		} catch (LectureBaseDonneesException exception) {
			// En cas d'erreur lors de la lecture dans la base de données (pour
			// vérifier qu'un autre utilisateur n'utilise pas les mêmes identifiants) on
			// envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Impossible d'accéder aux données.");
		} catch (EcritureBaseDonneesException exception) {
			// En cas d'erreur lors de l'écriture dans la base de données (pour
			// enregistrer le client) on envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Impossible d'enregistrer le client.");
		} catch (IdentifiantsUtilisesException exception) {
			// Dans le cas où les identifiants sont déjà utilisés par un utilisateur
			// enregistré on envoie un status HTTP 403
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Les identifiants sont déjà utilisés.");
		} catch (Exception exception) {
			// En cas d'erreur inattendue on envoie un status HTTP 501
			throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Erreur non traîtée.");
		}

		return clientEnregistre;
	}

	// Authentifie un client et retourne ses informations
	@PostMapping("/authentificationClient")
	@ResponseStatus(HttpStatus.OK)
	public Client authentificationClient(@RequestBody DemandeAuthentification demandeAuthentification) {

		Client clientAuthentifie;

		// On essaie d'exécuter le service pour authentifier le client
		try {
			clientAuthentifie = clientService.authentificationClient(demandeAuthentification);
		} catch (LectureBaseDonneesException exception) {
			// En cas d'erreur lors de la lecture dans la base de données (pour
			// authentifier le client et retourner ses informations) on envoie un status
			// HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Impossible d'accéder aux données.");
		} catch (ClientInconnuException exception) {
			// En cas d'absence de client reconnu (mauvais inputs) on envoie un status HTTP
			// 204
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Pas de client reconnu.");
		} catch (Exception exception) {
			// En cas d'erreur inattendue on envoie un status HTTP 501
			throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Erreur non traîtée.");
		}

		return clientAuthentifie;
	}

	// Modifie les informations d'un compte client
	@PostMapping("/modificationCompteClient")
	@ResponseStatus(HttpStatus.OK)
	public Client modificationCompteClient(@RequestBody Client clientAModifier) {

		Client clientModifie;

		// On essaie d'exécuter le service pour modifier le compte client
		try {
			clientModifie = clientService.modificationCompteClient(clientAModifier);
		} catch (LectureBaseDonneesException exception) {
			// En cas d'erreur lors de la lecture de la base de données (pour
			// vérifier la présence du client) on envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
					"Impossible de vérifier la présence du client.");
		} catch (EcritureBaseDonneesException exception) {
			// En cas d'erreur lors de l'écriture dans la base de données (pour
			// modifier les informations client) on envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
					"Impossible d'enregistrer les modifications pour le client.");
		} catch (ClientInconnuException exception) {
			// En cas d'absence de client reconnu on envoie un status HTTP 403
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Le client n'est pas reconnu.");
		} catch (IdentifiantsUtilisesException exception) {
			// Dans le cas où les identifiants sont déjà utilisés par un utilisateur
			// enregistré on envoie un status HTTP 403
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Les identifiants sont déjà utilisés.");
		} catch (Exception exception) {
			// En cas d'erreur inattendue on envoie un status HTTP 501
			throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Erreur non traîtée.");
		}

		return clientModifie;
	}

}
