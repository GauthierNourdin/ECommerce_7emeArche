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

/**
 * Classe de controller REST pour traîter les requêtes concernant les clients.
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
@RequestMapping("/userinterface/client")
public class ClientController {

	@Autowired
	ClientService clientService;

	/**
	 * Traîte une requête POST de création de compte client avec les informations client dans le corps.
	 * La réponse standard est le client enregistré.
	 * Retourne une erreur 503 si la base de données est inaccessible à la lecture ou à l'écriture.
	 * Retourne une erreur 403 si les identifiants sont déjà utilisés par un autre utilisateur.
	 * Retourne une erreur 500 en cas de problème inconnu.
	 * 
	 * @param nouveauClient Le nouveau client.
	 * 
	 * @return clientEnregistre Le client enregistré.
	 * 
	 * @since 1.0
	 */
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
			// En cas d'erreur inattendue on envoie un status HTTP 500
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur non traîtée.");
		}

		return clientEnregistre;
	}

	/**
	 * Traîte une requête POST d'authentification client avec les identifiants dans le corps.
	 * La réponse standard est le client identifié.
	 * Retourne une erreur 503 si la base de données est inaccessible à la lecture.
	 * Retourne une erreur 401 si les identifiants ne correspondent à aucun client.
	 * Retourne une erreur 500 en cas de problème inconnu.
	 * 
	 * @param demandeAuthentification Les identifiants du client.
	 * 
	 * @return clientAuthentifie Les informations client.
	 * 
	 * @since 1.0
	 */
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
			// 401.
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Pas de client reconnu.");
		} catch (Exception exception) {
			// En cas d'erreur inattendue on envoie un status HTTP 500
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur non traîtée.");
		}

		return clientAuthentifie;
	}

	/**
	 * Traîte une requête POST de modification de compte client avec les informations client dans le corps.
	 * La réponse standard est le client mis à jour.
	 * Retourne une erreur 503 si la base de données est inaccessible à la lecture ou à l'écriture.
	 * Retourne une erreur 403 si les nouveaux identifiants sont déjà utilisés par un autre utilisateur ou si le client est inconnu.
	 * Retourne une erreur 500 en cas de problème inconnu.
	 * 
	 * @param clientAModifier Les nouvelles informations client.
	 * 
	 * @return clientModifie Les informations du client mis à jour.
	 * 
	 * @since 1.0
	 */
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
			// En cas d'erreur inattendue on envoie un status HTTP 500
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur non traîtée.");
		}

		return clientModifie;
	}

}
