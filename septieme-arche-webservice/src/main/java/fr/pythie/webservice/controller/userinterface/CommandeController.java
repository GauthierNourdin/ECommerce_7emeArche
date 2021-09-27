package fr.pythie.webservice.controller.userinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.pythie.webservice.communication.CommandeAvecIdClient;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.exception.StockInsuffisantException;
import fr.pythie.webservice.interfaces.service.userinterface.CommandeService;

/**
 * Classe de controller REST pour traîter les requêtes concernant les commandes.
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
@RequestMapping("/userinterface/commande")
public class CommandeController {
	
	@Autowired
	CommandeService commandeService;
	
	/**
	 * Traîte une requête POST de validation est d'enregistrement de commande.
	 * Les informations sur la commande sont inclues dans le corps avec l'identifiant du client qui la passe.
	 * La réponse standard est la commande enregistrée.
	 * Retourne une erreur 503 si la base de données est inaccessible à la lecture ou à l'écriture.
	 * Retourne une erreur 401 si le client n'est pas identifié ou reconnu.
	 * Retourne une erreur 409 si les stocks ne permettent plus le passage de la commande.
	 * Retourne une erreur 500 en cas de problème inconnu.
	 * 
	 * @param commandeAEnregistrer La nouvelle commande à valider et à enregistré.
	 * 
	 * @return nouvelleCommande La commande validée et enregistrée. 
	 * 
	 * @since 1.0
	 */
	@PostMapping("/enregistrementCommande")
	@ResponseStatus(HttpStatus.CREATED)
	public CommandeAvecIdClient enregistrementCommande(@RequestBody CommandeAvecIdClient commandeAEnregistrer) {

		CommandeAvecIdClient nouvelleCommande;

		// On essaie d'exécuter le service pour vérifier et enregistrer une commande
		try {
			nouvelleCommande = commandeService.enregistrementCommande(commandeAEnregistrer);
		} catch (LectureBaseDonneesException exception) {
			// En cas d'erreur lors de la lecture en base de données (pour vérifier
			// les stocks) on envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
					"Impossible de vérifier les stocks.");
		} catch (EcritureBaseDonneesException exception) {
			// En cas d'erreur lors de l'écriture en base de données (pour enregistrer
			// la commande et ses lignes associées) on envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
					"Impossible d'enregistrer la commande.");
		} catch (StockInsuffisantException exception) {
			// En cas de stock insuffisant pour passer commande (les stocks ont diminué depuis
			// la dernière vérification) on envoie un status HTTP 409
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Les stocks ont diminués et sont insuffisants.");
		} catch (ClientInconnuException exception) {
			// En cas de client non identifié on envoie un status HTTP 401
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Seul un client authentifié peut passer commande.");
		} catch (Exception exception) {
			// En cas d'erreur inattendue on envoie un status HTTP 500
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur non traîtée.");
		}

		return nouvelleCommande;
	}
	
}
