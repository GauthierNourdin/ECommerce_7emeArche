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
import fr.pythie.webservice.exception.MauvaisStringException;
import fr.pythie.webservice.model.Consultation;
import fr.pythie.webservice.model.LivreImprime;
import fr.pythie.webservice.model.LivreNumerique;

/**
 * Interface pour définir les méthodes traîtant les requêtes concernant les articles.
 * Les requêtes proviennent des controllers REST répondant au site web.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public interface ArticleService {

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
	public List<IdentifiantEtTypeArticle> obtenirListeArticleParDefaut() throws LectureBaseDonneesException;

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
	public ArrayList<LivreImprime> obtenirListeLivresImprimes(List<Long> listeIdLivresImprimes) throws LectureBaseDonneesException, ListeVideException, IdInvalideException;

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
	public ArrayList<LivreNumerique> obtenirListeLivresNumeriques(List<Long> listeIdLivresNumeriques) throws LectureBaseDonneesException, ListeVideException, IdInvalideException;
	
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
	 * @throws MauvaisStringException Si la chaîne de caractère est vide.
	 * 
	 * @see fr.pythie.webservice.communication.IdentifiantEtTypeArticle
	 * 
	 * @since 1.0
	 */
	public List<IdentifiantEtTypeArticle> obtenirListeLivresParAuteurOuTitre(String auteurOuTitre) throws LectureBaseDonneesException, ListeVideException, MauvaisStringException;

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
			throws EcritureBaseDonneesException, ConsultationNonAnonymeException;

	/**
	 * Enregistre une consultation d'un client identifié. L'entrée et la sortie
	 * sont de type ConsultationAvecIdClient.
	 * 
	 * @param consultationClient Consultation associée à un client.
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
	public ConsultationAvecIdClient ajoutConsultationClient(ConsultationAvecIdClient consultationClient)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException;

	/**
	 * Ajoute un client à une consultation anonyme. L'entrée et la sortie
	 * sont de type ConsultationAvecIdClient.
	 * 
	 * @param consultationAvecClient Consultation associée à un client.
	 * 
	 * @return consultationEnregistreeAvecIdClient Consultation enregistrée avec l'id client.
	 * 
	 * @throws LectureBaseDonneesException Si la lecture de la base de données échoue.
	 * @throws EcritureBaseDonneesException Si l'écriture dans la base de données échoue.
	 * @throws ConsultationNonAnonymeException Si la consultation n'est pas anonyme.
	 * @throws ClientInconnuException Si l'identifiant client est inconnu.
	 * @throws ConsultationInconnueException Si la consultation n'est pas connue.
	 * 
	 * @see fr.pythie.webservice.communication.ConsultationAvecIdClient
	 * 
	 * @since 1.0
	 */
	public ConsultationAvecIdClient ajoutClientAConsultation(ConsultationAvecIdClient consultationAvecClient)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, ConsultationNonAnonymeException,
			ClientInconnuException, ConsultationInconnueException;

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
			throws LectureBaseDonneesException, ListeVideException, IdInvalideException;


}
