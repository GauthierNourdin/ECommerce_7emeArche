package fr.pythie.webservice.controller.userinterface;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.ConsultationInconnueException;
import fr.pythie.webservice.exception.ConsultationNonAnonymeException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.exception.ListeResultatVideException;
import fr.pythie.webservice.interfaces.service.userinterface.ArticleService;
import fr.pythie.webservice.model.Article;
import fr.pythie.webservice.model.Consultation;
import fr.pythie.webservice.model.Livre;

@RestController
@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
@RequestMapping("/userinterface/article")
public class ArticleController {
	/**
	 * Classe définissant toutes les requêtes possibles, ainsi que les réponses
	 * appropriées, concernant les articles, venant de l'interface utilisateur web.
	 * Distribue les requêtes aux classes services implémentant l'interface dédiée
	 * via Spring.
	 */

	@Autowired
	ArticleService articleService;

	
	// Retourne la liste par défaut d'articles
	@GetMapping("/listeArticlesParDefaut")
	@ResponseStatus(HttpStatus.OK)
	public List<Article> listeParDefautArticles() {

		ArrayList<Article> listeParDefautArticles = new ArrayList<Article>();

		// On essaie d'exécuter le service pour générer la liste par défaut
		try {
			listeParDefautArticles = (ArrayList<Article>) articleService.obtenirListeArticleParDefaut();
		} catch (LectureBaseDonneesException exception) {
			// En cas d'erreur lors de la lecture de la base de données (pour collecter les
			// commandes) on envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Les données sont inaccessibles.");
		} catch (Exception exception) {
			// En cas d'erreur inattendue on envoie un status HTTP 501
			throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Erreur non traîtée.");
		}
		return listeParDefautArticles;
	}

	
	// Retourne une liste de livres selon l'auteur ou le titre (l'un ou l'autre
	// inclut la chaîne de caractère)
	@GetMapping("/listeLivresParAuteurOuTitre")
	@ResponseStatus(HttpStatus.OK)
	public List<Livre> listeLivresParAuteurOuTitre(@RequestBody String auteurOuTitre) {

		ArrayList<Livre> listeLivresParAuteurOuTitre = new ArrayList<Livre>();

		// On essaie d'exécuter le service pour générer la liste de livres
		// dont l'auteur ou le titre contient la chaîne de caractères
		try {
			listeLivresParAuteurOuTitre = (ArrayList<Livre>) articleService
					.obtenirListeLivresParAuteurOuTitre(auteurOuTitre);
		} catch (LectureBaseDonneesException exception) {
			// En cas d'erreur lors de la lecture de la base de données (pour collecter les
			// livres) on envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Les données sont inaccessibles.");
		} catch (ListeResultatVideException exception) {
			// Dans le cas où la liste de résultat est vide, on envoie un status HTTP 204
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Pas de résultats.");
		} catch (Exception exception) {
			// En cas d'erreur inattendue on envoie un status HTTP 501
			throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Erreur non traîtée.");
		}

		return listeLivresParAuteurOuTitre;
	}

	
	// Enregistre une consultation anonyme
	@PostMapping("/ajoutConsultationAnonyme")
	@ResponseStatus(HttpStatus.CREATED)
	public Consultation ajoutConsultationAnonyme(@RequestBody Consultation consultationAnonyme) {

		Consultation consultation = new Consultation();

		// On essaie d'exécuter le service pour enregistrer une consultation anonyme
		try {
			consultation = articleService.ajoutConsultationAnonyme(consultationAnonyme);
		} catch (EcritureBaseDonneesException exception) {
			// En cas d'erreur lors de l'écriture en base de données (pour enregistrer
			// la consultation) on envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
					"Impossible d'enregistrer la consultation.");
		} catch (ConsultationNonAnonymeException exception) {
			// En cas d'erreur avec une consultation non anonyme (on utilise
			// le mauvais service) on envoie un status HTTP 403
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "La consultation n'est pas anonyme.");
		} catch (Exception exception) {
			// En cas d'erreur inattendue on envoie un status HTTP 501
			throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Erreur non traîtée.");
		}

		return consultation;
	}

	
	// Enregistre une consultation d'un client identifié
	@PostMapping("/ajoutConsultationClient")
	@ResponseStatus(HttpStatus.CREATED)
	public Consultation ajoutConsultationClient(@RequestBody Consultation consultationClient) {

		Consultation consultation = new Consultation();

		// On essaie d'exécuter le service pour enregistrer une consultation d'un client
		// identifié
		try {
			consultation = articleService.ajoutConsultationClient(consultationClient);
		} catch (LectureBaseDonneesException exception) {
			// En cas d'erreur lors de la lecture en base de données (pour vérifier
			// présence du client) on envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
					"Impossible de vérifier le client.");
		} catch (EcritureBaseDonneesException exception) {
			// En cas d'erreur lors de l'écriture en base de données (pour enregistrer
			// la consultation) on envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
					"Impossible d'enregistrer la consultation.");
		} catch (ClientInconnuException exception) {
			// En cas d'erreur avec un client inconnu ou laissé vide (on utilise
			// le mauvais service) on envoie un status HTTP 403
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Le client n'est pas connu.");
		} catch (Exception exception) {
			// En cas d'erreur inattendue on envoie un status HTTP 501
			throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Erreur non traîtée.");
		}

		return consultation;
	}

	
	// Met à jour une consultation anonyme en y ajoutant un client
	@PutMapping("/ajoutClientAConsultation")
	@ResponseStatus(HttpStatus.CREATED)
	public Consultation ajoutClientAConsultation(@RequestBody Consultation consultationAvecClient) {

		Consultation consultation = new Consultation();

		// On essaie d'exécuter le service pour ajouter un client à une consultation
		// anonyme
		try {
			consultation = articleService.ajoutClientAConsultation(consultationAvecClient);
		} catch (LectureBaseDonneesException exception) {
			// En cas d'erreur lors de la lecture en base de données (pour vérifier
			// présence de la consultation anonyme et du client) on envoie un status HTTP
			// 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
					"Impossible de vérifier la présence du client et de la consultation.");
		} catch (EcritureBaseDonneesException exception) {
			// En cas d'erreur lors de l'écriture en base de données (pour modifier
			// la consultation) on envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
					"Impossible de mettre à jour la consultation.");
		} catch (ConsultationNonAnonymeException exception) {
			// En cas d'erreur avec une consultation non anonyme (il est interdit
			// de modifier une consultation non-anonyme) on envoie un status HTTP 403
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "La consultation n'est pas anonyme.");
		} catch (ClientInconnuException exception) {
			// En cas d'erreur avec un client inconnu ou laissé vide (on utilise
			// le mauvais service) on envoie un status HTTP 403
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Le client n'est pas connu.");
		} catch (ConsultationInconnueException exception) {
			// En cas d'erreur avec une consultation inconnue (on utilise
			// le mauvais service) on envoie un status HTTP 403
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "La consultation n'est pas connue.");
		} catch (Exception exception) {
			// En cas d'erreur inattendue on envoie un status HTTP 501
			throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Erreur non traîtée.");
		}

		return consultation;
	}

	
	// Donne la disponibilité d'une liste de livres imprimés. Reçoit en input une
	// liste d'ID et donne en sortie une liste de taille équivalente de nombres
	// entiers
	@GetMapping("/consulterDisponibiliteLivresImprimes")
	@ResponseStatus(HttpStatus.OK)
	public List<Integer> consulterDisponibiliteLivresImprimes(@RequestBody List<Long> listeIdLivresImprimes) {

		ArrayList<Integer> listeDisponibiliteLivresImprimes = new ArrayList<Integer>();

		// On essaie d'exécuter le service pour consulter la disponibilité d'une liste
		// de livres imprimés
		try {
			listeDisponibiliteLivresImprimes = articleService
					.consulterDisponibiliteLivresImprimes(listeIdLivresImprimes);
		} catch (LectureBaseDonneesException exception) {
			// En cas d'erreur lors de la lecture en base de données (pour vérifier
			// la disponibilité des livres imprimés) on envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
					"Les données sont inaccessibles.");
		} catch (ListeResultatVideException exception) {
			// Dans le cas où la liste d'ID est vide on envoie un status HTTP 400
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"La liste d'ID est vide.");
		} catch (Exception exception) {
			// En cas d'erreur inattendue on envoie un status HTTP 501
			throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Erreur non traîtée.");
		}

		return listeDisponibiliteLivresImprimes;

	}

}
