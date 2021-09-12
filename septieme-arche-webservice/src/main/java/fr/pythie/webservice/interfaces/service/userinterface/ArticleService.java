package fr.pythie.webservice.interfaces.service.userinterface;

import java.util.ArrayList;
import java.util.List;

import fr.pythie.webservice.communication.ConsultationAvecIdClient;
import fr.pythie.webservice.communication.IdentifiantEtTypeArticle;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.ConsultationInconnueException;
import fr.pythie.webservice.exception.ConsultationNonAnonymeException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.IdInvalideException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.exception.ListeVideException;
import fr.pythie.webservice.model.Consultation;
import fr.pythie.webservice.model.LivreImprime;
import fr.pythie.webservice.model.LivreNumerique;

public interface ArticleService {
	/**
	 * Interface pour définir les méthodes attendues pour le controller REST
	 * traîtant les requêtes concernant les articles, provenant de l'interface
	 * utilisateur web.
	 */

	public List<IdentifiantEtTypeArticle> obtenirListeArticleParDefaut() throws LectureBaseDonneesException;

	public ArrayList<LivreImprime> obtenirListeLivresImprimes(List<Long> listeIdLivresImprimes) throws LectureBaseDonneesException, ListeVideException, IdInvalideException;

	public ArrayList<LivreNumerique> obtenirListeLivresNumeriques(List<Long> listeIdLivresNumeriques) throws LectureBaseDonneesException, ListeVideException, IdInvalideException;
	
	public List<IdentifiantEtTypeArticle> obtenirListeLivresParAuteurOuTitre(String auteurOuTitre) throws LectureBaseDonneesException, ListeVideException;

	public Consultation ajoutConsultationAnonyme(Consultation consultationAnonyme)
			throws EcritureBaseDonneesException, ConsultationNonAnonymeException;

	public ConsultationAvecIdClient ajoutConsultationClient(ConsultationAvecIdClient consultationClient)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException;

	public ConsultationAvecIdClient ajoutClientAConsultation(ConsultationAvecIdClient consultationAvecClient)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ConsultationNonAnonymeException,
			ClientInconnuException, ConsultationInconnueException;

	public ArrayList<Integer> consulterDisponibiliteLivresImprimes(List<Long> listeIdLivresImprimes)
			throws LectureBaseDonneesException, ListeVideException, IdInvalideException;


}
