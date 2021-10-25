package fr.pythie.webservice.service.userinterface;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pythie.webservice.communication.ConsultationAvecIdClient;
import fr.pythie.webservice.communication.IdentifiantEtTypeArticle;
import fr.pythie.webservice.dao.ClientRepository;
import fr.pythie.webservice.dao.CommandeRepository;
import fr.pythie.webservice.dao.ConsultationRepository;
import fr.pythie.webservice.dao.LivreImprimeRepository;
import fr.pythie.webservice.dao.LivreNumeriqueRepository;
import fr.pythie.webservice.dao.LivreRepository;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.ConsultationInconnueException;
import fr.pythie.webservice.exception.ConsultationNonAnonymeException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.IdInvalideException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.exception.ListeVideException;
import fr.pythie.webservice.exception.MauvaisStringException;
import fr.pythie.webservice.interfaces.service.userinterface.ArticleService;
import fr.pythie.webservice.model.Auteur;
import fr.pythie.webservice.model.Client;
import fr.pythie.webservice.model.Commande;
import fr.pythie.webservice.model.Consultation;
import fr.pythie.webservice.model.LigneCommande;
import fr.pythie.webservice.model.Livre;
import fr.pythie.webservice.model.LivreImprime;
import fr.pythie.webservice.model.LivreNumerique;

/**
 * Classe service traîtant les requêtes concernant les articles.
 * Les requêtes proviennent des controllers REST répondant au site web.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@Component
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ConsultationRepository consultationRepository;

	@Autowired
	private CommandeRepository commandeRepository;

	@Autowired
	private LivreRepository livreRepository;

	@Autowired
	private LivreImprimeRepository livreImprimeRepository;

	@Autowired
	private LivreNumeriqueRepository livreNumeriqueRepository;

	/**
	 * Calcule et retourne la liste par défaut des articles. La réponse est une
	 * liste de type IdentifiantEtTypeArticle. On sélectionne les 50 articles les
	 * plus vendus en volume dans les 28 derniers jours.
	 * 
	 * @return listeArticleParDefaut Liste par défaut des articles.
	 * 
	 * @throws fr.pythie.webservice.exception.LectureBaseDonneesException Si la
	 * lecture de la base de données échoue.
	 * 
	 * @see fr.pythie.webservice.communication.IdentifiantEtTypeArticle
	 * 
	 * @since 1.0
	 */
	public List<IdentifiantEtTypeArticle> obtenirListeArticleParDefaut() throws LectureBaseDonneesException {
		// On détermine d'abord la date servant à trier les lignes de commandes (quatre
		// semaines)
		LocalDateTime dateDepartRecherche = LocalDateTime.now().minusDays(28);

		ArrayList<IdentifiantEtTypeArticle> listeArticleParDefaut = new ArrayList<IdentifiantEtTypeArticle>();
		ArrayList<Commande> commandesPertinantes = new ArrayList<Commande>();

		// On récupère les données des commandes de la base de données. On lève une
		// exception en cas d'échec.
		try {
			commandesPertinantes = (ArrayList<Commande>) commandeRepository.findByDateAfter(dateDepartRecherche);
		} catch (Exception exception) {
			throw new LectureBaseDonneesException();
		}

		ArrayList<LigneCommande> lignesCommandesResultat = new ArrayList<LigneCommande>();

		/*
		 * Pour trouver les articles les plus vendus, il faut déjà compter les ventes
		 * liés à cet article dans les quatre dernières semaines. Pour cela on commence
		 * par examiner chaque ligne de commande de chaque commande puis on recherche si
		 * une ligne de commande dans la liste résultat ne se réfère pas à cet article.
		 * Si c'est le cas on rajoute à la quantité commandée de la ligne concernée dans
		 * la liste résultat la quantité commandée dans la ligne de commande examinée.
		 * Dans le cas contraire on créé une nouvelle ligne de commande dans la liste
		 * résultat.
		 */

		for (Commande comm : commandesPertinantes) {

			for (LigneCommande ligneComm : comm.getLignesCommande()) {

				// Indicateur de correspondance
				boolean correspondance = false;

				// Pour les lignes de commandes de la liste résultat
				for (LigneCommande ligneCommRes : lignesCommandesResultat) {

					// Vérification de la correspondance sur un article entre les deux lignes (selon
					// l'ID)
					// Si on a déjà trouvé une correspondance, on arrête les vérifications.
					if ((!correspondance) && (ligneComm.getArticle().getId() == (ligneCommRes.getArticle().getId()))) {

						// Si on trouve une correspondance :
						// On renseigne l'indicateur...
						correspondance = true;

						/*
						 * et on ajoute la quantité commandée de la ligne de commande examinée à la
						 * ligne de commande de la liste résultat
						 */
						ligneCommRes.setQuantiteCommandee(
								ligneCommRes.getQuantiteCommandee() + ligneComm.getQuantiteCommandee());

					}

				}

				// En absence de correspondance on créé une nouvelle ligne
				if (!correspondance) {
					lignesCommandesResultat.add(ligneComm);
				}

			}

		}

		/*
		 * À partir de la liste de lignes de commandes résultats il faut maintenant
		 * faire le tri par le volume de vente (via un comparateur anonyme). L'ordre est
		 * décroissant
		 */
		Collections.sort(lignesCommandesResultat, new Comparator<LigneCommande>() {
			public int compare(LigneCommande ligneCommande1, LigneCommande ligneCommande2) {
				return ligneCommande2.getQuantiteCommandee() - ligneCommande1.getQuantiteCommandee();
			}
		});

		// Reste à récupérer les 50 premiers résultats, si leur nombre est suffisant, ou
		// la totalité
		if (lignesCommandesResultat.size() > 50) {
			lignesCommandesResultat = (ArrayList<LigneCommande>) lignesCommandesResultat.subList(0, 49);
		}

		// Dès lors il faut extraire les articles correspondant à ces lignes et préparer
		// la liste de retour
		for (LigneCommande ligneCommRes : lignesCommandesResultat) {
			// Si l'article est de type livreImprime
			if (ligneCommRes.getArticle() instanceof LivreImprime) {
				listeArticleParDefaut
						.add(new IdentifiantEtTypeArticle(ligneCommRes.getArticle().getId(), "LivreImprime"));
			} else {
				// Sinon il est de type livreNumerique
				listeArticleParDefaut
						.add(new IdentifiantEtTypeArticle(ligneCommRes.getArticle().getId(), "LivreNumerique"));
			}
		}

		return listeArticleParDefaut;
	}

	/**
	 * Retourne une liste de livres imprimés à partir d'une liste d'identifiants.
	 * Conserve l'ordre de la liste transmise.
	 * 
	 * @param listeIdLivresImprimes Liste des identifiants des livres.
	 * 
	 * @return livreImprimes Liste des livres imprimés correspondant.
	 * 
	 * @throws fr.pythie.webservice.exception.LectureBaseDonneesException Si la
	 * lecture de la base de données échoue.
	 * @throws fr.pythie.webservice.exception.ListeVideException Si la liste
	 * d'identifiants est vide.
	 * @throws fr.pythie.webservice.exception.IdInvalideException Si l'un des
	 * identifiants est invalide.
	 *
	 * @since 1.0
	 */
	public ArrayList<LivreImprime> obtenirListeLivresImprimes(List<Long> listeIdLivresImprimes)
			throws LectureBaseDonneesException, ListeVideException, IdInvalideException {

		ArrayList<LivreImprime> livresImprimes = new ArrayList<LivreImprime>();

		// On doit vérifier que la liste d'identifiants n'est pas vide, sinon on lève
		// une exception;
		if (listeIdLivresImprimes == null || listeIdLivresImprimes.isEmpty()) {
			throw new ListeVideException();
		}

		// Ensuite on doit pour chaque identifiant obtenir le livre imprimé
		// correspondant;
		for (long idLivreImprime : listeIdLivresImprimes) {

			LivreImprime livreImprime;

			try {
				livreImprime = livreImprimeRepository.findById(idLivreImprime).orElse(null);
			} catch (Exception exception) {
				// En cas d'erreur de lecture on lève une exception;
				throw new LectureBaseDonneesException();
			}

			// Si l'identifiant est invalide est doit lever une exception.
			if (livreImprime == null) {
				throw new IdInvalideException();
			}
			
			livresImprimes.add(livreImprime);

		}

		return livresImprimes;

	}

	/**
	 * Retourne une liste de livres numériques à partir d'une liste d'identifiants.
	 * Conserve l'ordre de la liste transmise.
	 * 
	 * @param listeIdLivresNumeriques Liste des identifiants des livres.
	 * 
	 * @return livreNumeriques Liste des livres numériques correspondant.
	 * 
	 * @throws fr.pythie.webservice.exception.LectureBaseDonneesException Si la
	 * lecture de la base de données échoue.
	 * @throws fr.pythie.webservice.exception.ListeVideException Si la liste
	 * d'identifiants est vide.
	 * @throws fr.pythie.webservice.exception.IdInvalideException Si l'un des
	 * identifiants est invalide.
	 *
	 * @since 1.0
	 */
	public ArrayList<LivreNumerique> obtenirListeLivresNumeriques(List<Long> listeIdLivresNumeriques)
			throws LectureBaseDonneesException, ListeVideException, IdInvalideException {

		ArrayList<LivreNumerique> livresNumeriques = new ArrayList<LivreNumerique>();

		// On doit vérifier que la liste d'identifiants n'est pas vide, sinon on lève
		// une exception;
		if (listeIdLivresNumeriques == null || listeIdLivresNumeriques.isEmpty()) {
			throw new ListeVideException();
		}

		// Ensuite on doit pour chaque identifiant obtenir le livre imprimé
		// correspondant;
		for (long idLivreNumerique : listeIdLivresNumeriques) {

			LivreNumerique livreNumerique;

			try {
				livreNumerique = livreNumeriqueRepository.findById(idLivreNumerique).orElse(null);
			} catch (Exception exception) {
				// En cas d'erreur de lecture on lève une exception;
				throw new LectureBaseDonneesException();
			}

			// Si l'identifiant est invalide on doit lever une exception.
			if (livreNumerique == null) {
				throw new IdInvalideException();
			}
			
			livresNumeriques.add(livreNumerique);

		}

		return livresNumeriques;
	}

	/**
	 * Retourne une liste de livres correspondant à une chaîne de caractère. La
	 * réponse est une liste de type IdentifiantEtTypeArticle. Pour correspondre un
	 * livre doit avoir une partie du titre ou une partie du nom et prénom d'un des
	 * auteurs correspondant à la chaîne de caractère en ignorant la casse.
	 * 
	 * @param auteurOuTitre Chaîne de caractère que l'on recherche.
	 * 
	 * @return livresCorrespondants Liste des livres correspondants.
	 * 
	 * @throws LectureBaseDonneesException Si la lecture de la base de données
	 * échoue.
	 * @throws ListeVideException Si la liste résultat est vide.
	 * @throws MauvaisStringException Si le String en entrée est vide. 
	 * 
	 * @see fr.pythie.webservice.communication.IdentifiantEtTypeArticle
	 * 
	 * @since 1.0
	 */
	public List<IdentifiantEtTypeArticle> obtenirListeLivresParAuteurOuTitre(String auteurOuTitre)
			throws LectureBaseDonneesException, ListeVideException, MauvaisStringException {
		// On prépare la liste de résultats
		ArrayList<IdentifiantEtTypeArticle> livresCorrespondants = new ArrayList<IdentifiantEtTypeArticle>();

		// On vérifie d'abord que la chaîne de caractère en entrée ne soit pas vide. Sinon on lève une exception.
		if (auteurOuTitre.isEmpty() || auteurOuTitre.isBlank()) {
			throw new MauvaisStringException();
		}
		
		// On supprime les espaces au début et la fin de la chaîne
		auteurOuTitre = auteurOuTitre.trim();
		
		/*
		 * On recherche les livres pour lesquels le prénom de l'auteur, ou son nom, ou
		 * son prenom + nom, ou le titre contient la chaîne de caractère souhaitée On ne
		 * peut pas faire de requête SQL directe (trop compliqué et peu fiable avec Hibernate !). On doit
		 * à la place extraire l'ensemble des livres et faire une recherche en Java.
		 */

		// Tout d'abord on doit extraire l'ensemble des livres.
		ArrayList<Livre> livresEnregistrees = new ArrayList<Livre>();

		try {
			livresEnregistrees = (ArrayList<Livre>) livreRepository.findAll();
		} catch (Exception e) {
			// En cas d'erreur on lève une exception indicant le problème de lecture
			throw new LectureBaseDonneesException();
		}

		/*
		 * Maintenant il faut faire un traitement sur chaque livre. Il faut faire une
		 * série de tests entre les données du livre et la chaîne de caractère
		 * recherchée. A chaque test, si le résultat est positif on enregistre le livre
		 * dans la liste résultat et on n'effectue pas les tests suivants. Les tests
		 * consiste à vérifier la présence de la chaîne de caractère recherchée (sans
		 * tenir compte de la casse, mais aucune différence permise). Les informations
		 * testées sont le titre du livre, le nom de l'auteur, le prénom de l'auteur et
		 * l'assemblage (prénom + nom de l'auteur). Dans le cas de plusieurs auteurs il
		 * faut établir une correspondance avec au moins un auteur.
		 */

		for (Livre livre : livresEnregistrees) {

			// Indicateur de correspondance
			boolean correspondance = false;

			// Test sur le titre du livre
			if (livre.getTitreLivre().toLowerCase().contains(auteurOuTitre.toLowerCase())) {
				correspondance = true;
			}

			for (Auteur auteur : livre.getAuteurs()) {

				// Test sur le prénom de l'auteur
				if (!correspondance && (auteur.getPrenom().toLowerCase().contains(auteurOuTitre.toLowerCase()))) {
					correspondance = true;
				}

				// Test sur le nom de l'auteur
				if (!correspondance && (auteur.getNom().toLowerCase().contains(auteurOuTitre.toLowerCase()))) {
					correspondance = true;
				}

				// Test sur le prénom + nom de l'auteur
				if (!correspondance && ((auteur.getPrenom().toLowerCase() + ' ' + auteur.getNom()).toLowerCase()
						.contains(auteurOuTitre.toLowerCase()))) {
					correspondance = true;
				}

				// Test sur le nom + prénom de l'auteur
				if (!correspondance && ((auteur.getNom().toLowerCase() + ' ' + auteur.getPrenom()).toLowerCase()
						.contains(auteurOuTitre.toLowerCase()))) {
					correspondance = true;
				}

			}

			// Si l'un des tests est positif on ajoute le livre testé à la liste des
			// résultats
			if (correspondance) {

				// Si le livre est de type livreImprime
				if (livre instanceof LivreImprime) {
					livresCorrespondants.add(new IdentifiantEtTypeArticle(livre.getId(), "LivreImprime"));
				} else {
					// Sinon il est de type livreNumerique
					livresCorrespondants.add(new IdentifiantEtTypeArticle(livre.getId(), "LivreNumerique"));
				}

			}

		}

		// Avant de retourner le résultat, on vérifie que la liste n'est pas vide sinon
		// on lève une exception
		if (livresCorrespondants.isEmpty()) {
			throw new ListeVideException();
		}

		return livresCorrespondants;
	}

	/**
	 * Enregistre une consultation anonyme.
	 * 
	 * @param consultationAnonyme Consultation non associée à un client.
	 * 
	 * @return nouvelleConsultation Consultation enregistrée.
	 * 
	 * @throws EcritureBaseDonneesException Si l'écriture dans la base de données échoue.
	 * @throws ConsultationNonAnonymeException Si on obtient des informations clients avec la consultation.
	 * 
	 * @since 1.0
	 */
	public Consultation ajoutConsultationAnonyme(Consultation consultationAnonyme)
			throws EcritureBaseDonneesException, ConsultationNonAnonymeException {

		// On doit vérifier que la consultation transmise ne contienne pas d'information
		// client, sinon on lève une exception.
		if (consultationAnonyme.getClient() != null) {
			throw new ConsultationNonAnonymeException();
		}

		Consultation nouvelleConsultation;

		try {
			nouvelleConsultation = consultationRepository.save(consultationAnonyme);
		} catch (Exception exception) {
			// En cas d'erreur à l'écriture on lève une exception.
			throw new EcritureBaseDonneesException();
		}

		return nouvelleConsultation;
	}

	/**
	 * Enregistre une consultation d'un client identifié. L'entrée et la sortie
	 * sont de type ConsultationAvecIdClient.
	 * 
	 * @param consultationAvecIdClient Consultation associée à un client.
	 * 
	 * @return consultationEnregistreeAvecIdClient Consultation enregistrée avec l'id client.
	 * 
	 * @throws LectureBaseDonneesException Si la lecture de la base de données échoue.
	 * @throws EcritureBaseDonneesException Si l'écriture dans la base de données échoue.
	 * @throws ClientInconnuException Si l'identifiant client est inconnu.
	 * 
	 * @see fr.pythie.webservice.communication.ConsultationAvecIdClient
	 * 
	 * @since 1.0
	 */
	public ConsultationAvecIdClient ajoutConsultationClient(ConsultationAvecIdClient consultationAvecIdClient)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException {

		// On vérifie que l'identifiant client correspond à un utilisateur enregistré.
		Client client;

		try {
			client = clientRepository.findById(consultationAvecIdClient.getIdClient()).orElse(null);
		} catch (Exception exception) {
			// En cas d'erreur de lecture on lève une exception.
			throw new LectureBaseDonneesException();
		}

		// Si l'identifiant client n'est pas reconnu, on lève une exception.
		if (client == null) {
			throw new ClientInconnuException();
		}

		// On ajoute le client à la consultation.
		consultationAvecIdClient.getConsultation().setClient(client);

		// On ajoute la consultation au client.
		ArrayList<Consultation> consultationsClient = (ArrayList<Consultation>) client.getConsultations();
		consultationsClient.add(consultationAvecIdClient.getConsultation());
		client.setConsultations(consultationsClient);

		// On enregistre la consultation.
		Consultation consultationEnregistree;

		try {
			consultationEnregistree = consultationRepository.save(consultationAvecIdClient.getConsultation());
		} catch (Exception exception) {
			// En cas d'erreur d'écriture on lève une exception.
			throw new EcritureBaseDonneesException();
		}

		// On enregistre les modifications sur le client.
		Client clientModifie;

		try {
			clientModifie = clientRepository.save(client);
		} catch (Exception exception) {
			// En cas d'erreur d'écriture on lève une exception.
			throw new EcritureBaseDonneesException();
		}

		ConsultationAvecIdClient consultationEnregistreeAvecIdClient = new ConsultationAvecIdClient(consultationEnregistree, clientModifie.getId());
		
		return consultationEnregistreeAvecIdClient;
	}

	/**
	 * Ajoute un client à une consultation anonyme. L'entrée et la sortie
	 * sont de type ConsultationAvecIdClient.
	 * 
	 * @param consultationAvecIdClient Consultation associée à un client.
	 * 
	 * @return consultationEnregistreeAvecIdClient Consultation enregistrée avec l'id client.
	 * 
	 * @throws LectureBaseDonneesException Si la lecture de la base de données échoue.
	 * @throws EcritureBaseDonneesException Si l'écriture dans la base de données échoue.
	 * @throws ClientInconnuException Si l'identifiant client est inconnu.
	 * 
	 * @see fr.pythie.webservice.communication.ConsultationAvecIdClient
	 * 
	 * @since 1.0
	 */
	public ConsultationAvecIdClient ajoutClientAConsultation(ConsultationAvecIdClient consultationAvecIdClient)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ConsultationNonAnonymeException,
			ClientInconnuException, ConsultationInconnueException {

		// On vérifie que l'identifiant client correspond à un utilisateur enregistré.
		Client client;

		try {
			client = clientRepository.findById(consultationAvecIdClient.getIdClient()).orElse(null);
		} catch (Exception exception) {
			// En cas d'erreur de lecture on lève une exception.
			throw new LectureBaseDonneesException();
		}

		// Si l'identifiant client n'est pas reconnu, on lève une exception.
		if (client == null) {
			throw new ClientInconnuException();
		}

		// On vérifie que la consultation corresponde à une consultation enregistrée.
		Consultation consultationAncienne;

		try {
			consultationAncienne = consultationRepository.findById(consultationAvecIdClient.getConsultation().getId())
					.orElse(null);
		} catch (Exception exception) {
			// En cas d'erreur de lecture on lève une exception.
			throw new LectureBaseDonneesException();
		}

		// Si la consultation est inconnue, on lève une exception.
		if (consultationAncienne == null) {
			throw new ConsultationInconnueException();
		}

		// On vérifie que cette consultation était anonyme sinon on lève une exception.
		if (consultationAncienne.getClient() != null) {
			throw new ConsultationNonAnonymeException();
		}

		// On ajoute le client à la consultation.
		consultationAncienne.setClient(client);

		// On ajoute la consultation au client.
		ArrayList<Consultation> consultationsClient = (ArrayList<Consultation>) client.getConsultations();
		consultationsClient.add(consultationAncienne);
		client.setConsultations(consultationsClient);

		// On enregistre l'ajout du client à la consultation.
		Consultation consultationModifiee;

		try {
			consultationModifiee = consultationRepository.save(consultationAvecIdClient.getConsultation());
		} catch (Exception exception) {
			// En cas d'erreur d'écriture on lève une exception.
			throw new EcritureBaseDonneesException();
		}

		// On enregistre les modifications sur le client.
		Client clientModifie;

		try {
			clientModifie = clientRepository.save(client);
		} catch (Exception exception) {
			// En cas d'erreur d'écriture on lève une exception.
			throw new EcritureBaseDonneesException();
		}

		return new ConsultationAvecIdClient(consultationModifiee, clientModifie.getId());
	}

	/**
	 * Retourne le stock des livres imprimés à partir d'une liste d'identifiants.
	 * Conserve l'ordre de la liste transmise.
	 * 
	 * @param listeIdLivresImprimes Liste d'identifiants.
	 * 
	 * @return disponibiliteLivresImprime Liste des stocks.
	 * 
	 * @throws LectureBaseDonneesException Si la lecture de la base de données échoue.
	 * @throws ListeVideException Si la liste d'identifiants est vide.
	 * @throws IdInvalideException Si l'un des identifiants est invalide.
	 * 
	 * @since 1.0
	 */
	public ArrayList<Integer> consulterDisponibiliteLivresImprimes(List<Long> listeIdLivresImprimes)
			throws LectureBaseDonneesException, ListeVideException, IdInvalideException {
		// On prépare la liste de résultats
		ArrayList<Integer> disponibiliteLivresImprime = new ArrayList<Integer>();

		// On vérifie que la liste d'ID contienne au moins un élément. Sinon on lève une
		// exception.
		if (listeIdLivresImprimes == null || listeIdLivresImprimes.isEmpty()) {
			throw new ListeVideException();
		}

		// Boucle sur les id de livres imprimés dont on veut connaitre le stock.
		for (Long idLivreImprime : listeIdLivresImprimes) {

			LivreImprime livreImprime;

			// On recherche le livre imprimé correspondant à l'ID demandé
			try {
				livreImprime = livreImprimeRepository.findById(idLivreImprime).orElse(null);
			} catch (Exception e) {
				// En cas d'erreur on lève une exception indicant le problème de lecture
				throw new LectureBaseDonneesException();
			}

			// On doit vérifier que l'on a obtenu un livre valide. Sinon on a reçu un
			// mauvais ID et on doit l'indiquer par une exception.
			if (livreImprime == null || livreImprime.getId() == 0) {
				throw new IdInvalideException();
			}

			// Enfin on peut extraire la quantité en stock.
			disponibiliteLivresImprime.add(livreImprime.getQuantiteStock());

		}

		return disponibiliteLivresImprime;
	}

}