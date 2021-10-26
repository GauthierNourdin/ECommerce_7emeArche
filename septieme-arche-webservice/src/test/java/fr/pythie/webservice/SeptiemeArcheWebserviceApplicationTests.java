package fr.pythie.webservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.pythie.webservice.communication.CommandeAvecIdClient;
import fr.pythie.webservice.communication.ConsultationAvecIdClient;
import fr.pythie.webservice.communication.DemandeAuthentification;
import fr.pythie.webservice.communication.InformationsPaiement;
import fr.pythie.webservice.dao.AdresseRepository;
import fr.pythie.webservice.dao.ArticleRepository;
import fr.pythie.webservice.dao.ClientRepository;
import fr.pythie.webservice.dao.ConsultationRepository;
import fr.pythie.webservice.model.Adresse;
import fr.pythie.webservice.model.Article;
import fr.pythie.webservice.model.Client;
import fr.pythie.webservice.model.Commande;
import fr.pythie.webservice.model.Consultation;
import fr.pythie.webservice.model.LigneCommande;

@SpringBootTest
@AutoConfigureMockMvc
class SeptiemeArcheWebserviceApplicationTests {
	/*
	 * Classe de test permettant de vérifier l'intégralité de l'application. Comme
	 * les tests dépendent des données présents dans la base de données, il est
	 * nécessaire de respecter la procédure suivante : 1) Réinitialiser la base de
	 * données 2) Lancer l'application pour créer les tables et les données
	 * initiales 3) Lancer les tests et vérifier les résultats 4) Eteindre
	 * l'application 5) Réinitialiser la base de données Dans cette version de
	 * l'application seuls les cas courants sont testés, on ne teste pas encore les
	 * cas que le système doit rejeter.
	 */

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	AdresseRepository adresseRepository;
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	ConsultationRepository consultationRepository;
	
	@Test
	void testListeParDefautArticles() throws Exception {

		this.mockMvc
		.perform(get("/userinterface/article/listeArticlesParDefaut"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void testLivresImprimes() throws Exception {

		// Préparation de l'objet à envoyer
		List<Long> listeIdLivresImprimes = new ArrayList<Long>();
		listeIdLivresImprimes.add(5L);
		listeIdLivresImprimes.add(7L);
		
		this.mockMvc
		.perform(post("/userinterface/article/livresImprimes")
        .contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(listeIdLivresImprimes))
        .accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}

	@Test
	void testLivresNumeriques() throws Exception {

		// Préparation de l'objet à envoyer
		List<Long> listeIdLivresNumeriques = new ArrayList<Long>();
		listeIdLivresNumeriques.add(Long.valueOf(2L));
		listeIdLivresNumeriques.add(Long.valueOf(3L));
		
		this.mockMvc
		.perform(post("/userinterface/article/livresNumeriques")
        .contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(listeIdLivresNumeriques))
        .accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}

	@Test
	void testListeLivresParAuteurOuTitre() throws Exception {

		// Préparation de l'objet à envoyer
		String auteurOuTitre = "Charlie Chaplin";
		
		this.mockMvc
		.perform(post("/userinterface/article/listeLivresParAuteurOuTitre")
        .contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(auteurOuTitre))
        .accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	void testListeLivresParAuteurOuTitreResultatVide() throws Exception {

		// Préparation de l'objet à envoyer
		String auteurOuTitre = "Cette requête est tellement longue qu'il est impossible qu'un livre ou auteur corresponde";
		
		this.mockMvc
		.perform(post("/userinterface/article/listeLivresParAuteurOuTitre")
        .contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(auteurOuTitre))
        .accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isNoContent());
		
	}

	@Test
	void testAjoutConsultationAnonyme() throws Exception {

		// Préparation de l'objet à envoyer
		Article articleConsulte = articleRepository.getById(3L);
		Consultation consultationAnonyme = new Consultation(LocalDateTime.now(), null, articleConsulte);
		
		this.mockMvc
		.perform(post("/userinterface/article/ajoutConsultationAnonyme")
        .contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(consultationAnonyme))
        .accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}

	@Test
	void testAjoutConsultationClient() throws Exception {

		// Préparation de l'objet à envoyer
		Article articleConsulte = articleRepository.getById(5L);
		Consultation consultation = new Consultation(LocalDateTime.now(), null, articleConsulte);
		ConsultationAvecIdClient consultationClient = new ConsultationAvecIdClient(consultation, 3L);
		
		this.mockMvc
		.perform(post("/userinterface/article/ajoutConsultationClient")
        .contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(consultationClient))
        .accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}

	@Test
	void testAjoutClientAConsultation() throws Exception {

		// Préparation de l'objet à envoyer
		Consultation consultation = consultationRepository.getById(12L);
		ConsultationAvecIdClient consultationAvecClient = new ConsultationAvecIdClient(consultation, 4L);

		this.mockMvc
		.perform(post("/userinterface/article/ajoutClientAConsultation")
        .contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(consultationAvecClient))
        .accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}

	@Test
	void testConsulterDisponibiliteLivresImprimes() throws Exception {

		// Préparation de l'objet à envoyer
		List<Long> listeIdLivresImprimes = new ArrayList<Long>();
		listeIdLivresImprimes.add(8L);
		listeIdLivresImprimes.add(5L);
		listeIdLivresImprimes.add(6L);
		
		this.mockMvc
		.perform(post("/userinterface/article/consulterDisponibiliteLivresImprimes")
        .contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(listeIdLivresImprimes))
        .accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}

	@Test
	void testCreationCompteClient() throws Exception {

		// Préparation de l'objet à envoyer
		Adresse adresseLivraison = new Adresse("12", "Rue de Nanterre", "75019", "Paris", "France", "");
		Adresse adresseFacturation = new Adresse("12", "Rue de Nanterre", "75019", "Paris", "France", "");
		Client nouveauClient = new Client("Mme", "Kolos", "Anna", "akolos@orange.fr", "LukeBesson", null, null, null, adresseFacturation, adresseLivraison, null, null);
		
		this.mockMvc
		.perform(post("/userinterface/client/creationCompteClient")
        .contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(nouveauClient))
        .accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	void testAuthentificationClient() throws Exception {

		// Préparation de l'objet à envoyer
		DemandeAuthentification demandeAuthentification = new DemandeAuthentification("mathile@lamour.com", "LeCinemaCestLaVie");
		
		this.mockMvc
		.perform(post("/userinterface/client/authentificationClient")
        .contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(demandeAuthentification))
        .accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}
	
	@Test
	void testAuthentificationClientEchec() throws Exception {

		// Préparation de l'objet à envoyer
		DemandeAuthentification demandeAuthentification = new DemandeAuthentification("mathile@lamour.com", "NImporteQuoi");
		
		this.mockMvc
		.perform(post("/userinterface/client/authentificationClient")
        .contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(demandeAuthentification))
        .accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isUnauthorized());

	}

	@Test
	void testModificationCompteClient() throws Exception {

		// Préparation de l'objet à envoyer
		ArrayList<Consultation> consultations = new ArrayList<Consultation>();
		ArrayList<Commande> commandes = new ArrayList<Commande>();
		Adresse adresseFacturation = adresseRepository.findById(8L).orElse(null);
		Adresse adresseLivraison = adresseRepository.findById(7L).orElse(null);
		
		Client clientModifie = new Client(4L, "Mme", "Renard", "Marguerite", "marguerite.renard@protonmail.com", "ViveLe7eArt", "4354-1961-9711-0489", "01/21", "178", adresseFacturation, adresseLivraison, consultations, commandes);
		
		this.mockMvc
		.perform(post("/userinterface/client/modificationCompteClient")
        .contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(clientModifie))
        .accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	void testEnregistrementCommande() throws Exception {

		// Prépartion de l'objet à envoyer.
		List<LigneCommande> lignesCommande = new ArrayList<LigneCommande>();
		
		// Obtention des articles.
		Article article1 = articleRepository.getById(3L);
		Article article2 = articleRepository.getById(6L);
		Article article3 = articleRepository.getById(8L);
		
		// Fabrication des lignes de commande.
		LigneCommande ligneCommande1 = new LigneCommande(1, 1895, 1999, article1, null);
		LigneCommande ligneCommande2 = new LigneCommande(1, 3791, 3999, article2, null);
		LigneCommande ligneCommande3 = new LigneCommande(2, 2369, 2499, article3, null);
		
		// Remplissage de la liste de lignes de commande.
		lignesCommande.add(ligneCommande1);
		lignesCommande.add(ligneCommande2);
		lignesCommande.add(ligneCommande3);
		
		// Préparation de la commande.
		Commande commande = new Commande(null, "En attente de paiement", LocalDateTime.now(), lignesCommande, null, null);
		
		CommandeAvecIdClient commandeAEnregistrer = new CommandeAvecIdClient(commande, 1L);
		
		this.mockMvc
		.perform(post("/userinterface/commande/enregistrementCommande")
        .contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(commandeAEnregistrer))
        .accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}

	@Test
	void testEnregistrementInformationsBancaires() throws Exception {

		// Préparation de l'objet à envoyer
		InformationsPaiement informationsPaiement = new InformationsPaiement("7510-4167-6722-0236", "03/22", "570", Long.valueOf(3L));

		this.mockMvc
		.perform(post("/userinterface/paiement/enregistrementInformationsBancaires")
        .contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(informationsPaiement))
        .accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
