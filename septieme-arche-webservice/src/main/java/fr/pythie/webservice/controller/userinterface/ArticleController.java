package fr.pythie.webservice.controller.userinterface;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.pythie.webservice.communication.ConsultationAvecIdClient;
import fr.pythie.webservice.communication.IdentifiantEtTypeArticle;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.ConsultationInconnueException;
import fr.pythie.webservice.exception.ConsultationNonAnonymeException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.IdInvalideException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.exception.ListeVideException;
import fr.pythie.webservice.exception.MauvaisStringException;
import fr.pythie.webservice.interfaces.service.userinterface.ArticleService;
import fr.pythie.webservice.model.Consultation;
import fr.pythie.webservice.model.LivreImprime;
import fr.pythie.webservice.model.LivreNumerique;

/**
 * Classe de controller REST pour traîter les requêtes concernant les articles.
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
@RequestMapping("/userinterface/article")
public class ArticleController {

	@Autowired
	ArticleService articleService;

	/**
	 * Traîte une requête GET d'obtention de la liste par défaut des articles.
	 * La réponse standard est une liste d'identifiants avec le type d'article.
	 * Retourne une erreur 503 si la base de données est inaccessible à la lecture.
	 * Retourne une erreur 500 en cas de problème inconnu.
	 * 
	 * @return listeParDefautArticles La liste des articles par défaut.
	 * 
	 * @since 1.0
	 */
	@GetMapping("/listeArticlesParDefaut")
	@ResponseStatus(HttpStatus.OK)
	public List<IdentifiantEtTypeArticle> listeParDefautArticles() {

		ArrayList<IdentifiantEtTypeArticle> listeParDefautArticles = new ArrayList<IdentifiantEtTypeArticle>();

		// On essaie d'exécuter le service pour générer la liste par défaut.
		try {
			listeParDefautArticles = (ArrayList<IdentifiantEtTypeArticle>) articleService
					.obtenirListeArticleParDefaut();
		} catch (LectureBaseDonneesException exception) {
			// En cas d'erreur lors de la lecture de la base de données (pour collecter les
			// commandes) on envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Les données sont inaccessibles.");
		} catch (Exception exception) {
			// En cas d'erreur inattendue on envoie un status HTTP 500
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur non traîtée.");
		}

		return listeParDefautArticles;
	}

	/**
	 * Traîte une requête POST d'obtention d'une liste de livres imprimés avec une liste d'identifiants dans le corps.
	 * La réponse standard est une liste de livres imprimés dans le même ordre que la liste d'identifiants.
	 * Retourne une erreur 503 si la base de données est inaccessible à la lecture.
	 * Retourne une erreur 403 si la liste d'identifiants est vide ou si l'un des identifiants est invalide.
	 * Retourne une erreur 500 en cas de problème inconnu.
	 * 
	 * @param listeIdLivresImprimes La liste d'identifiants de livres imprimés.
	 * 
	 * @return livresImprimes La liste des livres imprimés correspondants.
	 * 
	 * @since 1.0
	 */
	@PostMapping("/livresImprimes")
	@ResponseStatus(HttpStatus.OK)
	public List<LivreImprime> livresImprimes(@RequestBody List<Long> listeIdLivresImprimes) {

		ArrayList<LivreImprime> livresImprimes = new ArrayList<LivreImprime>();

		// On essaie d'exécuter le service pour obtenir la liste de livres imprimés
		// correspondant aux ids reçus.
		try {
			livresImprimes = (ArrayList<LivreImprime>) articleService.obtenirListeLivresImprimes(listeIdLivresImprimes);
		} catch (LectureBaseDonneesException exception) {
			// En cas d'erreur lors de la lecture de la base de données (pour collecter les
			// commandes) on envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Les données sont inaccessibles.");
		} catch (ListeVideException exception) {
			// Dans le cas où la liste en entrée est vide, on envoie un status HTTP 403
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Liste d'identifiants vide.");
		} catch (IdInvalideException exception) {
			// Dans le cas où un des id est inconnu, on envoie un status HTTP 403
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Un des identifiants est inconnu.");
		} catch (Exception exception) {
			// En cas d'erreur inattendue on envoie un status HTTP 500
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur non traîtée.");
		}

		return livresImprimes;

	}

	/**
	 * Traîte une requête POST d'obtention d'une liste de livres numériques avec une liste d'identifiants dans le corps.
	 * La réponse standard est une liste de livres numériques dans le même ordre que la liste d'identifiants.
	 * Retourne une erreur 503 si la base de données est inaccessible à la lecture.
	 * Retourne une erreur 403 si la liste d'identifiants est vide ou si l'un des identifiants est invalide.
	 * Retourne une erreur 500 en cas de problème inconnu.
	 * 
	 * @param listeIdLivresNumeriques La liste d'identifiants de livres imprimés.
	 * 
	 * @return livresNumeriques La liste des livres imprimés correspondants.
	 * 
     * @since 1.0
	 */
	@PostMapping("/livresNumeriques")
	@ResponseStatus(HttpStatus.OK)
	public List<LivreNumerique> livresNumeriques(@RequestBody List<Long> listeIdLivresNumeriques) {

		ArrayList<LivreNumerique> livresNumeriques = new ArrayList<LivreNumerique>();

		// On essaie d'exécuter le service pour obtenir la liste de livres imprimés
		// correspondant aux ids reçus.
		try {
			livresNumeriques = (ArrayList<LivreNumerique>) articleService.obtenirListeLivresNumeriques(listeIdLivresNumeriques);
		} catch (LectureBaseDonneesException exception) {
			// En cas d'erreur lors de la lecture de la base de données (pour collecter les
			// commandes) on envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Les données sont inaccessibles.");
		} catch (ListeVideException exception) {
			// Dans le cas où la liste en entrée est vide, on envoie un status HTTP 403
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Liste d'identifiant vide.");
		} catch (IdInvalideException exception) {
			// Dans le cas où un des id est inconnu, on envoie un status HTTP 403
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Un des identifiants est inconnu.");
		} catch (Exception exception) {
			// En cas d'erreur inattendue on envoie un status HTTP 500
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur non traîtée.");
		}

		return livresNumeriques;

	}
	
	/**
	 * Traîte une requête POST d'obtention d'une liste de livres correspondant avec une chaîne de caractère dans le corps.
	 * La réponse standard est une liste d'identifiants avec le type de livre.
	 * Si on n'a aucune correspondance on retoure un status 204.
	 * Le critère de correspondance est la présence de la chaîne de caractère dans le titre du livre ou dans le nom complet d'un de ses auteurs.
	 * Retourne une erreur 503 si la base de données est inaccessible à la lecture.
	 * Retourne une erreur 403 si la chaîne de caractère est vide.
	 * Retourne une erreur 500 en cas de problème inconnu.
	 * 
	 * @param auteurOuTitre La chaîne de caractère recherchée dans le titre et les auteurs.
	 * 
	 * @return listeLivresParAuteurOuTitre Les livres correspondants.
	 * 
	 * @since 1.0
	 */
	@PostMapping("/listeLivresParAuteurOuTitre")
	@ResponseStatus(HttpStatus.OK)
	public List<IdentifiantEtTypeArticle> listeLivresParAuteurOuTitre(@RequestBody String auteurOuTitre) {

		ArrayList<IdentifiantEtTypeArticle> listeLivresParAuteurOuTitre = new ArrayList<IdentifiantEtTypeArticle>();

		// On essaie d'exécuter le service pour générer la liste de livres
		// dont l'auteur ou le titre contient la chaîne de caractères
		try {
			listeLivresParAuteurOuTitre = (ArrayList<IdentifiantEtTypeArticle>) articleService
					.obtenirListeLivresParAuteurOuTitre(auteurOuTitre);
		} catch (LectureBaseDonneesException exception) {
			// En cas d'erreur lors de la lecture de la base de données (pour collecter les
			// livres) on envoie un status HTTP 503.
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Les données sont inaccessibles.");
		} catch (ListeVideException exception) {
			// Dans le cas où la liste de résultat est vide, on envoie un status HTTP 204.
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Pas de résultats.");
		} catch (MauvaisStringException exception) {
			// Dans le cas où la chaîne de caractère reçue en entrée est vide, on envoie un status HTTP 403.
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "La chaîne de caractère est vide.");
		} catch (Exception exception) {
			// En cas d'erreur inattendue on envoie un status HTTP 500.
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur non traîtée.");
		}

		return listeLivresParAuteurOuTitre;
	}

	/**
	 * Traîte une requête POST d'enregistrement d'une consultation anonyme incluse dans le corps.
	 * La réponse standard est la consultation enregistrée.
	 * Retourne une erreur 503 si la base de données est inaccessible à l'écriture.
	 * Retourne une erreur 403 si la consultation n'est pas anonyme.
	 * Retourne une erreur 500 en cas de problème inconnu.
	 * 
	 * @param consultationAnonyme La consultation anonyme à enregistrer.
	 * 
	 * @return consultation La consultation anonyme enregistrée.
	 * 
	 * @since 1.0
	 */
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
			// En cas d'erreur inattendue on envoie un status HTTP 500
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur non traîtée.");
		}

		return consultation;
	}

	/**
	 * Traîte une requête POST d'enregistrement d'une consultation d'un client identifié.
	 * La consultation est incluse dans le corps avec l'identifiant du client.
	 * La réponse standard est la consultation enregistrée avec l'identifiant du client.
	 * Retourne une erreur 503 si la base de données est inaccessible à la lecture ou à l'écriture.
	 * Retourne une erreur 403 si l'identifiant client est inconnu.
	 * Retourne une erreur 500 en cas de problème inconnu.
	 * 
	 * @param consultationClient La consultation d'un client à enregistrer.
	 * 
	 * @return consultation La consultation d'un client enregistrée.
	 * 
	 * @since 1.0
	 */
	@PostMapping("/ajoutConsultationClient")
	@ResponseStatus(HttpStatus.CREATED)
	public ConsultationAvecIdClient ajoutConsultationClient(@RequestBody ConsultationAvecIdClient consultationClient) {

		ConsultationAvecIdClient consultation = new ConsultationAvecIdClient();

		// On essaie d'exécuter le service pour enregistrer une consultation d'un client
		// identifié
		try {
			consultation = articleService.ajoutConsultationClient(consultationClient);
		} catch (LectureBaseDonneesException exception) {
			// En cas d'erreur lors de la lecture en base de données (pour vérifier
			// présence du client) on envoie un status HTTP 503
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Impossible de vérifier le client.");
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
			// En cas d'erreur inattendue on envoie un status HTTP 500
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur non traîtée.");
		}

		return consultation;
	}

	/** 
	 * Traîte une requête POST d'ajout d'un client à une consultation anonyme.
	 * La consultation est incluse dans le corps avec l'identifiant du client.
	 * La réponse standard est la consultation mise à jour avec l'identifiant du client.
	 * Retourne une erreur 503 si la base de données est inaccessible à la lecture ou à l'écriture.
	 * Retourne une erreur 403 si l'identifiant client est inconnu ou si la consultation n'est pas anonyme.
	 * Retourne une erreur 500 en cas de problème inconnu.
	 * 
	 * @param consultationAvecClient La consultation anonyme à mettre à jour.
	 * 
	 * @return consultation La consultation mise à jour.
	 * 
	 * @since 1.0
	 */
	@PostMapping("/ajoutClientAConsultation")
	@ResponseStatus(HttpStatus.OK)
	public ConsultationAvecIdClient ajoutClientAConsultation(@RequestBody ConsultationAvecIdClient consultationAvecIdClient) {

		ConsultationAvecIdClient consultation = new ConsultationAvecIdClient();

		// On essaie d'exécuter le service pour ajouter un client à une consultation
		// anonyme
		try {
			consultation = articleService.ajoutClientAConsultation(consultationAvecIdClient);
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
			// En cas d'erreur inattendue on envoie un status HTTP 500
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur non traîtée.");
		}

		return consultation;
	}

	/**
	 * Traîte une requête POST d'obtention des stocks d'une liste de livres imprimés. 
	 * La liste d'identifiants des livres imprimés est dans le corps.
	 * La réponse standard est une liste des stocks dans le même ordre que la liste d'identifiants.
	 * Retourne une erreur 503 si la base de données est inaccessible à la lecture.
	 * Retourne une erreur 403 si la liste d'identifiants est vide ou si un des identifiants est inconnu.
	 * Retourne une erreur 500 en cas de problème inconnu.
	 * 
	 * @param listeIdLivresImprimes Les identifiants de livres imprimés.
	 * 
	 * @return listeDisponibiliteLivresImprimes Les stocks des livres imprimés correspondants.
	 * 
	 * @since 1.0
	 */
	@PostMapping("/consulterDisponibiliteLivresImprimes")
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
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Les données sont inaccessibles.");
		} catch (ListeVideException exception) {
			// Dans le cas où la liste d'ID est vide on envoie un status HTTP 403
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "La liste d'identifiants est vide.");
		} catch (IdInvalideException exception) {
			// Dans le cas où un ID de la liste est invalide, on envoie un status HTTP 403
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Un ou plusieurs identifiants sont invalides");
		} catch (Exception exception) {
			// En cas d'erreur inattendue on envoie un status HTTP 500
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur non traîtée.");
		}

		return listeDisponibiliteLivresImprimes;

	}

}
