package fr.pythie.webservice.service.userinterface;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pythie.webservice.dao.CommandeRepository;
import fr.pythie.webservice.dao.LivreImprimeRepository;
import fr.pythie.webservice.dao.LivreRepository;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.ConsultationNonAnonymeException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.IdInvalideException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.exception.ListeVideException;
import fr.pythie.webservice.interfaces.service.userinterface.ArticleService;
import fr.pythie.webservice.model.Article;
import fr.pythie.webservice.model.Auteur;
import fr.pythie.webservice.model.Commande;
import fr.pythie.webservice.model.Consultation;
import fr.pythie.webservice.model.LigneCommande;
import fr.pythie.webservice.model.Livre;
import fr.pythie.webservice.model.LivreImprime;

@Component
public class ArticleServiceImpl implements ArticleService {
	/**
	 * Classe service implémentant les méthodes demandées par ArticleService afin de
	 * traîter les demandes concernant les articles faites par l'interface
	 * utilisateur
	 */

	@Autowired
	private CommandeRepository commandeRepository;

	@Autowired
	private LivreRepository livreRepository;

	@Autowired
	private LivreImprimeRepository livreImprimeRepository;

	public List<Article> obtenirListeArticleParDefaut() throws LectureBaseDonneesException {
		// On détermine d'abord la date servant à trier les lignes de commandes (quatre
		// semaines)
		LocalDateTime dateDepartRecherche = LocalDateTime.now().minusDays(28);

		ArrayList<Article> listeArticleParDefaut = new ArrayList<Article>();
		ArrayList<Commande> commandesMois = new ArrayList<Commande>();

		// On récupère les données des commandes de la base de données. On lève une
		// exception en cas d'échec.
		try {
			commandesMois = (ArrayList<Commande>) commandeRepository.findByDateAfter(dateDepartRecherche);
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

		// Boucle sur les commandes
		for (Commande comm : commandesMois) {

			// Boucle sur les lignes de commandes d'une commande
			for (LigneCommande ligneComm : comm.getLignesCommande()) {

				// Indicateur de correspondance
				boolean correspondance = false;

				// Boucle sur les lignes de commandes de la liste résultat
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
			listeArticleParDefaut.add(ligneCommRes.getArticle());
		}

		return listeArticleParDefaut;
	}

	public List<Livre> obtenirListeLivresParAuteurOuTitre(String auteurOuTitre)
			throws LectureBaseDonneesException, ListeVideException {
		// On prépare la liste de résultats
		ArrayList<Livre> livresCorrespondants = new ArrayList<Livre>();

		/*
		 * On recherche les livres pour lesquels le prénom de l'auteur, ou son nom, ou
		 * son prenom + nom, ou le titre contient la chaîne de caractère souhaitée On ne
		 * peut pas faire de requête SQL directe (trop compliqué et peu fiable). On doit
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

		// Boucle sur les livres enregistrés
		for (Livre livre : livresEnregistrees) {

			boolean correspondance = false;

			// Test sur le titre du livre
			if (livre.getTitreLivre().toLowerCase().contains(auteurOuTitre.toLowerCase())) {
				correspondance = true;
			}

			// Boucle sur les auteurs du livre
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
				if (!correspondance && ((auteur.getPrenom() + ' ' + auteur.getNom()).toLowerCase()
						.contains(auteurOuTitre.toLowerCase()))) {
					correspondance = true;
				}

			}

			// Si l'un des tests est positif on ajoute le livre testé à la liste des
			// résultats
			if (correspondance) {
				livresCorrespondants.add(livre);
			}

		}

		// Avant de retourner le résultat, on vérifie que la liste n'est pas vide sinon
		// on lève une exception
		if (livresCorrespondants.isEmpty()) {
			throw new ListeVideException();
		}

		return livresCorrespondants;
	}

	public Consultation ajoutConsultationAnonyme(Consultation consultationAnonyme)
			throws EcritureBaseDonneesException, ConsultationNonAnonymeException {
		// A faire avant la page article Angular
		return null;
	}

	public Consultation ajoutConsultationClient(Consultation consultationClient)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException {
		// A faire avant la page article Angular
		return null;
	}

	public Consultation ajoutClientAConsultation(Consultation consultationAvecClient)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ConsultationNonAnonymeException,
			ClientInconnuException {
		// A faire avant le popup de connexion Angular
		return null;
	}

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