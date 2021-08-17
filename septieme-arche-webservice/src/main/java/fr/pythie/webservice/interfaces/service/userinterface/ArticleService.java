package fr.pythie.webservice.interfaces.service.userinterface;

import java.util.ArrayList;
import java.util.List;

import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.ConsultationInconnueException;
import fr.pythie.webservice.exception.ConsultationNonAnonymeException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.exception.ListeResultatVideException;
import fr.pythie.webservice.model.Article;
import fr.pythie.webservice.model.Consultation;
import fr.pythie.webservice.model.Livre;

public interface ArticleService {
	/**
	 * Interface pour définir les méthodes attendues pour le controller REST
	 * traîtant les requêtes concernant les articles, provenant de l'interface
	 * utilisateur web.
	 */

	public List<Article> obtenirListeArticleParDefaut() throws LectureBaseDonneesException;

	public List<Livre> obtenirListeLivresParAuteurOuTitre(String auteurOuTitre) throws LectureBaseDonneesException, ListeResultatVideException;

	public Consultation ajoutConsultationAnonyme(Consultation consultationAnonyme)
			throws EcritureBaseDonneesException, ConsultationNonAnonymeException;

	public Consultation ajoutConsultationClient(Consultation consultationClient)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException;

	public Consultation ajoutClientAConsultation(Consultation consultationAvecClient)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ConsultationNonAnonymeException,
			ClientInconnuException, ConsultationInconnueException;

	public ArrayList<Integer> consulterDisponibiliteLivresImprimes(List<Long> listeIdLivresImprimes)
			throws LectureBaseDonneesException, ListeResultatVideException;

}
