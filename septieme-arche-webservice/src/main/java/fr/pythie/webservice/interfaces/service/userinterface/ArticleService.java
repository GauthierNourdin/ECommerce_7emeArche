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

/**
 * Interface pour définir les méthodes attendues pour le controller REST
 * traîtant les requêtes concernant les articles, provenant de l'interface
 * utilisateur web.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public interface ArticleService {

	/**
	 * 
	 * @return
	 * @throws LectureBaseDonneesException
	 */
	public List<IdentifiantEtTypeArticle> obtenirListeArticleParDefaut() throws LectureBaseDonneesException;

	/**
	 * 
	 * @param listeIdLivresImprimes
	 * @return
	 * @throws LectureBaseDonneesException
	 * @throws ListeVideException
	 * @throws IdInvalideException
	 */
	public ArrayList<LivreImprime> obtenirListeLivresImprimes(List<Long> listeIdLivresImprimes) throws LectureBaseDonneesException, ListeVideException, IdInvalideException;

	/**
	 * 
	 * @param listeIdLivresNumeriques
	 * @return
	 * @throws LectureBaseDonneesException
	 * @throws ListeVideException
	 * @throws IdInvalideException
	 */
	public ArrayList<LivreNumerique> obtenirListeLivresNumeriques(List<Long> listeIdLivresNumeriques) throws LectureBaseDonneesException, ListeVideException, IdInvalideException;
	
	/**
	 * 
	 * @param auteurOuTitre
	 * @return
	 * @throws LectureBaseDonneesException
	 * @throws ListeVideException
	 */
	public List<IdentifiantEtTypeArticle> obtenirListeLivresParAuteurOuTitre(String auteurOuTitre) throws LectureBaseDonneesException, ListeVideException;

	/**
	 * 
	 * @param consultationAnonyme
	 * @return
	 * @throws EcritureBaseDonneesException
	 * @throws ConsultationNonAnonymeException
	 */
	public Consultation ajoutConsultationAnonyme(Consultation consultationAnonyme)
			throws EcritureBaseDonneesException, ConsultationNonAnonymeException;

	/**
	 * 
	 * @param consultationClient
	 * @return
	 * @throws LectureBaseDonneesException
	 * @throws EcritureBaseDonneesException
	 * @throws ClientInconnuException
	 */
	public ConsultationAvecIdClient ajoutConsultationClient(ConsultationAvecIdClient consultationClient)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException;

	/**
	 * 
	 * @param consultationAvecClient
	 * @return
	 * @throws LectureBaseDonneesException
	 * @throws EcritureBaseDonneesException
	 * @throws ConsultationNonAnonymeException
	 * @throws ClientInconnuException
	 * @throws ConsultationInconnueException
	 */
	public ConsultationAvecIdClient ajoutClientAConsultation(ConsultationAvecIdClient consultationAvecClient)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ConsultationNonAnonymeException,
			ClientInconnuException, ConsultationInconnueException;

	/**
	 * 
	 * @param listeIdLivresImprimes
	 * @return
	 * @throws LectureBaseDonneesException
	 * @throws ListeVideException
	 * @throws IdInvalideException
	 */
	public ArrayList<Integer> consulterDisponibiliteLivresImprimes(List<Long> listeIdLivresImprimes)
			throws LectureBaseDonneesException, ListeVideException, IdInvalideException;


}
