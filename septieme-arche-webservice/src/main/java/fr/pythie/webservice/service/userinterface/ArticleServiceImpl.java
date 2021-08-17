package fr.pythie.webservice.service.userinterface;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pythie.webservice.dao.CommandeRepository;
import fr.pythie.webservice.dao.LivreRepository;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.ConsultationNonAnonymeException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.interfaces.service.userinterface.ArticleService;
import fr.pythie.webservice.model.Article;
import fr.pythie.webservice.model.Commande;
import fr.pythie.webservice.model.Consultation;
import fr.pythie.webservice.model.LigneCommande;
import fr.pythie.webservice.model.Livre;

@Component
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private CommandeRepository commandeRepository;
	
	@Autowired
	private LivreRepository livreRepository;

	@Override
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
		 * faire le tri par le volume de vente (via un comparateur anonyme). L'ordre est décroissant
		 */
		Collections.sort(lignesCommandesResultat, new Comparator<LigneCommande>() {
		    public int compare(LigneCommande ligneCommande1, LigneCommande ligneCommande2) {
		    	return ligneCommande2.getQuantiteCommandee() - ligneCommande1.getQuantiteCommandee();
		    }
		});
		
		// Reste à récupérer les 50 premiers résultats, si leur nombre est suffisant, ou la totalité
		if (lignesCommandesResultat.size() > 50) {
			lignesCommandesResultat = (ArrayList<LigneCommande>) lignesCommandesResultat.subList(0, 49);
		}
		
		// Dès lors il faut extraire les articles correspondant à ces lignes et préparer la liste de retour
		for (LigneCommande ligneCommRes : lignesCommandesResultat) {
			listeArticleParDefaut.add(ligneCommRes.getArticle());
		}
		
		return listeArticleParDefaut;
	}

	@Override
	public List<Livre> obtenirListeLivresParAuteurOuTitre(String auteurOuTitre) throws LectureBaseDonneesException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consultation ajoutConsultationAnonyme(Consultation consultationAnonyme)
			throws EcritureBaseDonneesException, ConsultationNonAnonymeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consultation ajoutConsultationClient(Consultation consultationClient)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consultation ajoutClientAConsultation(Consultation consultationAvecClient)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ConsultationNonAnonymeException,
			ClientInconnuException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Integer> consulterDisponibiliteLivresImprimes(List<Long> listeIdLivresImprimes)
			throws LectureBaseDonneesException {
		// TODO Auto-generated method stub
		return null;
	}

}