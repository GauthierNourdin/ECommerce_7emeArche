package fr.pythie.webservice.tests.service.userinterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import fr.pythie.webservice.communication.ConsultationAvecIdClient;
import fr.pythie.webservice.communication.IdentifiantEtTypeArticle;
import fr.pythie.webservice.dao.AdresseRepository;
import fr.pythie.webservice.dao.ArticleRepository;
import fr.pythie.webservice.dao.AuteurRepository;
import fr.pythie.webservice.dao.ClientRepository;
import fr.pythie.webservice.dao.CommandeRepository;
import fr.pythie.webservice.dao.ConsultationRepository;
import fr.pythie.webservice.dao.EditeurRepository;
import fr.pythie.webservice.dao.FactureRepository;
import fr.pythie.webservice.dao.GenreRepository;
import fr.pythie.webservice.dao.LigneCommandeRepository;
import fr.pythie.webservice.dao.LivreImprimeRepository;
import fr.pythie.webservice.dao.LivreNumeriqueRepository;
import fr.pythie.webservice.dao.LivreRepository;
import fr.pythie.webservice.dao.PersonneRepository;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.ConsultationNonAnonymeException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.IdInvalideException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.exception.ListeVideException;
import fr.pythie.webservice.exception.MauvaisStringException;
import fr.pythie.webservice.model.Adresse;
import fr.pythie.webservice.model.Auteur;
import fr.pythie.webservice.model.Client;
import fr.pythie.webservice.model.Commande;
import fr.pythie.webservice.model.Consultation;
import fr.pythie.webservice.model.Editeur;
import fr.pythie.webservice.model.Facture;
import fr.pythie.webservice.model.Genre;
import fr.pythie.webservice.model.LigneCommande;
import fr.pythie.webservice.model.Livre;
import fr.pythie.webservice.model.LivreImprime;
import fr.pythie.webservice.model.LivreNumerique;
import fr.pythie.webservice.service.userinterface.ArticleServiceImpl;

/*
 * Teste l'ensemble des services traîtant des articles.
 */
@SpringBootTest
public class ArticleServiceImplTest {

	@Mock
    private AdresseRepository adresseRepository;
	
	@Mock
    private ArticleRepository articleRepository;
	
	@Mock
    private AuteurRepository auteurRepository;
	
	@Mock
    private ClientRepository clientRepository;
	
	@Mock
    private CommandeRepository commandeRepository;
	
	@Mock
    private ConsultationRepository consultationRepository;
	
	@Mock
    private EditeurRepository editeurRepository;
	
	@Mock
    private FactureRepository factureRepository;
	
	@Mock
    private GenreRepository genreRepository;
	
	@Mock
    private LigneCommandeRepository ligneCommandeRepository;
	
	@Mock
    private LivreImprimeRepository livreImprimeRepository;
	
	@Mock
    private LivreNumeriqueRepository livreNumeriqueRepository;
	
	@Mock
    private LivreRepository livreRepository;
	
	@Mock
    private PersonneRepository personneRepository;
	
	// Injecte automatiquement les répositories. 
	@InjectMocks
	private ArticleServiceImpl articleServiceImpl = new ArticleServiceImpl();
	
	// Test de la méthode obtenirListeArticleParDefaut dans le cas standard.
	@Test
	void testObtenirListeArticleParDefaut() throws LectureBaseDonneesException {
		
		// Préparation de la liste des commandes pertinantes.
		List<Commande> commandesPertinantes = new ArrayList<Commande>();
		
		Adresse adresse1 = new Adresse(1L, "24 bis", "Rue D'Indochine", "35200", "Rennes", "France", "");
		Adresse adresse2 = new Adresse(2L, "18", "Avenue des Frères Lumière", "69003", "Lyon", "France", "");
		Adresse adresse4 = new Adresse(4L, "14", "Stirling Street", "EH10", "Edinburgh", "Ecosse", "");
		Adresse adresse5 = new Adresse(5L, "4 ter", "Allée du 7ème Art", "33400", "Talence", "France", "Appartement 326");
		Adresse adresse6 = new Adresse(6L, "117", "Avenue de Stalingrad", "75013", "Paris", "France", "");
		
		// Editeurs sans les livres.
		Editeur editeur1 = new Editeur(1L, "Editions 7ème Art", adresse1, new ArrayList<Livre>());
		Editeur editeur2 = new Editeur(2L, "Editions Philosophie et Arts", adresse2, new ArrayList<Livre>());
		Editeur editeur3 = new Editeur(3L, "Groupe Argonaute", adresse2, new ArrayList<Livre>());
		
		// Clients sans les consultations ni les commandes.
		Client client1 = new Client(1L, "M", "Finan","Didier","didier.finan@gmail.com","SpyFilmsAreTheBest","1565-4961-1787-1857","08/22","975", adresse4,adresse4, new ArrayList<Consultation>(), new ArrayList<Commande>());
		Client client2 = new Client(2L, "M", "Hillfort","William E.","william.hillfort@protonmail.com","AlainDelon","1765-8273-2745-5818","04/23","170",adresse1,adresse5, new ArrayList<Consultation>(), new ArrayList<Commande>());
		Client client3 = new Client(3L, "Mme", "Lamour","Mathilde","mathile@lamour.com","LeCinemaCestLaVie","","","",adresse6,adresse6, new ArrayList<Consultation>(), new ArrayList<Commande>());
		
		// Auteurs sans les livres.
		Auteur auteur1 = new Auteur(1L, "M", "Picard", "Bernard", new ArrayList<Livre>());
		Auteur auteur2 = new Auteur(2L, "M", "Shoeffer", "Edouard", new ArrayList<Livre>());
		Auteur auteur3 = new Auteur(3L, "M", "Kurkov", "Alexey", new ArrayList<Livre>());
		Auteur auteur4 = new Auteur(4L, "M", "Ryan", "Lyrol", new ArrayList<Livre>());
		Auteur auteur5 = new Auteur(5L, "M", "Labbe", "Antoine", new ArrayList<Livre>());
		Auteur auteur6 = new Auteur(6L, "Mme", "Sancta", "Maria", new ArrayList<Livre>());
		
		// Genres sans les livres.
		Genre genre1 = new Genre(1L, "Cinéma américain", new ArrayList<Livre>());
		Genre genre2 = new Genre(2L, "Biographie", new ArrayList<Livre>());
		Genre genre3 = new Genre(3L, "Livre d'exposition", new ArrayList<Livre>());
		Genre genre4 = new Genre(4L, "Historique", new ArrayList<Livre>());
		Genre genre5 = new Genre(5L, "Cinéma asiatique", new ArrayList<Livre>());
		Genre genre6 = new Genre(6L, "Analyse", new ArrayList<Livre>());
		Genre genre7 = new Genre(7L, "Documentaire", new ArrayList<Livre>());
		
		// Livres numériques sans les consultations ni les lignes de commandes.
		ArrayList<Genre> genresLivreNum1 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreNum2 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreNum3 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreNum4 = new ArrayList<Genre>();
		
		genresLivreNum1.add(genre1);
		genresLivreNum1.add(genre2);
		genresLivreNum2.add(genre3);
		genresLivreNum2.add(genre4);
		genresLivreNum3.add(genre5);
		genresLivreNum3.add(genre6);
		genresLivreNum4.add(genre4);
		genresLivreNum4.add(genre7);
		
		ArrayList<Auteur> auteursLivreNum1 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreNum2 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreNum3 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreNum4 = new ArrayList<Auteur>();
				
		auteursLivreNum1.add(auteur1);
		auteursLivreNum2.add(auteur2);
		auteursLivreNum3.add(auteur3);
		auteursLivreNum4.add(auteur3);
		
		LivreNumerique livreNumerique1 = new LivreNumerique(1L, "Archives du musée du cinéma de Los Angeles : Charlie Chaplin", "Recueil de photographies, de témoignages et de notes, autour du parcours de Charlie Chaplin en tant qu'acteur et réalisateur.", 1895, 1999, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-713-5", "Charlie Chaplin", ".pdf", "Charlie_Chaplin_Ed7emeArt.png", 98, LocalDate.parse("2014-10-18"), genresLivreNum1, auteursLivreNum1, editeur1, 275.4, "Mo");
		LivreNumerique livreNumerique2 = new LivreNumerique(2L, "Musée des Arts et Métiers, Livre d'exposition : Les premiers pas du cinéma", "Livre de l'exposition temporaire début 2015 du musée des Arts et Métiers. Sujet centré sur le matériel et les techniques de tournage et de projection au début du cinéma à 1920", 1421, 1499, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-804-2", "Les premiers pas du cinéma", ".pdf", "Les_premiers_pas_du_cinema_Ed7emeArt.png", 74, LocalDate.parse("2015-01-24"), genresLivreNum2, auteursLivreNum2, editeur1, 115.8, "Mo");
		LivreNumerique livreNumerique3 = new LivreNumerique(3L, "Bollywood, simple copie de l'Occident ? Avec préface de l'auteur", "Analyse complète du cinéma bollywoodien contemporain et comparaison avec le cinéma américain et européen.", 2179, 2299, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-17172-074-0", "Bollywood, simple copie de l'Occident ?", ".pdf", "Bollywood_simple_copie_de_l_Occident_EdPhiloEtArt.png", 388, LocalDate.parse("2019-06-04"), genresLivreNum3, auteursLivreNum3, editeur2, 44.2, "Mo");
		
		// Livres imprimés sans les consultations ni les lignes de commandes.
		ArrayList<Genre> genresLivreImp1 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreImp2 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreImp3 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreImp4 = new ArrayList<Genre>();
		
		genresLivreImp1.add(genre1);
		genresLivreImp1.add(genre7);
		genresLivreImp2.add(genre1);
		genresLivreImp2.add(genre2);
		genresLivreImp2.add(genre7);
		genresLivreImp3.add(genre6);
		genresLivreImp4.add(genre4);
		genresLivreImp4.add(genre7);
		
		ArrayList<Auteur> auteursLivreImp1 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreImp2 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreImp3 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreImp4 = new ArrayList<Auteur>();
		
		auteursLivreImp1.add(auteur1);
		auteursLivreImp1.add(auteur6);
		auteursLivreImp2.add(auteur1);
		auteursLivreImp2.add(auteur2);
		auteursLivreImp3.add(auteur4);
		auteursLivreImp4.add(auteur5);
		auteursLivreImp4.add(auteur6);
		
		LivreImprime livreImprime1 = new LivreImprime(5L, "Dans les coulises du tournage de Mission Impossible, 2e édition", "Recueil de photographies et de témoignages autour des différents tournages de la série de films Mission Impossible.", 3222, 3399, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-954-7", "Dans les coulises du tournage de Mission Impossible", "Grand carré à couverture rigide", "Dans_les_coulises_du_tournage_de_Mission Impossible_Ed7emeArt.png", 186, LocalDate.parse("2020-08-05"), genresLivreImp1, auteursLivreImp1, editeur1, 4, LocalDate.parse("2020-08-02"), LocalDate.parse("2020-12-28"), 359.4, "g", 21.0, 21.0, 1.63, "cm");
		LivreImprime livreImprime2 = new LivreImprime(6L, "Georges Lucas et Star Wars, derrière la saga, édition de luxe", "Bibliographie illustrée de Georges Lucas avec une interview autour de son travail autour de Star Wars.", 3791, 3999, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-27508-154-7", "Georges Lucas et Star Wars, derrière la saga", "A4 à couverture rigide", "Georges_Lucas_et_Star_Wars_derriere_la_saga_GpArgo.png", 238, LocalDate.parse("2016-11-28"), genresLivreImp2, auteursLivreImp2, editeur3, 1, LocalDate.parse("2016-11-13"), LocalDate.parse("2018-04-30"), 645.5, "g", 29.7, 21.0, 2.07, "cm");
		LivreImprime livreImprime3 = new LivreImprime(7L, "1944-2019 : soixante-quinze ans de films de super-héros, 1ère édition", "Analyse de l'évolution des films de super-héros depuis 1944. Résumés et illustrations d'une cinquante de films représentatifs.", 3032, 3199, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-27508-207-3", "1944-2019 : soixante-quinze ans de films de super-héros", "A5 à couverture rigide", "1944_2019_soixante_quinze_ans_de_films_de_super_heros_GpArgo.png", 314, LocalDate.parse("2020-04-17"), genresLivreImp3, auteursLivreImp3, editeur3, 3, LocalDate.parse("2020-03-17"), null, 422.7, "g", 21.0, 14.8, 2.72, "cm");
		
		// Commandes sans lignes de commande ni factures.
		Commande commande1 = new Commande(1L, "210722173401-00000001", "Expédiée", LocalDateTime.of(2021, 7, 22, 17, 34, 1), new ArrayList<LigneCommande>(), client1, new ArrayList<Facture>());
		Commande commande2 = new Commande(2L, "210802071157-00000001", "Expédiée", LocalDateTime.of(2021, 8, 2, 7, 11, 57), new ArrayList<LigneCommande>(), client1, new ArrayList<Facture>());
		Commande commande3 = new Commande(3L, "210805140247-00000002", "Expédiée", LocalDateTime.of(2021, 8, 5, 14, 2, 47), new ArrayList<LigneCommande>(), client2, new ArrayList<Facture>());
		Commande commande4 = new Commande(4L, "210809222724-00000003", "Payée", LocalDateTime.of(2021, 8, 9, 22, 27, 24), new ArrayList<LigneCommande>(), client3, new ArrayList<Facture>());
		
		// Lignes de commandes.
		LigneCommande ligneCommande1 = new LigneCommande(1L, 1, 1895, 1999, livreNumerique1, commande1);
		LigneCommande ligneCommande2 = new LigneCommande(2L, 2, 3222, 3399, livreImprime1, commande1);
		LigneCommande ligneCommande3 = new LigneCommande(3L, 3, 3791, 3999, livreImprime2, commande1);
		LigneCommande ligneCommande4 = new LigneCommande(4L, 1, 1421, 1499, livreNumerique2, commande2);
		LigneCommande ligneCommande5 = new LigneCommande(5L, 1, 2179, 2299, livreNumerique3, commande2);
		LigneCommande ligneCommande6 = new LigneCommande(6L, 1, 3222, 3399, livreImprime1, commande3);
		LigneCommande ligneCommande7 = new LigneCommande(7L, 2, 3032, 3199, livreImprime3, commande3);
		LigneCommande ligneCommande8 = new LigneCommande(8L, 1, 3222, 3399, livreImprime1, commande4);
		
		// Ajout des lignes de commandes aux commandes.
		ArrayList<LigneCommande> lignesCommandeCommande1 = new ArrayList<LigneCommande>();
		ArrayList<LigneCommande> lignesCommandeCommande2 = new ArrayList<LigneCommande>();
		ArrayList<LigneCommande> lignesCommandeCommande3 = new ArrayList<LigneCommande>();
		ArrayList<LigneCommande> lignesCommandeCommande4 = new ArrayList<LigneCommande>();
		
		lignesCommandeCommande1.add(ligneCommande1);
		lignesCommandeCommande1.add(ligneCommande2);
		lignesCommandeCommande1.add(ligneCommande3);
		lignesCommandeCommande2.add(ligneCommande4);
		lignesCommandeCommande2.add(ligneCommande5);
		lignesCommandeCommande3.add(ligneCommande6);
		lignesCommandeCommande3.add(ligneCommande7);
		lignesCommandeCommande3.add(ligneCommande8);
		
		commande1.setLignesCommande(lignesCommandeCommande1);
		commande2.setLignesCommande(lignesCommandeCommande2);
		commande3.setLignesCommande(lignesCommandeCommande3);
		commande4.setLignesCommande(lignesCommandeCommande4);
		
		commandesPertinantes.add(commande1);
		commandesPertinantes.add(commande2);
		commandesPertinantes.add(commande3);
		commandesPertinantes.add(commande4);
		
		// Mock de l'appel à la DB pour les commandes pertinantes.
		when(commandeRepository.findByDateAfter(any(LocalDateTime.class))).thenReturn(commandesPertinantes);
		
		// Liste résultat attendue.
		List<IdentifiantEtTypeArticle> listeArticleParDefaut = new ArrayList<IdentifiantEtTypeArticle>();
		listeArticleParDefaut.add(new IdentifiantEtTypeArticle(5L, "LivreImprime"));
		listeArticleParDefaut.add(new IdentifiantEtTypeArticle(6L, "LivreImprime"));
		listeArticleParDefaut.add(new IdentifiantEtTypeArticle(7L, "LivreImprime"));
		listeArticleParDefaut.add(new IdentifiantEtTypeArticle(1L, "LivreNumerique"));
		listeArticleParDefaut.add(new IdentifiantEtTypeArticle(2L, "LivreNumerique"));
		listeArticleParDefaut.add(new IdentifiantEtTypeArticle(3L, "LivreNumerique"));
		
		// Test.
		assertEquals(listeArticleParDefaut, articleServiceImpl.obtenirListeArticleParDefaut());
	}
	
	// Test de la méthode obtenirListeLivresImprimes dans le cas standard.
	@Test
	void testObtenirListeLivresImprimes() throws LectureBaseDonneesException, ListeVideException, IdInvalideException {
		
		// Liste des identifiants dont on veut obtenir les livres imprimés.
		List<Long> listeIdLivresImprimes = new ArrayList<Long>();
		listeIdLivresImprimes.add(5L);
		listeIdLivresImprimes.add(7L);
		listeIdLivresImprimes.add(6L);
		
		// Livres imprimés à retourner.
		// Adresses initiales.
		Adresse adresse1 = new Adresse(1L, "24 bis", "Rue D'Indochine", "35200", "Rennes", "France", "");
		Adresse adresse2 = new Adresse(2L, "18", "Avenue des Frères Lumière", "69003", "Lyon", "France", "");
		
		// Editeurs sans les livres.
		Editeur editeur1 = new Editeur(1L, "Editions 7ème Art", adresse1, new ArrayList<Livre>());
		Editeur editeur3 = new Editeur(3L, "Groupe Argonaute", adresse2, new ArrayList<Livre>());
		
		// Auteurs sans les livres.
		Auteur auteur1 = new Auteur(1L, "M", "Picard", "Bernard", new ArrayList<Livre>());
		Auteur auteur2 = new Auteur(2L, "M", "Shoeffer", "Edouard", new ArrayList<Livre>());
		Auteur auteur4 = new Auteur(4L, "M", "Ryan", "Lyrol", new ArrayList<Livre>());
		Auteur auteur6 = new Auteur(6L, "Mme", "Sancta", "Maria", new ArrayList<Livre>());
		
		// Genres sans les livres.
		Genre genre1 = new Genre(1L, "Cinéma américain", new ArrayList<Livre>());
		Genre genre2 = new Genre(2L, "Biographie", new ArrayList<Livre>());
		Genre genre6 = new Genre(4L, "Analyse", new ArrayList<Livre>());
		Genre genre7 = new Genre(6L, "Documentaire", new ArrayList<Livre>());
		
		// Livres imprimés sans les consultations ni les lignes de commandes.
		ArrayList<Genre> genresLivreImp1 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreImp2 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreImp3 = new ArrayList<Genre>();
		
		genresLivreImp1.add(genre1);
		genresLivreImp1.add(genre7);
		genresLivreImp2.add(genre1);
		genresLivreImp2.add(genre2);
		genresLivreImp2.add(genre7);
		genresLivreImp3.add(genre6);
		
		ArrayList<Auteur> auteursLivreImp1 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreImp2 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreImp3 = new ArrayList<Auteur>();
		
		auteursLivreImp1.add(auteur1);
		auteursLivreImp1.add(auteur6);
		auteursLivreImp2.add(auteur1);
		auteursLivreImp2.add(auteur2);
		auteursLivreImp3.add(auteur4);
		
		LivreImprime livreImprime1 = new LivreImprime(5L, "Dans les coulises du tournage de Mission Impossible, 2e édition", "Recueil de photographies et de témoignages autour des différents tournages de la série de films Mission Impossible.", 3222, 3399, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-954-7", "Dans les coulises du tournage de Mission Impossible", "Grand carré à couverture rigide", "Dans_les_coulises_du_tournage_de_Mission Impossible_Ed7emeArt.png", 186, LocalDate.parse("2020-08-05"), genresLivreImp1, auteursLivreImp1, editeur1, 4, LocalDate.parse("2020-08-02"), LocalDate.parse("2020-12-28"), 359.4, "g", 21.0, 21.0, 1.63, "cm");
		LivreImprime livreImprime2 = new LivreImprime(6L, "Georges Lucas et Star Wars, derrière la saga, édition de luxe", "Bibliographie illustrée de Georges Lucas avec une interview autour de son travail autour de Star Wars.", 3791, 3999, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-27508-154-7", "Georges Lucas et Star Wars, derrière la saga", "A4 à couverture rigide", "Georges_Lucas_et_Star_Wars_derriere_la_saga_GpArgo.png", 238, LocalDate.parse("2016-11-28"), genresLivreImp2, auteursLivreImp2, editeur3, 1, LocalDate.parse("2016-11-13"), LocalDate.parse("2018-04-30"), 645.5, "g", 29.7, 21.0, 2.07, "cm");
		LivreImprime livreImprime3 = new LivreImprime(7L, "1944-2019 : soixante-quinze ans de films de super-héros, 1ère édition", "Analyse de l'évolution des films de super-héros depuis 1944. Résumés et illustrations d'une cinquante de films représentatifs.", 3032, 3199, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-27508-207-3", "1944-2019 : soixante-quinze ans de films de super-héros", "A5 à couverture rigide", "1944_2019_soixante_quinze_ans_de_films_de_super_heros_GpArgo.png", 314, LocalDate.parse("2020-04-17"), genresLivreImp3, auteursLivreImp3, editeur3, 3, LocalDate.parse("2020-03-17"), null, 422.7, "g", 21.0, 14.8, 2.72, "cm");
	
		// Liste résultat attendue.
		List<LivreImprime> livresImprimes = new ArrayList<LivreImprime>();
		livresImprimes.add(livreImprime1);
		livresImprimes.add(livreImprime3);
		livresImprimes.add(livreImprime2);
		
		// Mock des appels à la DB pour obtenir les livres imprimés.
		when(livreImprimeRepository.findById(5L)).thenReturn(Optional.of(livreImprime1));
		when(livreImprimeRepository.findById(6L)).thenReturn(Optional.of(livreImprime2));
		when(livreImprimeRepository.findById(7L)).thenReturn(Optional.of(livreImprime3));
		
		// Test.
		assertEquals(livresImprimes, articleServiceImpl.obtenirListeLivresImprimes(listeIdLivresImprimes));
	}
	
	// Test de la méthode obtenirListeLivresImprimes avec une liste d'identifiants vide.
	@Test
	void testObtenirListeLivresImprimesExceptionListeVide() {
		// Liste d'identifiants vide.
		List<Long> listeIdLivresImprimes = new ArrayList<Long>();
		
		// Test.
		assertThrows(ListeVideException.class, () -> { articleServiceImpl.obtenirListeLivresImprimes(listeIdLivresImprimes); });
	}
	
	// Test de la méthode obtenirListeLivresImprimes avec un id invalide.
	@Test
	void testObtenirListeLivresImprimesExceptionIdInvalide() {
		// Liste des identifiants dont on veut obtenir les livres imprimés, avec un ID invalide.
		List<Long> listeIdLivresImprimes = new ArrayList<Long>();
		listeIdLivresImprimes.add(7L);
		listeIdLivresImprimes.add(3L);
		
		// Livre imprimé à retourner.
		// Adresses initiales.
		Adresse adresse2 = new Adresse(2L, "18", "Avenue des Frères Lumière", "69003", "Lyon", "France", "");
		
		// Editeurs sans les livres.
		Editeur editeur3 = new Editeur(3L, "Groupe Argonaute", adresse2, new ArrayList<Livre>());
		
		// Auteurs sans les livres.
		Auteur auteur4 = new Auteur(4L, "M", "Ryan", "Lyrol", new ArrayList<Livre>());
		
		// Genres sans les livres.
		Genre genre6 = new Genre(6L, "Analyse", new ArrayList<Livre>());
		
		// Livres imprimés sans les consultations ni les lignes de commandes.
		ArrayList<Genre> genresLivreImp3 = new ArrayList<Genre>();
		
		genresLivreImp3.add(genre6);
		
		ArrayList<Auteur> auteursLivreImp3 = new ArrayList<Auteur>();
		
		auteursLivreImp3.add(auteur4);
		
		LivreImprime livreImprime3 = new LivreImprime(7L, "1944-2019 : soixante-quinze ans de films de super-héros, 1ère édition", "Analyse de l'évolution des films de super-héros depuis 1944. Résumés et illustrations d'une cinquante de films représentatifs.", 3032, 3199, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-27508-207-3", "1944-2019 : soixante-quinze ans de films de super-héros", "A5 à couverture rigide", "1944_2019_soixante_quinze_ans_de_films_de_super_heros_GpArgo.png", 314, LocalDate.parse("2020-04-17"), genresLivreImp3, auteursLivreImp3, editeur3, 3, LocalDate.parse("2020-03-17"), null, 422.7, "g", 21.0, 14.8, 2.72, "cm");
		
		// Mock des appels à la DB pour obtenir les livres imprimés.
		when(livreImprimeRepository.findById(3L)).thenReturn(Optional.empty());
		when(livreImprimeRepository.findById(7L)).thenReturn(Optional.of(livreImprime3));
		
		// Test.
		assertThrows(IdInvalideException.class, () -> { articleServiceImpl.obtenirListeLivresImprimes(listeIdLivresImprimes); });
	}
	
	// Test de la méthode obtenirListeLivresNumeriques dans le cas standard.
	@Test
	void testObtenirListeLivresNumeriques() throws LectureBaseDonneesException, ListeVideException, IdInvalideException {
		
		// Liste des identifiants dont on veut obtenir les livres numériques.
		List<Long> listeIdLivresNumeriques = new ArrayList<Long>();
		listeIdLivresNumeriques.add(4L);
		listeIdLivresNumeriques.add(2L);
		listeIdLivresNumeriques.add(1L);
		
		// Livres numériques à retourner.
		// Adresses initiales.
		Adresse adresse1 = new Adresse(1L, "24 bis", "Rue D'Indochine", "35200", "Rennes", "France", "");
		Adresse adresse3 = new Adresse(3L, "374", "Rue du Maréchal Foch", "59160", "Lille", "France", "3e étage");
		
		// Editeurs sans les livres.
		Editeur editeur1 = new Editeur(1L, "Editions 7ème Art", adresse1, new ArrayList<Livre>());
		Editeur editeur4 = new Editeur(4L, "Historicae", adresse3, new ArrayList<Livre>());
				
		// Auteurs sans les livres.
		Auteur auteur1 = new Auteur(1L, "M", "Picard", "Bernard", new ArrayList<Livre>());
		Auteur auteur2 = new Auteur(2L, "M", "Shoeffer", "Edouard", new ArrayList<Livre>());
		Auteur auteur3 = new Auteur(3L, "M", "Kurkov", "Alexey", new ArrayList<Livre>());
		
		// Genres sans les livres.
		Genre genre1 = new Genre(1L, "Cinéma américain", new ArrayList<Livre>());
		Genre genre2 = new Genre(2L, "Biographie", new ArrayList<Livre>());
		Genre genre3 = new Genre(3L, "Livre d'exposition", new ArrayList<Livre>());
		Genre genre4 = new Genre(4L, "Historique", new ArrayList<Livre>());
		Genre genre7 = new Genre(7L, "Documentaire", new ArrayList<Livre>());
		
		// Livres numériques sans les consultations ni les lignes de commandes.
		ArrayList<Genre> genresLivreNum1 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreNum2 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreNum4 = new ArrayList<Genre>();
		
		genresLivreNum1.add(genre1);
		genresLivreNum1.add(genre2);
		genresLivreNum2.add(genre3);
		genresLivreNum2.add(genre4);
		genresLivreNum4.add(genre4);
		genresLivreNum4.add(genre7);
		
		ArrayList<Auteur> auteursLivreNum1 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreNum2 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreNum4 = new ArrayList<Auteur>();
				
		auteursLivreNum1.add(auteur1);
		auteursLivreNum2.add(auteur2);
		auteursLivreNum4.add(auteur3);
		
		LivreNumerique livreNumerique1 = new LivreNumerique(1L, "Archives du musée du cinéma de Los Angeles : Charlie Chaplin", "Recueil de photographies, de témoignages et de notes, autour du parcours de Charlie Chaplin en tant qu'acteur et réalisateur.", 1895, 1999, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-713-5", "Charlie Chaplin", ".pdf", "Charlie_Chaplin_Ed7emeArt.png", 98, LocalDate.parse("2014-10-18"), genresLivreNum1, auteursLivreNum1, editeur1, 275.4, "Mo");
		LivreNumerique livreNumerique2 = new LivreNumerique(2L, "Musée des Arts et Métiers, Livre d'exposition : Les premiers pas du cinéma", "Livre de l'exposition temporaire début 2015 du musée des Arts et Métiers. Sujet centré sur le matériel et les techniques de tournage et de projection au début du cinéma à 1920", 1421, 1499, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-804-2", "Les premiers pas du cinéma", ".pdf", "Les_premiers_pas_du_cinema_Ed7emeArt.png", 74, LocalDate.parse("2015-01-24"), genresLivreNum2, auteursLivreNum2, editeur1, 115.8, "Mo");
		LivreNumerique livreNumerique4 = new LivreNumerique(4L, "Archives publiques, 1914-1918 : reportages de guerre", "Recueil d'extraits de pellicules tournés sur les théâtres d'opérations de la première guerre modiale avec notes associées et commentaires.", 2843, 2999, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-32724-014-8", "1914-1918 : reportages de guerre", ".pdf", "1914_1918_reportages_de_guerre_Historicae.png", 254, LocalDate.parse("2008-11-11"), genresLivreNum4, auteursLivreNum4, editeur4, 872.5, "Mo");
		
		// Liste résultat attendue.
		List<LivreNumerique> livresNumeriques = new ArrayList<LivreNumerique>();
		livresNumeriques.add(livreNumerique4);
		livresNumeriques.add(livreNumerique2);
		livresNumeriques.add(livreNumerique1);
		
		// Mock des appels à la DB pour obtenir les livres imprimés.
		when(livreNumeriqueRepository.findById(1L)).thenReturn(Optional.of(livreNumerique1));
		when(livreNumeriqueRepository.findById(2L)).thenReturn(Optional.of(livreNumerique2));
		when(livreNumeriqueRepository.findById(4L)).thenReturn(Optional.of(livreNumerique4));
		
		// Test.
		assertEquals(livresNumeriques, articleServiceImpl.obtenirListeLivresNumeriques(listeIdLivresNumeriques));
	}
	
	// Test de la méthode obtenirListeLivresNumeriques avec une liste d'identifiants vide.
	@Test
	void testObtenirListeLivresNumeriquesExceptionListeVide() {
		
		// Liste d'identifiants vide.
		List<Long> listeIdLivresNumeriques = new ArrayList<Long>();
		
		// Test.
		assertThrows(ListeVideException.class, () -> { articleServiceImpl.obtenirListeLivresNumeriques(listeIdLivresNumeriques); });
	}
	
	// Test de la méthode obtenirListeLivresNumeriques avec un id invalide.
	@Test
	void testObtenirListeLivresNumeriquesExceptionIdInvalide() {
		
		// Liste des identifiants dont on veut obtenir les livres numériques, avec un ID invalide.
		List<Long> listeIdLivresNumeriques = new ArrayList<Long>();
		listeIdLivresNumeriques.add(2L);
		listeIdLivresNumeriques.add(5L);
		
		// Livres numériques à retourner.
		// Adresses initiales.
		Adresse adresse1 = new Adresse(1L, "24 bis", "Rue D'Indochine", "35200", "Rennes", "France", "");
		
		// Editeurs sans les livres.
		Editeur editeur1 = new Editeur(1L, "Editions 7ème Art", adresse1, new ArrayList<Livre>());
				
		// Auteurs sans les livres.
		Auteur auteur2 = new Auteur(2L, "M", "Shoeffer", "Edouard", new ArrayList<Livre>());
		
		// Genres sans les livres.
		Genre genre3 = new Genre(3L, "Livre d'exposition", new ArrayList<Livre>());
		Genre genre4 = new Genre(4L, "Historique", new ArrayList<Livre>());
		
		// Livres numériques sans les consultations ni les lignes de commandes.
		ArrayList<Genre> genresLivreNum2 = new ArrayList<Genre>();
		
		genresLivreNum2.add(genre3);
		genresLivreNum2.add(genre4);
		
		ArrayList<Auteur> auteursLivreNum2 = new ArrayList<Auteur>();
				
		auteursLivreNum2.add(auteur2);
		
		LivreNumerique livreNumerique2 = new LivreNumerique(2L, "Musée des Arts et Métiers, Livre d'exposition : Les premiers pas du cinéma", "Livre de l'exposition temporaire début 2015 du musée des Arts et Métiers. Sujet centré sur le matériel et les techniques de tournage et de projection au début du cinéma à 1920", 1421, 1499, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-804-2", "Les premiers pas du cinéma", ".pdf", "Les_premiers_pas_du_cinema_Ed7emeArt.png", 74, LocalDate.parse("2015-01-24"), genresLivreNum2, auteursLivreNum2, editeur1, 115.8, "Mo");
		
		// Mock des appels à la DB pour obtenir les livres numériques.
		when(livreNumeriqueRepository.findById(2L)).thenReturn(Optional.of(livreNumerique2));
		when(livreNumeriqueRepository.findById(5L)).thenReturn(Optional.empty());
		
		// Test.
		assertThrows(IdInvalideException.class, () -> { articleServiceImpl.obtenirListeLivresNumeriques(listeIdLivresNumeriques); });
	}
	
	// Test de la méthode obtenirListeLivresParAuteurOuTitre dans le cas standard.
	@Test
	void testObtenirListeLivresParAuteurOuTitre() throws LectureBaseDonneesException, ListeVideException, MauvaisStringException {
		
		// Chaîne de caractère recherchée.
		String auteurOuTitre = "Charlie Chaplin";
		
		// L'ensemble des livres retournés par le getAll.
		// Adresses initiales.
		Adresse adresse1 = new Adresse(1L, "24 bis", "Rue D'Indochine", "35200", "Rennes", "France", "");
		Adresse adresse2 = new Adresse(2L, "18", "Avenue des Frères Lumière", "69003", "Lyon", "France", "");
		Adresse adresse3 = new Adresse(3L, "374", "Rue du Maréchal Foch", "59160", "Lille", "France", "3e étage");
		
		// Editeurs sans les livres.
		Editeur editeur1 = new Editeur(1L, "Editions 7ème Art", adresse1, new ArrayList<Livre>());
		Editeur editeur2 = new Editeur(2L, "Editions Philosophie et Arts", adresse2, new ArrayList<Livre>());
		Editeur editeur3 = new Editeur(3L, "Groupe Argonaute", adresse2, new ArrayList<Livre>());
		Editeur editeur4 = new Editeur(4L, "Historicae", adresse3, new ArrayList<Livre>());
				
		// Auteurs sans les livres.
		Auteur auteur1 = new Auteur(1L, "M", "Picard", "Bernard", new ArrayList<Livre>());
		Auteur auteur2 = new Auteur(2L, "M", "Shoeffer", "Edouard", new ArrayList<Livre>());
		Auteur auteur3 = new Auteur(3L, "M", "Kurkov", "Alexey", new ArrayList<Livre>());
		Auteur auteur4 = new Auteur(4L, "M", "Ryan", "Lyrol", new ArrayList<Livre>());
		Auteur auteur5 = new Auteur(5L, "M", "Labbe", "Antoine", new ArrayList<Livre>());
		Auteur auteur6 = new Auteur(6L, "Mme", "Sancta", "Maria", new ArrayList<Livre>());
		
		// Genres sans les livres.
		Genre genre1 = new Genre(1L, "Cinéma américain", new ArrayList<Livre>());
		Genre genre2 = new Genre(2L, "Biographie", new ArrayList<Livre>());
		Genre genre3 = new Genre(3L, "Livre d'exposition", new ArrayList<Livre>());
		Genre genre4 = new Genre(4L, "Historique", new ArrayList<Livre>());
		Genre genre5 = new Genre(5L, "Cinéma asiatique", new ArrayList<Livre>());
		Genre genre6 = new Genre(6L, "Analyse", new ArrayList<Livre>());
		Genre genre7 = new Genre(7L, "Documentaire", new ArrayList<Livre>());
		
		// Livres numériques sans les consultations ni les lignes de commandes.
		ArrayList<Genre> genresLivreNum1 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreNum2 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreNum3 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreNum4 = new ArrayList<Genre>();
		
		genresLivreNum1.add(genre1);
		genresLivreNum1.add(genre2);
		genresLivreNum2.add(genre3);
		genresLivreNum2.add(genre4);
		genresLivreNum3.add(genre5);
		genresLivreNum3.add(genre6);
		genresLivreNum4.add(genre4);
		genresLivreNum4.add(genre7);
		
		ArrayList<Auteur> auteursLivreNum1 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreNum2 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreNum3 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreNum4 = new ArrayList<Auteur>();
				
		auteursLivreNum1.add(auteur1);
		auteursLivreNum2.add(auteur2);
		auteursLivreNum3.add(auteur3);
		auteursLivreNum4.add(auteur3);
		
		LivreNumerique livreNumerique1 = new LivreNumerique(1L, "Archives du musée du cinéma de Los Angeles : Charlie Chaplin", "Recueil de photographies, de témoignages et de notes, autour du parcours de Charlie Chaplin en tant qu'acteur et réalisateur.", 1895, 1999, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-713-5", "Charlie Chaplin", ".pdf", "Charlie_Chaplin_Ed7emeArt.png", 98, LocalDate.parse("2014-10-18"), genresLivreNum1, auteursLivreNum1, editeur1, 275.4, "Mo");
		LivreNumerique livreNumerique2 = new LivreNumerique(2L, "Musée des Arts et Métiers, Livre d'exposition : Les premiers pas du cinéma", "Livre de l'exposition temporaire début 2015 du musée des Arts et Métiers. Sujet centré sur le matériel et les techniques de tournage et de projection au début du cinéma à 1920", 1421, 1499, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-804-2", "Les premiers pas du cinéma", ".pdf", "Les_premiers_pas_du_cinema_Ed7emeArt.png", 74, LocalDate.parse("2015-01-24"), genresLivreNum2, auteursLivreNum2, editeur1, 115.8, "Mo");
		LivreNumerique livreNumerique3 = new LivreNumerique(3L, "Bollywood, simple copie de l'Occident ? Avec préface de l'auteur", "Analyse complète du cinéma bollywoodien contemporain et comparaison avec le cinéma américain et européen.", 2179, 2299, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-17172-074-0", "Bollywood, simple copie de l'Occident ?", ".pdf", "Bollywood_simple_copie_de_l_Occident_EdPhiloEtArt.png", 388, LocalDate.parse("2019-06-04"), genresLivreNum3, auteursLivreNum3, editeur2, 44.2, "Mo");
		LivreNumerique livreNumerique4 = new LivreNumerique(4L, "Archives publiques, 1914-1918 : reportages de guerre", "Recueil d'extraits de pellicules tournés sur les théâtres d'opérations de la première guerre modiale avec notes associées et commentaires.", 2843, 2999, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-32724-014-8", "1914-1918 : reportages de guerre", ".pdf", "1914_1918_reportages_de_guerre_Historicae.png", 254, LocalDate.parse("2008-11-11"), genresLivreNum4, auteursLivreNum4, editeur4, 872.5, "Mo");
		
		// Livres imprimés sans les consultations ni les lignes de commandes.
		ArrayList<Genre> genresLivreImp1 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreImp2 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreImp3 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreImp4 = new ArrayList<Genre>();
		
		genresLivreImp1.add(genre1);
		genresLivreImp1.add(genre7);
		genresLivreImp2.add(genre1);
		genresLivreImp2.add(genre2);
		genresLivreImp2.add(genre7);
		genresLivreImp3.add(genre6);
		genresLivreImp4.add(genre4);
		genresLivreImp4.add(genre7);
		
		ArrayList<Auteur> auteursLivreImp1 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreImp2 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreImp3 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreImp4 = new ArrayList<Auteur>();
		
		auteursLivreImp1.add(auteur1);
		auteursLivreImp1.add(auteur6);
		auteursLivreImp2.add(auteur1);
		auteursLivreImp2.add(auteur2);
		auteursLivreImp3.add(auteur4);
		auteursLivreImp4.add(auteur5);
		auteursLivreImp4.add(auteur6);
		
		LivreImprime livreImprime1 = new LivreImprime(5L, "Dans les coulises du tournage de Mission Impossible, 2e édition", "Recueil de photographies et de témoignages autour des différents tournages de la série de films Mission Impossible.", 3222, 3399, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-954-7", "Dans les coulises du tournage de Mission Impossible", "Grand carré à couverture rigide", "Dans_les_coulises_du_tournage_de_Mission Impossible_Ed7emeArt.png", 186, LocalDate.parse("2020-08-05"), genresLivreImp1, auteursLivreImp1, editeur1, 4, LocalDate.parse("2020-08-02"), LocalDate.parse("2020-12-28"), 359.4, "g", 21.0, 21.0, 1.63, "cm");
		LivreImprime livreImprime2 = new LivreImprime(6L, "Georges Lucas et Star Wars, derrière la saga, édition de luxe", "Bibliographie illustrée de Georges Lucas avec une interview autour de son travail autour de Star Wars.", 3791, 3999, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-27508-154-7", "Georges Lucas et Star Wars, derrière la saga", "A4 à couverture rigide", "Georges_Lucas_et_Star_Wars_derriere_la_saga_GpArgo.png", 238, LocalDate.parse("2016-11-28"), genresLivreImp2, auteursLivreImp2, editeur3, 1, LocalDate.parse("2016-11-13"), LocalDate.parse("2018-04-30"), 645.5, "g", 29.7, 21.0, 2.07, "cm");
		LivreImprime livreImprime3 = new LivreImprime(7L, "1944-2019 : soixante-quinze ans de films de super-héros, 1ère édition", "Analyse de l'évolution des films de super-héros depuis 1944. Résumés et illustrations d'une cinquante de films représentatifs.", 3032, 3199, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-27508-207-3", "1944-2019 : soixante-quinze ans de films de super-héros", "A5 à couverture rigide", "1944_2019_soixante_quinze_ans_de_films_de_super_heros_GpArgo.png", 314, LocalDate.parse("2020-04-17"), genresLivreImp3, auteursLivreImp3, editeur3, 3, LocalDate.parse("2020-03-17"), null, 422.7, "g", 21.0, 14.8, 2.72, "cm");
		LivreImprime livreImprime4 = new LivreImprime(8L, "Films de guerre : raconter l'histoire des soldats, 4e édition", "Analyse de films de guerre de différentes époques et pays avec un regard particulier sur les personnages de soldat.", 2369, 2499, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-32724-089-9", "Films de guerre : raconter l'histoire des soldats", "Poche à dos carré collé", "Films_de_guerre_raconter_l_histoire_des_soldats_Historicae.png", 360, LocalDate.parse("2017-06-18"), genresLivreImp4, auteursLivreImp4, editeur4, 5, LocalDate.parse("2017-06-15"), LocalDate.parse("2019-02-14"), 290.8, "g", 17.0, 11.0, 3.11, "cm");
		
		List<Livre> livres = new ArrayList<Livre>();
		
		livres.add(livreImprime1);
		livres.add(livreImprime2);
		livres.add(livreImprime3);
		livres.add(livreImprime4);
		livres.add(livreNumerique1);
		livres.add(livreNumerique2);
		livres.add(livreNumerique3);
		livres.add(livreNumerique4);
		
		// Mock de l'appel à la DB pour retourner tous les livres.
		when(livreRepository.findAll()).thenReturn(livres);
		
		// Résultat attendu.
		List<IdentifiantEtTypeArticle> livresCorrespondants = new ArrayList<IdentifiantEtTypeArticle>();
		livresCorrespondants.add(new IdentifiantEtTypeArticle(1L, "LivreNumerique"));
		
		// Test.
		assertEquals(livresCorrespondants, articleServiceImpl.obtenirListeLivresParAuteurOuTitre(auteurOuTitre));
	}
	
	// Test de la méthode obtenirListeLivresParAuteurOuTitre avec une chaîne vide.
	@Test
	void testObtenirListeLivresParAuteurOuTitreExceptionMauvaisString() {
		// Chaîne de caractère vide.
		String auteurOuTitre = "";
		
		// L'ensemble des livres retournés par le getAll.
		// Adresses initiales.
		Adresse adresse1 = new Adresse(1L, "24 bis", "Rue D'Indochine", "35200", "Rennes", "France", "");
		Adresse adresse2 = new Adresse(2L, "18", "Avenue des Frères Lumière", "69003", "Lyon", "France", "");
		Adresse adresse3 = new Adresse(3L, "374", "Rue du Maréchal Foch", "59160", "Lille", "France", "3e étage");
		
		// Editeurs sans les livres.
		Editeur editeur1 = new Editeur(1L, "Editions 7ème Art", adresse1, new ArrayList<Livre>());
		Editeur editeur2 = new Editeur(2L, "Editions Philosophie et Arts", adresse2, new ArrayList<Livre>());
		Editeur editeur3 = new Editeur(3L, "Groupe Argonaute", adresse2, new ArrayList<Livre>());
		Editeur editeur4 = new Editeur(4L, "Historicae", adresse3, new ArrayList<Livre>());
				
		// Auteurs sans les livres.
		Auteur auteur1 = new Auteur(1L, "M", "Picard", "Bernard", new ArrayList<Livre>());
		Auteur auteur2 = new Auteur(2L, "M", "Shoeffer", "Edouard", new ArrayList<Livre>());
		Auteur auteur3 = new Auteur(3L, "M", "Kurkov", "Alexey", new ArrayList<Livre>());
		Auteur auteur4 = new Auteur(4L, "M", "Ryan", "Lyrol", new ArrayList<Livre>());
		Auteur auteur5 = new Auteur(5L, "M", "Labbe", "Antoine", new ArrayList<Livre>());
		Auteur auteur6 = new Auteur(6L, "Mme", "Sancta", "Maria", new ArrayList<Livre>());
		
		// Genres sans les livres.
		Genre genre1 = new Genre(1L, "Cinéma américain", new ArrayList<Livre>());
		Genre genre2 = new Genre(2L, "Biographie", new ArrayList<Livre>());
		Genre genre3 = new Genre(3L, "Livre d'exposition", new ArrayList<Livre>());
		Genre genre4 = new Genre(4L, "Historique", new ArrayList<Livre>());
		Genre genre5 = new Genre(5L, "Cinéma asiatique", new ArrayList<Livre>());
		Genre genre6 = new Genre(6L, "Analyse", new ArrayList<Livre>());
		Genre genre7 = new Genre(7L, "Documentaire", new ArrayList<Livre>());
		
		// Livres numériques sans les consultations ni les lignes de commandes.
		ArrayList<Genre> genresLivreNum1 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreNum2 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreNum3 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreNum4 = new ArrayList<Genre>();
		
		genresLivreNum1.add(genre1);
		genresLivreNum1.add(genre2);
		genresLivreNum2.add(genre3);
		genresLivreNum2.add(genre4);
		genresLivreNum3.add(genre5);
		genresLivreNum3.add(genre6);
		genresLivreNum4.add(genre4);
		genresLivreNum4.add(genre7);
		
		ArrayList<Auteur> auteursLivreNum1 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreNum2 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreNum3 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreNum4 = new ArrayList<Auteur>();
				
		auteursLivreNum1.add(auteur1);
		auteursLivreNum2.add(auteur2);
		auteursLivreNum3.add(auteur3);
		auteursLivreNum4.add(auteur3);
		
		LivreNumerique livreNumerique1 = new LivreNumerique(1L, "Archives du musée du cinéma de Los Angeles : Charlie Chaplin", "Recueil de photographies, de témoignages et de notes, autour du parcours de Charlie Chaplin en tant qu'acteur et réalisateur.", 1895, 1999, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-713-5", "Charlie Chaplin", ".pdf", "Charlie_Chaplin_Ed7emeArt.png", 98, LocalDate.parse("2014-10-18"), genresLivreNum1, auteursLivreNum1, editeur1, 275.4, "Mo");
		LivreNumerique livreNumerique2 = new LivreNumerique(2L, "Musée des Arts et Métiers, Livre d'exposition : Les premiers pas du cinéma", "Livre de l'exposition temporaire début 2015 du musée des Arts et Métiers. Sujet centré sur le matériel et les techniques de tournage et de projection au début du cinéma à 1920", 1421, 1499, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-804-2", "Les premiers pas du cinéma", ".pdf", "Les_premiers_pas_du_cinema_Ed7emeArt.png", 74, LocalDate.parse("2015-01-24"), genresLivreNum2, auteursLivreNum2, editeur1, 115.8, "Mo");
		LivreNumerique livreNumerique3 = new LivreNumerique(3L, "Bollywood, simple copie de l'Occident ? Avec préface de l'auteur", "Analyse complète du cinéma bollywoodien contemporain et comparaison avec le cinéma américain et européen.", 2179, 2299, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-17172-074-0", "Bollywood, simple copie de l'Occident ?", ".pdf", "Bollywood_simple_copie_de_l_Occident_EdPhiloEtArt.png", 388, LocalDate.parse("2019-06-04"), genresLivreNum3, auteursLivreNum3, editeur2, 44.2, "Mo");
		LivreNumerique livreNumerique4 = new LivreNumerique(4L, "Archives publiques, 1914-1918 : reportages de guerre", "Recueil d'extraits de pellicules tournés sur les théâtres d'opérations de la première guerre modiale avec notes associées et commentaires.", 2843, 2999, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-32724-014-8", "1914-1918 : reportages de guerre", ".pdf", "1914_1918_reportages_de_guerre_Historicae.png", 254, LocalDate.parse("2008-11-11"), genresLivreNum4, auteursLivreNum4, editeur4, 872.5, "Mo");
		
		// Livres imprimés sans les consultations ni les lignes de commandes.
		ArrayList<Genre> genresLivreImp1 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreImp2 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreImp3 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreImp4 = new ArrayList<Genre>();
		
		genresLivreImp1.add(genre1);
		genresLivreImp1.add(genre7);
		genresLivreImp2.add(genre1);
		genresLivreImp2.add(genre2);
		genresLivreImp2.add(genre7);
		genresLivreImp3.add(genre6);
		genresLivreImp4.add(genre4);
		genresLivreImp4.add(genre7);
		
		ArrayList<Auteur> auteursLivreImp1 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreImp2 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreImp3 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreImp4 = new ArrayList<Auteur>();
		
		auteursLivreImp1.add(auteur1);
		auteursLivreImp1.add(auteur6);
		auteursLivreImp2.add(auteur1);
		auteursLivreImp2.add(auteur2);
		auteursLivreImp3.add(auteur4);
		auteursLivreImp4.add(auteur5);
		auteursLivreImp4.add(auteur6);
		
		LivreImprime livreImprime1 = new LivreImprime(5L, "Dans les coulises du tournage de Mission Impossible, 2e édition", "Recueil de photographies et de témoignages autour des différents tournages de la série de films Mission Impossible.", 3222, 3399, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-954-7", "Dans les coulises du tournage de Mission Impossible", "Grand carré à couverture rigide", "Dans_les_coulises_du_tournage_de_Mission Impossible_Ed7emeArt.png", 186, LocalDate.parse("2020-08-05"), genresLivreImp1, auteursLivreImp1, editeur1, 4, LocalDate.parse("2020-08-02"), LocalDate.parse("2020-12-28"), 359.4, "g", 21.0, 21.0, 1.63, "cm");
		LivreImprime livreImprime2 = new LivreImprime(6L, "Georges Lucas et Star Wars, derrière la saga, édition de luxe", "Bibliographie illustrée de Georges Lucas avec une interview autour de son travail autour de Star Wars.", 3791, 3999, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-27508-154-7", "Georges Lucas et Star Wars, derrière la saga", "A4 à couverture rigide", "Georges_Lucas_et_Star_Wars_derriere_la_saga_GpArgo.png", 238, LocalDate.parse("2016-11-28"), genresLivreImp2, auteursLivreImp2, editeur3, 1, LocalDate.parse("2016-11-13"), LocalDate.parse("2018-04-30"), 645.5, "g", 29.7, 21.0, 2.07, "cm");
		LivreImprime livreImprime3 = new LivreImprime(7L, "1944-2019 : soixante-quinze ans de films de super-héros, 1ère édition", "Analyse de l'évolution des films de super-héros depuis 1944. Résumés et illustrations d'une cinquante de films représentatifs.", 3032, 3199, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-27508-207-3", "1944-2019 : soixante-quinze ans de films de super-héros", "A5 à couverture rigide", "1944_2019_soixante_quinze_ans_de_films_de_super_heros_GpArgo.png", 314, LocalDate.parse("2020-04-17"), genresLivreImp3, auteursLivreImp3, editeur3, 3, LocalDate.parse("2020-03-17"), null, 422.7, "g", 21.0, 14.8, 2.72, "cm");
		LivreImprime livreImprime4 = new LivreImprime(8L, "Films de guerre : raconter l'histoire des soldats, 4e édition", "Analyse de films de guerre de différentes époques et pays avec un regard particulier sur les personnages de soldat.", 2369, 2499, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-32724-089-9", "Films de guerre : raconter l'histoire des soldats", "Poche à dos carré collé", "Films_de_guerre_raconter_l_histoire_des_soldats_Historicae.png", 360, LocalDate.parse("2017-06-18"), genresLivreImp4, auteursLivreImp4, editeur4, 5, LocalDate.parse("2017-06-15"), LocalDate.parse("2019-02-14"), 290.8, "g", 17.0, 11.0, 3.11, "cm");
		
		List<Livre> livres = new ArrayList<Livre>();
		
		livres.add(livreImprime1);
		livres.add(livreImprime2);
		livres.add(livreImprime3);
		livres.add(livreImprime4);
		livres.add(livreNumerique1);
		livres.add(livreNumerique2);
		livres.add(livreNumerique3);
		livres.add(livreNumerique4);
		
		// Mock de l'appel à la DB pour retourner tous les livres.
		when(livreRepository.findAll()).thenReturn(livres);
		
		// Test.
		assertThrows(MauvaisStringException.class, () -> { articleServiceImpl.obtenirListeLivresParAuteurOuTitre(auteurOuTitre); });
	}
	
	// Test de la méthode obtenirListeLivresParAuteurOuTitre avec un résultat vide.
	@Test
	void testObtenirListeLivresParAuteurOuTitreExceptionListeVide() {
		// Chaîne de caractère absurde.
		String auteurOuTitre = "Cette chaîne de caractère est tellement longue qu'aucun titre ou auteur ne devrait correspondre";
		
		// L'ensemble des livres retournés par le getAll.
		// Adresses initiales.
		Adresse adresse1 = new Adresse(1L, "24 bis", "Rue D'Indochine", "35200", "Rennes", "France", "");
		Adresse adresse2 = new Adresse(2L, "18", "Avenue des Frères Lumière", "69003", "Lyon", "France", "");
		Adresse adresse3 = new Adresse(3L, "374", "Rue du Maréchal Foch", "59160", "Lille", "France", "3e étage");
		
		// Editeurs sans les livres.
		Editeur editeur1 = new Editeur(1L, "Editions 7ème Art", adresse1, new ArrayList<Livre>());
		Editeur editeur2 = new Editeur(2L, "Editions Philosophie et Arts", adresse2, new ArrayList<Livre>());
		Editeur editeur3 = new Editeur(3L, "Groupe Argonaute", adresse2, new ArrayList<Livre>());
		Editeur editeur4 = new Editeur(4L, "Historicae", adresse3, new ArrayList<Livre>());
				
		// Auteurs sans les livres.
		Auteur auteur1 = new Auteur(1L, "M", "Picard", "Bernard", new ArrayList<Livre>());
		Auteur auteur2 = new Auteur(2L, "M", "Shoeffer", "Edouard", new ArrayList<Livre>());
		Auteur auteur3 = new Auteur(3L, "M", "Kurkov", "Alexey", new ArrayList<Livre>());
		Auteur auteur4 = new Auteur(4L, "M", "Ryan", "Lyrol", new ArrayList<Livre>());
		Auteur auteur5 = new Auteur(5L, "M", "Labbe", "Antoine", new ArrayList<Livre>());
		Auteur auteur6 = new Auteur(6L, "Mme", "Sancta", "Maria", new ArrayList<Livre>());
		
		// Genres sans les livres.
		Genre genre1 = new Genre(1L, "Cinéma américain", new ArrayList<Livre>());
		Genre genre2 = new Genre(2L, "Biographie", new ArrayList<Livre>());
		Genre genre3 = new Genre(3L, "Livre d'exposition", new ArrayList<Livre>());
		Genre genre4 = new Genre(4L, "Historique", new ArrayList<Livre>());
		Genre genre5 = new Genre(5L, "Cinéma asiatique", new ArrayList<Livre>());
		Genre genre6 = new Genre(6L, "Analyse", new ArrayList<Livre>());
		Genre genre7 = new Genre(7L, "Documentaire", new ArrayList<Livre>());
		
		// Livres numériques sans les consultations ni les lignes de commandes.
		ArrayList<Genre> genresLivreNum1 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreNum2 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreNum3 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreNum4 = new ArrayList<Genre>();
		
		genresLivreNum1.add(genre1);
		genresLivreNum1.add(genre2);
		genresLivreNum2.add(genre3);
		genresLivreNum2.add(genre4);
		genresLivreNum3.add(genre5);
		genresLivreNum3.add(genre6);
		genresLivreNum4.add(genre4);
		genresLivreNum4.add(genre7);
		
		ArrayList<Auteur> auteursLivreNum1 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreNum2 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreNum3 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreNum4 = new ArrayList<Auteur>();
				
		auteursLivreNum1.add(auteur1);
		auteursLivreNum2.add(auteur2);
		auteursLivreNum3.add(auteur3);
		auteursLivreNum4.add(auteur3);
		
		LivreNumerique livreNumerique1 = new LivreNumerique(1L, "Archives du musée du cinéma de Los Angeles : Charlie Chaplin", "Recueil de photographies, de témoignages et de notes, autour du parcours de Charlie Chaplin en tant qu'acteur et réalisateur.", 1895, 1999, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-713-5", "Charlie Chaplin", ".pdf", "Charlie_Chaplin_Ed7emeArt.png", 98, LocalDate.parse("2014-10-18"), genresLivreNum1, auteursLivreNum1, editeur1, 275.4, "Mo");
		LivreNumerique livreNumerique2 = new LivreNumerique(2L, "Musée des Arts et Métiers, Livre d'exposition : Les premiers pas du cinéma", "Livre de l'exposition temporaire début 2015 du musée des Arts et Métiers. Sujet centré sur le matériel et les techniques de tournage et de projection au début du cinéma à 1920", 1421, 1499, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-804-2", "Les premiers pas du cinéma", ".pdf", "Les_premiers_pas_du_cinema_Ed7emeArt.png", 74, LocalDate.parse("2015-01-24"), genresLivreNum2, auteursLivreNum2, editeur1, 115.8, "Mo");
		LivreNumerique livreNumerique3 = new LivreNumerique(3L, "Bollywood, simple copie de l'Occident ? Avec préface de l'auteur", "Analyse complète du cinéma bollywoodien contemporain et comparaison avec le cinéma américain et européen.", 2179, 2299, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-17172-074-0", "Bollywood, simple copie de l'Occident ?", ".pdf", "Bollywood_simple_copie_de_l_Occident_EdPhiloEtArt.png", 388, LocalDate.parse("2019-06-04"), genresLivreNum3, auteursLivreNum3, editeur2, 44.2, "Mo");
		LivreNumerique livreNumerique4 = new LivreNumerique(4L, "Archives publiques, 1914-1918 : reportages de guerre", "Recueil d'extraits de pellicules tournés sur les théâtres d'opérations de la première guerre modiale avec notes associées et commentaires.", 2843, 2999, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-32724-014-8", "1914-1918 : reportages de guerre", ".pdf", "1914_1918_reportages_de_guerre_Historicae.png", 254, LocalDate.parse("2008-11-11"), genresLivreNum4, auteursLivreNum4, editeur4, 872.5, "Mo");
		
		// Livres imprimés sans les consultations ni les lignes de commandes.
		ArrayList<Genre> genresLivreImp1 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreImp2 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreImp3 = new ArrayList<Genre>();
		ArrayList<Genre> genresLivreImp4 = new ArrayList<Genre>();
		
		genresLivreImp1.add(genre1);
		genresLivreImp1.add(genre7);
		genresLivreImp2.add(genre1);
		genresLivreImp2.add(genre2);
		genresLivreImp2.add(genre7);
		genresLivreImp3.add(genre6);
		genresLivreImp4.add(genre4);
		genresLivreImp4.add(genre7);
		
		ArrayList<Auteur> auteursLivreImp1 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreImp2 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreImp3 = new ArrayList<Auteur>();
		ArrayList<Auteur> auteursLivreImp4 = new ArrayList<Auteur>();
		
		auteursLivreImp1.add(auteur1);
		auteursLivreImp1.add(auteur6);
		auteursLivreImp2.add(auteur1);
		auteursLivreImp2.add(auteur2);
		auteursLivreImp3.add(auteur4);
		auteursLivreImp4.add(auteur5);
		auteursLivreImp4.add(auteur6);
		
		LivreImprime livreImprime1 = new LivreImprime(5L, "Dans les coulises du tournage de Mission Impossible, 2e édition", "Recueil de photographies et de témoignages autour des différents tournages de la série de films Mission Impossible.", 3222, 3399, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-954-7", "Dans les coulises du tournage de Mission Impossible", "Grand carré à couverture rigide", "Dans_les_coulises_du_tournage_de_Mission Impossible_Ed7emeArt.png", 186, LocalDate.parse("2020-08-05"), genresLivreImp1, auteursLivreImp1, editeur1, 4, LocalDate.parse("2020-08-02"), LocalDate.parse("2020-12-28"), 359.4, "g", 21.0, 21.0, 1.63, "cm");
		LivreImprime livreImprime2 = new LivreImprime(6L, "Georges Lucas et Star Wars, derrière la saga, édition de luxe", "Bibliographie illustrée de Georges Lucas avec une interview autour de son travail autour de Star Wars.", 3791, 3999, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-27508-154-7", "Georges Lucas et Star Wars, derrière la saga", "A4 à couverture rigide", "Georges_Lucas_et_Star_Wars_derriere_la_saga_GpArgo.png", 238, LocalDate.parse("2016-11-28"), genresLivreImp2, auteursLivreImp2, editeur3, 1, LocalDate.parse("2016-11-13"), LocalDate.parse("2018-04-30"), 645.5, "g", 29.7, 21.0, 2.07, "cm");
		LivreImprime livreImprime3 = new LivreImprime(7L, "1944-2019 : soixante-quinze ans de films de super-héros, 1ère édition", "Analyse de l'évolution des films de super-héros depuis 1944. Résumés et illustrations d'une cinquante de films représentatifs.", 3032, 3199, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-27508-207-3", "1944-2019 : soixante-quinze ans de films de super-héros", "A5 à couverture rigide", "1944_2019_soixante_quinze_ans_de_films_de_super_heros_GpArgo.png", 314, LocalDate.parse("2020-04-17"), genresLivreImp3, auteursLivreImp3, editeur3, 3, LocalDate.parse("2020-03-17"), null, 422.7, "g", 21.0, 14.8, 2.72, "cm");
		LivreImprime livreImprime4 = new LivreImprime(8L, "Films de guerre : raconter l'histoire des soldats, 4e édition", "Analyse de films de guerre de différentes époques et pays avec un regard particulier sur les personnages de soldat.", 2369, 2499, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-32724-089-9", "Films de guerre : raconter l'histoire des soldats", "Poche à dos carré collé", "Films_de_guerre_raconter_l_histoire_des_soldats_Historicae.png", 360, LocalDate.parse("2017-06-18"), genresLivreImp4, auteursLivreImp4, editeur4, 5, LocalDate.parse("2017-06-15"), LocalDate.parse("2019-02-14"), 290.8, "g", 17.0, 11.0, 3.11, "cm");
		
		List<Livre> livres = new ArrayList<Livre>();
		
		livres.add(livreImprime1);
		livres.add(livreImprime2);
		livres.add(livreImprime3);
		livres.add(livreImprime4);
		livres.add(livreNumerique1);
		livres.add(livreNumerique2);
		livres.add(livreNumerique3);
		livres.add(livreNumerique4);
		
		// Mock de l'appel à la DB pour retourner tous les livres.
		when(livreRepository.findAll()).thenReturn(livres);
		
		// Test.
		assertThrows(ListeVideException.class, () -> { articleServiceImpl.obtenirListeLivresParAuteurOuTitre(auteurOuTitre); });
	}
	
	// Test de la méthode ajoutConsultationAnonyme dans le cas standard.
	@Test
	void testAjoutConsultationAnonyme() throws EcritureBaseDonneesException, ConsultationNonAnonymeException {
		
		// Consultation anonyme.
		// Adresses initiales.
		Adresse adresse1 = new Adresse(1L, "24 bis", "Rue D'Indochine", "35200", "Rennes", "France", "");
					
		// Editeurs sans les livres.
		Editeur editeur1 = new Editeur(1L, "Editions 7ème Art", adresse1, new ArrayList<Livre>());
		
		// Auteurs sans les livres.
		Auteur auteur1 = new Auteur(5L, "M", "Picard", "Bernard", new ArrayList<Livre>());
		Auteur auteur6 = new Auteur(10L, "Mme", "Sancta", "Maria", new ArrayList<Livre>());
		
		// Genres sans les livres.
		Genre genre1 = new Genre(1L, "Cinéma américain", new ArrayList<Livre>());
		Genre genre7 = new Genre(7L, "Documentaire", new ArrayList<Livre>());
		
		// Livres imprimés sans les consultations ni les lignes de commandes.
		ArrayList<Genre> genresLivreImp1 = new ArrayList<Genre>();
		
		genresLivreImp1.add(genre1);
		genresLivreImp1.add(genre7);
		
		ArrayList<Auteur> auteursLivreImp1 = new ArrayList<Auteur>();
		
		auteursLivreImp1.add(auteur1);
		auteursLivreImp1.add(auteur6);
		
		LivreImprime livreImprime1 = new LivreImprime(5L, "Dans les coulises du tournage de Mission Impossible, 2e édition", "Recueil de photographies et de témoignages autour des différents tournages de la série de films Mission Impossible.", 3222, 3399, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-954-7", "Dans les coulises du tournage de Mission Impossible", "Grand carré à couverture rigide", "Dans_les_coulises_du_tournage_de_Mission Impossible_Ed7emeArt.png", 186, LocalDate.parse("2020-08-05"), genresLivreImp1, auteursLivreImp1, editeur1, 4, LocalDate.parse("2020-08-02"), LocalDate.parse("2020-12-28"), 359.4, "g", 21.0, 21.0, 1.63, "cm");
		
		Consultation consultationAnonyme = new Consultation(LocalDateTime.of(2021, 10, 25, 16, 35, 14, 38570036), null, livreImprime1); 
		
		// Résultat attendu.
		Consultation nouvelleConsultation = new Consultation(9L, consultationAnonyme.getDateEnregistrement(), consultationAnonyme.getClient(), consultationAnonyme.getArticle());
		
		// Mock de l'appel à la DB pour enregistrer la consultation anonyme.
		when(consultationRepository.save(consultationAnonyme)).thenReturn(nouvelleConsultation);
		
		// Test.
		assertEquals(nouvelleConsultation, articleServiceImpl.ajoutConsultationAnonyme(consultationAnonyme));
	}
	
	// Test de la méthode ajoutConsultationAnonyme avec une consultation non anonyme.
	@Test
	void testAjoutConsultationAnonymeExceptionConsultationNonAnonyme() {
		
		// Consultation non anonyme.
		// Adresses initiales.
		Adresse adresse1 = new Adresse(1L, "24 bis", "Rue D'Indochine", "35200", "Rennes", "France", "");
		Adresse adresse4 = new Adresse(4L, "14", "Stirling Street", "EH10", "Edinburgh", "Ecosse", "");
					
		// Editeurs sans les livres.
		Editeur editeur1 = new Editeur(1L, "Editions 7ème Art", adresse1, new ArrayList<Livre>());
		
		// Auteurs sans les livres.
		Auteur auteur1 = new Auteur(5L, "M", "Picard", "Bernard", new ArrayList<Livre>());
		Auteur auteur6 = new Auteur(10L, "Mme", "Sancta", "Maria", new ArrayList<Livre>());
		
		// Clients sans les consultations ni les commandes.
		Client client1 = new Client(1L, "M", "Finan","Didier","didier.finan@gmail.com","SpyFilmsAreTheBest","1565-4961-1787-1857","08/22","975", adresse4,adresse4, new ArrayList<Consultation>(), new ArrayList<Commande>());
		
		// Genres sans les livres.
		Genre genre1 = new Genre(1L, "Cinéma américain", new ArrayList<Livre>());
		Genre genre7 = new Genre(7L, "Documentaire", new ArrayList<Livre>());
		
		// Livres imprimés sans les consultations ni les lignes de commandes.
		ArrayList<Genre> genresLivreImp1 = new ArrayList<Genre>();
		
		genresLivreImp1.add(genre1);
		genresLivreImp1.add(genre7);
		
		ArrayList<Auteur> auteursLivreImp1 = new ArrayList<Auteur>();
		
		auteursLivreImp1.add(auteur1);
		auteursLivreImp1.add(auteur6);
		
		LivreImprime livreImprime1 = new LivreImprime(5L, "Dans les coulises du tournage de Mission Impossible, 2e édition", "Recueil de photographies et de témoignages autour des différents tournages de la série de films Mission Impossible.", 3222, 3399, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-954-7", "Dans les coulises du tournage de Mission Impossible", "Grand carré à couverture rigide", "Dans_les_coulises_du_tournage_de_Mission Impossible_Ed7emeArt.png", 186, LocalDate.parse("2020-08-05"), genresLivreImp1, auteursLivreImp1, editeur1, 4, LocalDate.parse("2020-08-02"), LocalDate.parse("2020-12-28"), 359.4, "g", 21.0, 21.0, 1.63, "cm");
		
		List<Consultation> consultationsClient1 = new ArrayList<Consultation>();
		
		Consultation consultationAnonyme = new Consultation(LocalDateTime.of(2021, 10, 25, 16, 35, 14, 38570036), client1, livreImprime1); 

		consultationsClient1.add(consultationAnonyme);
		
		client1.setConsultations(consultationsClient1);
		
		// Mock de l'appel à la DB pour enregistrer la consultation anonyme.
		Consultation nouvelleConsultation = new Consultation(9L, consultationAnonyme.getDateEnregistrement(), consultationAnonyme.getClient(), consultationAnonyme.getArticle());
		
		when(consultationRepository.save(consultationAnonyme)).thenReturn(nouvelleConsultation);
		
		// Test.
		assertThrows(ConsultationNonAnonymeException.class, () -> { articleServiceImpl.ajoutConsultationAnonyme(consultationAnonyme); });
	}
	
	// Test de la méthode ajoutConsultationClient dans le cas standard.
	@Test
	void testAjoutConsultationClient() throws LectureBaseDonneesException, EcritureBaseDonneesException, ClientInconnuException {
		
		// Consultation avec id client.
		// Adresses initiales.
		Adresse adresse1 = new Adresse(1L, "24 bis", "Rue D'Indochine", "35200", "Rennes", "France", "");
		Adresse adresse4 = new Adresse(4L, "14", "Stirling Street", "EH10", "Edinburgh", "Ecosse", "");
					
		// Editeurs sans les livres.
		Editeur editeur1 = new Editeur(1L, "Editions 7ème Art", adresse1, new ArrayList<Livre>());
		
		// Auteurs sans les livres.
		Auteur auteur1 = new Auteur(5L, "M", "Picard", "Bernard", new ArrayList<Livre>());
		Auteur auteur6 = new Auteur(10L, "Mme", "Sancta", "Maria", new ArrayList<Livre>());
		
		// Clients sans les consultations ni les commandes.
		Client client1 = new Client(1L, "M", "Finan","Didier","didier.finan@gmail.com","SpyFilmsAreTheBest","1565-4961-1787-1857","08/22","975", adresse4,adresse4, new ArrayList<Consultation>(), new ArrayList<Commande>());
		
		// Genres sans les livres.
		Genre genre1 = new Genre(1L, "Cinéma américain", new ArrayList<Livre>());
		Genre genre7 = new Genre(7L, "Documentaire", new ArrayList<Livre>());
		
		// Livres imprimés sans les consultations ni les lignes de commandes.
		ArrayList<Genre> genresLivreImp1 = new ArrayList<Genre>();
		
		genresLivreImp1.add(genre1);
		genresLivreImp1.add(genre7);
		
		ArrayList<Auteur> auteursLivreImp1 = new ArrayList<Auteur>();
		
		auteursLivreImp1.add(auteur1);
		auteursLivreImp1.add(auteur6);
		
		LivreImprime livreImprime1 = new LivreImprime(5L, "Dans les coulises du tournage de Mission Impossible, 2e édition", "Recueil de photographies et de témoignages autour des différents tournages de la série de films Mission Impossible.", 3222, 3399, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-954-7", "Dans les coulises du tournage de Mission Impossible", "Grand carré à couverture rigide", "Dans_les_coulises_du_tournage_de_Mission Impossible_Ed7emeArt.png", 186, LocalDate.parse("2020-08-05"), genresLivreImp1, auteursLivreImp1, editeur1, 4, LocalDate.parse("2020-08-02"), LocalDate.parse("2020-12-28"), 359.4, "g", 21.0, 21.0, 1.63, "cm");
		
		List<Consultation> consultationsClient1 = new ArrayList<Consultation>();
		
		Consultation consultationClient = new Consultation(LocalDateTime.of(2021, 10, 25, 16, 35, 14, 38570036), client1, livreImprime1); 

		consultationsClient1.add(consultationClient);
		
		client1.setConsultations(consultationsClient1);
		
		ConsultationAvecIdClient consultationAvecIdClient = new ConsultationAvecIdClient(consultationClient, 1L);
		
		// Résultat attendu.
		Consultation nouvelleConsultation = new Consultation(9L, consultationClient.getDateEnregistrement(), consultationClient.getClient(), consultationClient.getArticle());
		ConsultationAvecIdClient nouvelleConsultationAvecIdClient = new ConsultationAvecIdClient(nouvelleConsultation, 1L);
		
		// Mock de l'appel à la DB pour enregistrer la consultation anonyme.
		when(consultationRepository.save(consultationClient)).thenReturn(nouvelleConsultation);

		// Test.
		assertEquals(nouvelleConsultationAvecIdClient, articleServiceImpl.ajoutConsultationClient(consultationAvecIdClient));
	}
	
	// Test de la méthode ajoutConsultationClient avec un identifiant client inconnu.
	@Test
	void testAjoutConsultationClientExceptionClientInconnu() {
		
	}
	
	// Test de la méthode ajoutClientAConsultation dans le cas standard.
	@Test
	void testAjoutClientAConsultation() {
		
	}
	
	// Test de la méthode ajoutClientAConsultation avec une consultation non anonyme.
	@Test
	void testAjoutClientAConsultationExceptionConsultationNonAnonyme() {
		
	}
	
	// Test de la méthode ajoutClientAConsultation avec un identifiant client inconnu.
	@Test
	void testAjoutClientAConsultationExceptionClientInconnu() {
		
	}
	
	// Test de la méthode ajoutClientAConsultation avec une consultation inconnue.
	@Test
	void testAjoutClientAConsultationExceptionConsultationInconnue() {
		
	}
	
	// Test de la méthode consulterDisponibiliteLivresImprimes dans le cas standard.
	@Test
	void testConsulterDisponibiliteLivresImprimes() {
		
	}
	
	// Test de la méthode consulterDisponibiliteLivresImprimes avec une liste d'identifiants vide.
	@Test
	void testConsulterDisponibiliteLivresImprimesExceptionListeVide() {
		
	}
	
	// Test de la méthode consulterDisponibiliteLivresImprimes avec un identifiant invalide.
	@Test
	void testConsulterDisponibiliteLivresImprimesExceptionIdInvalideException() {
		
	}
	
}
