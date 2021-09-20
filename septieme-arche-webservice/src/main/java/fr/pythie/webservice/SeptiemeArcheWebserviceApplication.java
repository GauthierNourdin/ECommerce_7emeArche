package fr.pythie.webservice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import fr.pythie.webservice.configuration.DonneesInitiales;
import fr.pythie.webservice.dao.AdresseRepository;
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

/**
 * 
 * @author Gauthier Nourdin.
 *
 */
@SpringBootApplication
public class SeptiemeArcheWebserviceApplication implements ApplicationRunner{

	@Autowired
	private AdresseRepository adresseRepository;
	
	@Autowired
	private AuteurRepository auteurRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CommandeRepository commandeRepository;
	
	@Autowired
	private ConsultationRepository consultationRepository;
	
	@Autowired
	private EditeurRepository editeurRepository;
	
	@Autowired
	private FactureRepository factureRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private LigneCommandeRepository ligneCommandeRepository;
	
	@Autowired
	private LivreImprimeRepository livreImprimeRepository;
	
	@Autowired 
	private LivreNumeriqueRepository livreNumeriqueRepository;
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SeptiemeArcheWebserviceApplication.class, args);
	}
	
	/**
	 * 
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		/* 
		 * On ne doit injecter les données initiales que si les tables sont vides.
		 */
		
		// Adresses déjà présentes en base.
		List<Adresse> adresses = adresseRepository.findAll();
		
		// On teste que la liste d'adresses enregistrées est vide ou nulle.
		if (adresses == null || adresses.isEmpty()) {
			
			// Alors on enregistre les données initiales.
			// Adresses initiales
			Adresse adresse1 = new Adresse("24 bis", "Rue D'Indochine", "35200", "Rennes", "France", "");
			Adresse adresse2 = new Adresse("18", "Avenue des Frères Lumière", "69003", "Lyon", "France", "");
			Adresse adresse3 = new Adresse("374", "Rue du Maréchal Foch", "59160", "Lille", "France", "3e étage");
			Adresse adresse4 = new Adresse("14", "Stirling Street", "EH10", "Edinburgh", "Ecosse", "");
			Adresse adresse5 = new Adresse("4 ter", "Allée du 7ème Art", "33400", "Talence", "France", "Appartement 326");
			Adresse adresse6 = new Adresse("117", "Avenue de Stalingrad", "75013", "Paris", "France", "");
			Adresse adresse7 = new Adresse("280", "Grand place", "1050", "Bruxelles", "Belgique", "");
			Adresse adresse8 = new Adresse("68", "Avenue du Général de Gaulle", "67000", "Strasbourg", "France", "");
			
			// Editeurs sans les livres
			Editeur editeur1 = new Editeur("Editions 7ème Art", adresse1, new ArrayList<Livre>());
			Editeur editeur2 = new Editeur("Editions Philosophie et Arts", adresse2, new ArrayList<Livre>());
			Editeur editeur3 = new Editeur("Groupe Argonaute", adresse2, new ArrayList<Livre>());
			Editeur editeur4 = new Editeur("Historicae", adresse3, new ArrayList<Livre>());
			
			// Clients sans les consultations ni les commandes
			Client client1 = new Client("M", "Finan","Didier","didier.finan@gmail.com","SpyFilmsAreTheBest","1565-4961-1787-1857","08/22","975", adresse4,adresse4, new ArrayList<Consultation>(), new ArrayList<Commande>());
			Client client2 = new Client("M", "Hillfort","William E.","william.hillfort@protonmail.com","AlainDelon","1765-8273-2745-5818","04/23","170",adresse1,adresse5, new ArrayList<Consultation>(), new ArrayList<Commande>());
			Client client3 = new Client("Mme", "Lamour","Mathilde","mathile@lamour.com","LeCinemaCestLaVie","","","",adresse6,adresse6, new ArrayList<Consultation>(), new ArrayList<Commande>());
			Client client4 = new Client("Mme", "Renard","Marguerite","marg.renard@gmail.com","ViveLe7eArt","4354-1961-9711-0489","01/21","178",adresse8,adresse7, new ArrayList<Consultation>(), new ArrayList<Commande>());
			
			// Auteurs sans les livres
			Auteur auteur1 = new Auteur("M", "Picard", "Bernard", new ArrayList<Livre>());
			Auteur auteur2 = new Auteur("M", "Shoeffer", "Edouard", new ArrayList<Livre>());
			Auteur auteur3 = new Auteur("M", "Kurkov", "Alexey", new ArrayList<Livre>());
			Auteur auteur4 = new Auteur("M", "Ryan", "Lyrol", new ArrayList<Livre>());
			Auteur auteur5 = new Auteur("M", "Labbe", "Antoine", new ArrayList<Livre>());
			Auteur auteur6 = new Auteur("Mme", "Sancta", "Maria", new ArrayList<Livre>());
			
			// Genres sans les livres
			Genre genre1 = new Genre("Cinéma américain", new ArrayList<Livre>());
			Genre genre2 = new Genre("Biographie", new ArrayList<Livre>());
			Genre genre3 = new Genre("Livre d'exposition", new ArrayList<Livre>());
			Genre genre4 = new Genre("Historique", new ArrayList<Livre>());
			Genre genre5 = new Genre("Cinéma asiatique", new ArrayList<Livre>());
			Genre genre6 = new Genre("Analyse", new ArrayList<Livre>());
			Genre genre7 = new Genre("Documentaire", new ArrayList<Livre>());
			
			// Livres numériques sans les consultations ni les lignes de commandes
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
			
			LivreNumerique livreNumerique1 = new LivreNumerique("Archives du musée du cinéma de Los Angeles : Charlie Chaplin", "Recueil de photographies, de témoignages et de notes, autour du parcours de Charlie Chaplin en tant qu'acteur et réalisateur.", 1895, 1999, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-713-5", "Charlie Chaplin", ".pdf", "Charlie_Chaplin_Ed7emeArt.png", 98, LocalDate.parse("2014-10-18"), genresLivreNum1, auteursLivreNum1, editeur1, 275.4, "Mo");
			LivreNumerique livreNumerique2 = new LivreNumerique("Musée des Arts et Métiers, Livre d'exposition : Les premiers pas du cinéma", "Livre de l'exposition temporaire début 2015 du musée des Arts et Métiers. Sujet centré sur le matériel et les techniques de tournage et de projection au début du cinéma à 1920", 1421, 1499, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-804-2", "Les premiers pas du cinéma", ".pdf", "Les_premiers_pas_du_cinema_Ed7emeArt.png", 74, LocalDate.parse("2015-01-24"), genresLivreNum2, auteursLivreNum2, editeur1, 115.8, "Mo");
			LivreNumerique livreNumerique3 = new LivreNumerique("Bollywood, simple copie de l'Occident ? Avec préface de l'auteur", "Analyse complète du cinéma bollywoodien contemporain et comparaison avec le cinéma américain et européen.", 2179, 2299, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-17172-074-0", "Bollywood, simple copie de l'Occident ?", ".pdf", "Bollywood_simple_copie_de_l_Occident_EdPhiloEtArt.png", 388, LocalDate.parse("2019-06-04"), genresLivreNum3, auteursLivreNum3, editeur2, 44.2, "Mo");
			LivreNumerique livreNumerique4 = new LivreNumerique("Archives publiques, 1914-1918 : reportages de guerre", "Recueil d'extraits de pellicules tournés sur les théâtres d'opérations de la première guerre modiale avec notes associées et commentaires.", 2843, 2999, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-32724-014-8", "1914-1918 : reportages de guerre", ".pdf", "1914_1918_reportages_de_guerre_Historicae.png", 254, LocalDate.parse("2008-11-11"), genresLivreNum4, auteursLivreNum4, editeur4, 872.5, "Mo");
			
			// Livres imprimés sans les consultations ni les lignes de commandes
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
			
			LivreImprime livreImprime1 = new LivreImprime("Dans les coulises du tournage de Mission Impossible, 2e édition", "Recueil de photographies et de témoignages autour des différents tournages de la série de films Mission Impossible.", 3222, 3399, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-15857-954-7", "Dans les coulises du tournage de Mission Impossible", "Grand carré à couverture rigide", "Dans_les_coulises_du_tournage_de_Mission Impossible_Ed7emeArt.png", 186, LocalDate.parse("2020-08-05"), genresLivreImp1, auteursLivreImp1, editeur1, 4, LocalDate.parse("2020-08-02"), LocalDate.parse("2020-12-28"), 359.4, "g", 21.0, 21.0, 1.63, "cm");
			LivreImprime livreImprime2 = new LivreImprime("Georges Lucas et Star Wars, derrière la saga, édition de luxe", "Bibliographie illustrée de Georges Lucas avec une interview autour de son travail autour de Star Wars.", 3791, 3999, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-27508-154-7", "Georges Lucas et Star Wars, derrière la saga", "A4 à couverture rigide", "Georges_Lucas_et_Star_Wars_derriere_la_saga_GpArgo.png", 238, LocalDate.parse("2016-11-28"), genresLivreImp2, auteursLivreImp2, editeur3, 1, LocalDate.parse("2016-11-13"), LocalDate.parse("2018-04-30"), 645.5, "g", 29.7, 21.0, 2.07, "cm");
			LivreImprime livreImprime3 = new LivreImprime("1944-2019 : soixante-quinze ans de films de super-héros, 1ère édition", "Analyse de l'évolution des films de super-héros depuis 1944. Résumés et illustrations d'une cinquante de films représentatifs.", 3032, 3199, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-27508-207-3", "1944-2019 : soixante-quinze ans de films de super-héros", "A5 à couverture rigide", "1944_2019_soixante_quinze_ans_de_films_de_super_heros_GpArgo.png", 314, LocalDate.parse("2020-04-17"), genresLivreImp3, auteursLivreImp3, editeur3, 3, LocalDate.parse("2020-03-17"), null, 422.7, "g", 21.0, 14.8, 2.72, "cm");
			LivreImprime livreImprime4 = new LivreImprime("Films de guerre : raconter l'histoire des soldats, 4e édition", "Analyse de films de guerre de différentes époques et pays avec un regard particulier sur les personnages de soldat.", 2369, 2499, new ArrayList<Consultation>(), new ArrayList<LigneCommande>(), "978-2-32724-089-9", "Films de guerre : raconter l'histoire des soldats", "Poche à dos carré collé", "Films_de_guerre_raconter_l_histoire_des_soldats_Historicae.png", 360, LocalDate.parse("2017-06-18"), genresLivreImp4, auteursLivreImp4, editeur4, 5, LocalDate.parse("2017-06-15"), LocalDate.parse("2019-02-14"), 290.8, "g", 17.0, 11.0, 3.11, "cm");
			
			// Ajout des livres numériques et imprimés aux éditeurs 
			ArrayList<Livre> livresEditeur1 = new ArrayList<Livre>();
			ArrayList<Livre> livresEditeur2 = new ArrayList<Livre>();
			ArrayList<Livre> livresEditeur3 = new ArrayList<Livre>();
			ArrayList<Livre> livresEditeur4 = new ArrayList<Livre>();
			
			livresEditeur1.add(livreNumerique1);
			livresEditeur1.add(livreNumerique2);
			livresEditeur1.add(livreImprime1);
			livresEditeur2.add(livreNumerique3);
			livresEditeur3.add(livreImprime2);
			livresEditeur3.add(livreImprime3);
			livresEditeur4.add(livreNumerique4);
			livresEditeur4.add(livreImprime4);
			
			editeur1.setLivres(livresEditeur1);
			editeur2.setLivres(livresEditeur2);
			editeur3.setLivres(livresEditeur3);
			editeur4.setLivres(livresEditeur4);
			
			// Ajout des livres numériques et imprimés aux auteurs
			ArrayList<Livre> livresAuteur1 = new ArrayList<Livre>();
			ArrayList<Livre> livresAuteur2 = new ArrayList<Livre>();
			ArrayList<Livre> livresAuteur3 = new ArrayList<Livre>();
			ArrayList<Livre> livresAuteur4 = new ArrayList<Livre>();
			ArrayList<Livre> livresAuteur5 = new ArrayList<Livre>();
			ArrayList<Livre> livresAuteur6 = new ArrayList<Livre>();
			
			livresAuteur1.add(livreNumerique1);
			livresAuteur1.add(livreImprime1);
			livresAuteur1.add(livreImprime2);
			livresAuteur2.add(livreNumerique2);
			livresAuteur2.add(livreImprime2);
			livresAuteur3.add(livreNumerique3);
			livresAuteur3.add(livreNumerique4);
			livresAuteur4.add(livreImprime3);
			livresAuteur5.add(livreImprime4);
			livresAuteur6.add(livreImprime1);
			livresAuteur6.add(livreImprime4);
			
			auteur1.setLivres(livresAuteur1);
			auteur2.setLivres(livresAuteur2);
			auteur3.setLivres(livresAuteur3);
			auteur4.setLivres(livresAuteur4);
			auteur5.setLivres(livresAuteur5);
			auteur6.setLivres(livresAuteur6);
			
			// Ajout des livres numériques et imprimés aux genres
			ArrayList<Livre> livresGenre1 = new ArrayList<Livre>();
			ArrayList<Livre> livresGenre2 = new ArrayList<Livre>();
			ArrayList<Livre> livresGenre3 = new ArrayList<Livre>();
			ArrayList<Livre> livresGenre4 = new ArrayList<Livre>();
			ArrayList<Livre> livresGenre5 = new ArrayList<Livre>();
			ArrayList<Livre> livresGenre6 = new ArrayList<Livre>();
			ArrayList<Livre> livresGenre7 = new ArrayList<Livre>();
			
			livresGenre1.add(livreNumerique1);
			livresGenre1.add(livreImprime1);
			livresGenre1.add(livreImprime2);
			livresGenre2.add(livreNumerique1);
			livresGenre2.add(livreImprime2);
			livresGenre3.add(livreNumerique2);
			livresGenre4.add(livreNumerique2);
			livresGenre4.add(livreNumerique4);
			livresGenre4.add(livreImprime4);
			livresGenre5.add(livreNumerique3);
			livresGenre6.add(livreNumerique3);
			livresGenre6.add(livreImprime3);
			livresGenre7.add(livreNumerique4);
			livresGenre7.add(livreImprime1);
			livresGenre7.add(livreImprime2);
			livresGenre7.add(livreImprime4);
			
			genre1.setLivres(livresGenre1);
			genre2.setLivres(livresGenre2);
			genre3.setLivres(livresGenre3);
			genre4.setLivres(livresGenre4);
			genre5.setLivres(livresGenre5);
			genre6.setLivres(livresGenre6);
			genre7.setLivres(livresGenre7);
			
			// Consultations
			Consultation consultation1 = new Consultation(LocalDateTime.of(2021, 7, 22, 17, 24, 5), client1, livreNumerique1);
			Consultation consultation2 = new Consultation(LocalDateTime.of(2021, 7, 22, 17, 26, 41), client1, livreNumerique3);
			Consultation consultation3 = new Consultation(LocalDateTime.of(2021, 7, 22, 17, 29, 4), client1, livreImprime2);
			Consultation consultation4 = new Consultation(LocalDateTime.of(2021, 7, 22, 17, 32, 17), client1, livreNumerique3);
			Consultation consultation5 = new Consultation(LocalDateTime.of(2021, 8, 2, 6, 55, 5), client1, livreNumerique2);
			Consultation consultation6 = new Consultation(LocalDateTime.of(2021, 8, 2, 7, 9, 37), client1, livreImprime4);
			Consultation consultation7 = new Consultation(LocalDateTime.of(2021, 8, 5, 13, 49, 37), client2, livreImprime1);
			Consultation consultation8 = new Consultation(LocalDateTime.of(2021, 8, 5, 13, 52, 7), client2, livreNumerique4);
			Consultation consultation9 = new Consultation(LocalDateTime.of(2021, 8, 5, 13, 56, 50), client2, livreImprime3);
			Consultation consultation10 = new Consultation(LocalDateTime.of(2021, 8, 9, 22, 07, 34), client3, livreImprime1);
			Consultation consultation11 = new Consultation(LocalDateTime.of(2021, 8, 10, 5, 18, 56), null, livreImprime1);
			Consultation consultation12 = new Consultation(LocalDateTime.of(2021, 8, 10, 12, 24, 14), null, livreImprime2);
			
			// Ajout des consultations aux clients
			ArrayList<Consultation> consultationsClient1 = new ArrayList<Consultation>();
			ArrayList<Consultation> consultationsClient2 = new ArrayList<Consultation>();
			ArrayList<Consultation> consultationsClient3 = new ArrayList<Consultation>();
			
			consultationsClient1.add(consultation1);
			consultationsClient1.add(consultation2);
			consultationsClient1.add(consultation3);
			consultationsClient1.add(consultation4);
			consultationsClient1.add(consultation5);
			consultationsClient1.add(consultation6);
			consultationsClient2.add(consultation7);
			consultationsClient2.add(consultation8);
			consultationsClient2.add(consultation9);
			consultationsClient3.add(consultation10);
			
			client1.setConsultations(consultationsClient1);
			client1.setConsultations(consultationsClient2);
			client1.setConsultations(consultationsClient3);
			
			// Ajout des consultations aux livres numériques et imprimés
			ArrayList<Consultation> consultationsLivreNum1 = new ArrayList<Consultation>();
			ArrayList<Consultation> consultationsLivreNum2 = new ArrayList<Consultation>();
			ArrayList<Consultation> consultationsLivreNum3 = new ArrayList<Consultation>();
			ArrayList<Consultation> consultationsLivreNum4 = new ArrayList<Consultation>();
			ArrayList<Consultation> consultationsLivreImp1 = new ArrayList<Consultation>();
			ArrayList<Consultation> consultationsLivreImp2 = new ArrayList<Consultation>();
			ArrayList<Consultation> consultationsLivreImp3 = new ArrayList<Consultation>();
			ArrayList<Consultation> consultationsLivreImp4 = new ArrayList<Consultation>();
			
			consultationsLivreNum1.add(consultation1);
			consultationsLivreNum2.add(consultation5);
			consultationsLivreNum3.add(consultation2);
			consultationsLivreNum3.add(consultation4);
			consultationsLivreNum4.add(consultation8);
			consultationsLivreImp1.add(consultation7);
			consultationsLivreImp1.add(consultation10);
			consultationsLivreImp1.add(consultation11);
			consultationsLivreImp2.add(consultation3);
			consultationsLivreImp2.add(consultation12);
			consultationsLivreImp3.add(consultation9);
			consultationsLivreImp4.add(consultation6);
			
			livreNumerique1.setConsultations(consultationsLivreNum1);
			livreNumerique2.setConsultations(consultationsLivreNum2);
			livreNumerique3.setConsultations(consultationsLivreNum3);
			livreNumerique4.setConsultations(consultationsLivreNum4);
			livreImprime1.setConsultations(consultationsLivreImp1);
			livreImprime1.setConsultations(consultationsLivreImp2);
			livreImprime1.setConsultations(consultationsLivreImp3);
			livreImprime1.setConsultations(consultationsLivreImp4);
			
			// Commandes sans lignes de commande ni factures
			Commande commande1 = new Commande("210722173401-00000001", "Expédiée", LocalDateTime.of(2021, 7, 22, 17, 34, 1), new ArrayList<LigneCommande>(), client1, new ArrayList<Facture>());
			Commande commande2 = new Commande("210802071157-00000001", "Expédiée", LocalDateTime.of(2021, 8, 2, 7, 11, 57), new ArrayList<LigneCommande>(), client1, new ArrayList<Facture>());
			Commande commande3 = new Commande("210805140247-00000002", "Expédiée", LocalDateTime.of(2021, 8, 5, 14, 2, 47), new ArrayList<LigneCommande>(), client2, new ArrayList<Facture>());
			Commande commande4 = new Commande("210809222724-00000003", "Payée", LocalDateTime.of(2021, 8, 9, 22, 27, 24), new ArrayList<LigneCommande>(), client3, new ArrayList<Facture>());
			
			// Ajout des commandes aux clients
			ArrayList<Commande> commandesClient1 = new ArrayList<Commande>();
			ArrayList<Commande> commandesClient2 = new ArrayList<Commande>();
			ArrayList<Commande> commandesClient3 = new ArrayList<Commande>();
			
			commandesClient1.add(commande1);
			commandesClient1.add(commande2);
			commandesClient2.add(commande3);
			commandesClient3.add(commande4);
			
			client1.setCommandes(commandesClient1);
			client2.setCommandes(commandesClient2);
			client3.setCommandes(commandesClient3);
			
			// Lignes de commandes
			LigneCommande ligneCommande1 = new LigneCommande(1, 1895, 1999, livreNumerique1, commande1);
			LigneCommande ligneCommande2 = new LigneCommande(2, 3222, 3399, livreImprime1, commande1);
			LigneCommande ligneCommande3 = new LigneCommande(3, 3791, 3999, livreImprime2, commande1);
			LigneCommande ligneCommande4 = new LigneCommande(1, 1421, 1499, livreNumerique2, commande2);
			LigneCommande ligneCommande5 = new LigneCommande(1, 2179, 2299, livreNumerique3, commande2);
			LigneCommande ligneCommande6 = new LigneCommande(1, 3222, 3399, livreImprime1, commande3);
			LigneCommande ligneCommande7 = new LigneCommande(2, 3032, 3199, livreImprime3, commande3);
			LigneCommande ligneCommande8 = new LigneCommande(1, 3222, 3399, livreImprime1, commande4);
			
			// Ajout des lignes de commandes aux commandes
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
			
			// Ajout des lignes de commandes aux livres numériques et imprimés
			ArrayList<LigneCommande> lignesCommandeLivreNum1 = new ArrayList<LigneCommande>();
			ArrayList<LigneCommande> lignesCommandeLivreNum2 = new ArrayList<LigneCommande>();
			ArrayList<LigneCommande> lignesCommandeLivreNum3 = new ArrayList<LigneCommande>();
			ArrayList<LigneCommande> lignesCommandeLivreImp1 = new ArrayList<LigneCommande>();
			ArrayList<LigneCommande> lignesCommandeLivreImp2 = new ArrayList<LigneCommande>();
			ArrayList<LigneCommande> lignesCommandeLivreImp3 = new ArrayList<LigneCommande>();
			
			lignesCommandeLivreNum1.add(ligneCommande1);
			lignesCommandeLivreNum2.add(ligneCommande4);
			lignesCommandeLivreNum3.add(ligneCommande5);
			lignesCommandeLivreImp1.add(ligneCommande2);
			lignesCommandeLivreImp1.add(ligneCommande6);
			lignesCommandeLivreImp1.add(ligneCommande8);
			lignesCommandeLivreImp2.add(ligneCommande3);
			lignesCommandeLivreImp3.add(ligneCommande7);
			
			livreNumerique1.setLignesCommande(lignesCommandeLivreNum1);
			livreNumerique2.setLignesCommande(lignesCommandeLivreNum2);
			livreNumerique3.setLignesCommande(lignesCommandeLivreNum3);
			livreImprime1.setLignesCommande(lignesCommandeLivreImp1);
			livreImprime2.setLignesCommande(lignesCommandeLivreImp2);
			livreImprime3.setLignesCommande(lignesCommandeLivreImp3);
			
			// Factures
			Facture facture1 = new Facture("210722173401-00000001-01", "Payée", commande1);
			Facture facture2 = new Facture("210802071157-00000001-01", "Payée", commande2);
			Facture facture3 = new Facture("210805140247-00000002-01", "Payée", commande3);
			Facture facture4 = new Facture("210809222724-00000003-01", "Payée", commande4);
			
			// Ajout des factures aux commandes
			ArrayList<Facture> facturesCommande1 = new ArrayList<Facture>();
			ArrayList<Facture> facturesCommande2 = new ArrayList<Facture>();
			ArrayList<Facture> facturesCommande3 = new ArrayList<Facture>();
			ArrayList<Facture> facturesCommande4 = new ArrayList<Facture>();
			
			facturesCommande1.add(facture1);
			facturesCommande2.add(facture2);
			facturesCommande3.add(facture3);
			facturesCommande4.add(facture4);
			
			commande1.setFactures(facturesCommande1);
			commande2.setFactures(facturesCommande2);
			commande3.setFactures(facturesCommande3);
			commande4.setFactures(facturesCommande4);
			
			// INJECTION DES DONNEES INITIALES DANS LA BASE DE DONNEES
			
			// Sauvegarde des adresses
			adresseRepository.saveAll(Arrays.asList(adresse1, adresse2, adresse3, adresse4, adresse5, adresse6, adresse7, adresse8)); 
			
			// Sauvegarde des éditeurs
			editeurRepository.saveAll(Arrays.asList(editeur1, editeur2, editeur3, editeur4)); 
			
			// Sauvegarde des clients
			clientRepository.saveAll(Arrays.asList(client1, client2, client3, client4));
			
			// Sauvegarde des auteurs
			auteurRepository.saveAll(Arrays.asList(auteur1, auteur2, auteur3, auteur4, auteur5, auteur6)); 
			
			// Sauvegarde des genres
			genreRepository.saveAll(Arrays.asList(genre1, genre2, genre3, genre4, genre5, genre6, genre7));  
			
			// Sauvegarde des livres numériques
			livreNumeriqueRepository.saveAll(Arrays.asList(livreNumerique1, livreNumerique2, livreNumerique3, livreNumerique4));
			
			// Sauvegarde des livres imprimés
			livreImprimeRepository.saveAll(Arrays.asList(livreImprime1, livreImprime2, livreImprime3, livreImprime4));
			
			// Sauvegarde des consultations
			consultationRepository.saveAll(Arrays.asList(consultation1, consultation2, consultation3, consultation4, consultation5, consultation6, consultation7, consultation8, consultation9, consultation10, consultation11, consultation12));
			
			// Sauvegarde des commandes
			commandeRepository.saveAll(Arrays.asList(commande1, commande2, commande3, commande4));
			
			// Sauvegarde des lignes de commande
			ligneCommandeRepository.saveAll(Arrays.asList(ligneCommande1, ligneCommande2, ligneCommande3, ligneCommande4, ligneCommande5, ligneCommande6, ligneCommande7, ligneCommande8));
			
			// Sauvegarde des factures
			factureRepository.saveAll(Arrays.asList(facture1, facture2, facture3, facture4));
			
		}
		// Sinon on ne fait rien.
		
	}

}
