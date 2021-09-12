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

@RestController
@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
@RequestMapping("/userinterface/commande")
public class CommandeController {
	/**
	 * Classe définissant toutes les requêtes possibles, ainsi que les réponses
	 * appropriées, concernant les commandes, venant de l'interface utilisateur web.
	 * Distribue les requêtes aux classes services implémentant l'interface dédiée
	 * via Spring.
	 */
	
	@Autowired
	CommandeService commandeService;
	
	// Vérifie et enregistre la commande
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
			// En cas d'erreur inattendue on envoie un status HTTP 501
			throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Erreur non traîtée.");
		}

		return nouvelleCommande;
	}
	
	
}
