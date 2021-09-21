package fr.pythie.webservice;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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

	// Port Serveur
	int portServeur = 8090;

	@Autowired
	AdresseRepository adresseRepository;
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	ConsultationRepository consultationRepository;
	
	//@Test
	void testListeParDefautArticles() throws URISyntaxException {

		// Préparation de la requête et de son URL.
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + portServeur
				+ "/SeptiemeArche.webservice.fr/userinterface/article/listeArticlesParDefaut";
		URI uri = new URI(baseUrl);

		// Envoi de la requête et récupération du résultat.
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Test sur le code status.
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}

	//@Test
	void testLivresImprimes() throws URISyntaxException {

		// Préparation de la requête et de son URL.
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + portServeur
				+ "/SeptiemeArche.webservice.fr/userinterface/article/livresImprimes";
		URI uri = new URI(baseUrl);

		// Préparation du header.
		HttpHeaders headers = new HttpHeaders();

		// Préparation de l'objet à envoyer
		List<Long> listeIdLivresImprimes = new ArrayList<Long>();
		listeIdLivresImprimes.add((long) 5);
		listeIdLivresImprimes.add((long) 7);

		// Préparation du corps de la requête.
		HttpEntity<List<Long>> request = new HttpEntity<>(listeIdLivresImprimes, headers);

		// Envoi de la requête et récupération du résultat.
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		// Test sur le code status.
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}

	//@Test
	void testLivresNumeriques() throws URISyntaxException {

		// Préparation de la requête et de son URL.
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + portServeur
				+ "/SeptiemeArche.webservice.fr/userinterface/article/livresNumeriques";
		URI uri = new URI(baseUrl);

		// Préparation du header.
		HttpHeaders headers = new HttpHeaders();

		// Préparation de l'objet à envoyer
		List<Long> listeIdLivresNumeriques = new ArrayList<Long>();
		listeIdLivresNumeriques.add((long) 2);
		listeIdLivresNumeriques.add((long) 3);

		// Préparation du corps de la requête.
		HttpEntity<List<Long>> request = new HttpEntity<>(listeIdLivresNumeriques, headers);

		// Envoi de la requête et récupération du résultat.
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		// Test sur le code status.
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}

	//@Test
	void testListeLivresParAuteurOuTitre() throws URISyntaxException {

		// Préparation de la requête et de son URL.
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + portServeur
				+ "/SeptiemeArche.webservice.fr/userinterface/article/listeLivresParAuteurOuTitre";
		URI uri = new URI(baseUrl);

		// Préparation du header.
		HttpHeaders headers = new HttpHeaders();

		// Préparation de l'objet à envoyer
		String auteurOuTitre = "Bollywood";

		// Préparation du corps de la requête.
		HttpEntity<String> request = new HttpEntity<>(auteurOuTitre, headers);

		// Envoi de la requête et récupération du résultat.
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		// Test sur le code status.
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}

	//@Test
	void testListeLivresParAuteurOuTitreResultatVide() throws URISyntaxException {

		// Préparation de la requête et de son URL.
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + portServeur
				+ "/SeptiemeArche.webservice.fr/userinterface/article/listeLivresParAuteurOuTitre";
		URI uri = new URI(baseUrl);

		// Préparation du header.
		HttpHeaders headers = new HttpHeaders();

		// Préparation de l'objet à envoyer
		String auteurOuTitre = "Cette requête est tellement longue qu'il est impossible qu'un livre ou auteur corresponde";

		// Préparation du corps de la requête.
		HttpEntity<String> request = new HttpEntity<>(auteurOuTitre, headers);

		// Envoi de la requête et récupération du résultat.
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		// Test sur le code status.
		Assertions.assertEquals(204, result.getStatusCodeValue());
	}

	//@Test
	void testAjoutConsultationAnonyme() throws URISyntaxException {

		// Préparation de la requête et de son URL.
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + portServeur
				+ "/SeptiemeArche.webservice.fr/userinterface/article/ajoutConsultationAnonyme";
		URI uri = new URI(baseUrl);

		// Préparation du header.
		HttpHeaders headers = new HttpHeaders();

		// Préparation de l'objet à envoyer
		Article articleConsulte = articleRepository.getById((long) 3);
		Consultation consultationAnonyme = new Consultation(LocalDateTime.now(), null, articleConsulte);

		// Préparation du corps de la requête.
		HttpEntity<Consultation> request = new HttpEntity<>(consultationAnonyme, headers);

		// Envoi de la requête et récupération du résultat.
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		// Test sur le code status.
		Assertions.assertEquals(201, result.getStatusCodeValue());
	}

	//@Test
	void testAjoutConsultationClient() throws URISyntaxException {

		// Préparation de la requête et de son URL.
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + portServeur
				+ "/SeptiemeArche.webservice.fr/userinterface/article/ajoutConsultationClient";
		URI uri = new URI(baseUrl);

		// Préparation du header.
		HttpHeaders headers = new HttpHeaders();

		// Préparation de l'objet à envoyer
		Article articleConsulte = articleRepository.getById((long) 5);
		Consultation consultation = new Consultation(LocalDateTime.now(), null, articleConsulte);
		ConsultationAvecIdClient consultationClient = new ConsultationAvecIdClient(consultation, (long) 3);

		// Préparation du corps de la requête.
		HttpEntity<ConsultationAvecIdClient> request = new HttpEntity<>(consultationClient, headers);

		// Envoi de la requête et récupération du résultat.
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		// Test sur le code status.
		Assertions.assertEquals(201, result.getStatusCodeValue());
	}

	//@Test
	void testAjoutClientAConsultation() throws URISyntaxException {

		// Préparation de la requête et de son URL.
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + portServeur
				+ "/SeptiemeArche.webservice.fr/userinterface/article/ajoutClientAConsultation";
		URI uri = new URI(baseUrl);

		// Préparation du header.
		HttpHeaders headers = new HttpHeaders();

		// Préparation de l'objet à envoyer
		Consultation consultation = consultationRepository.getById((long) 12);
		ConsultationAvecIdClient consultationAvecClient = new ConsultationAvecIdClient(consultation, (long) 4);
		
		// Préparation du corps de la requête.
		HttpEntity<ConsultationAvecIdClient> request = new HttpEntity<>(consultationAvecClient, headers);

		// Envoi de la requête et récupération du résultat.
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		// Test sur le code status.
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}

	//@Test
	void testConsulterDisponibiliteLivresImprimes() throws URISyntaxException {

		// Préparation de la requête et de son URL.
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + portServeur
				+ "/SeptiemeArche.webservice.fr/userinterface/article/consulterDisponibiliteLivresImprimes";
		URI uri = new URI(baseUrl);

		// Préparation du header.
		HttpHeaders headers = new HttpHeaders();

		// Préparation de l'objet à envoyer
		List<Long> listeIdLivresImprimes = new ArrayList<Long>();
		listeIdLivresImprimes.add((long) 8);
		listeIdLivresImprimes.add((long) 5);
		listeIdLivresImprimes.add((long) 6);

		// Préparation du corps de la requête.
		HttpEntity<List<Long>> request = new HttpEntity<>(listeIdLivresImprimes, headers);

		// Envoi de la requête et récupération du résultat.
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		// Test sur le code status.
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}

	//@Test
	void testCreationCompteClient() throws URISyntaxException {

		// Préparation de la requête et de son URL.
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + portServeur
				+ "/SeptiemeArche.webservice.fr/userinterface/client/creationCompteClient";
		URI uri = new URI(baseUrl);

		// Préparation du header.
		HttpHeaders headers = new HttpHeaders();

		// Préparation de l'objet à envoyer
		Adresse adresseLivraison = new Adresse("12", "Rue de Nanterre", "75019", "Paris", "France", "");
		Adresse adresseFacturation = new Adresse("12", "Rue de Nanterre", "75019", "Paris", "France", "");
		Client nouveauClient = new Client("Mme", "Kolos", "Anna", "akolos@orange.fr", "LukeBesson", null, null, null, adresseFacturation, adresseLivraison, null, null);
		
		// Préparation du corps de la requête.
		HttpEntity<Client> request = new HttpEntity<>(nouveauClient, headers);

		// Envoi de la requête et récupération du résultat.
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		// Test sur le code status.
		Assertions.assertEquals(201, result.getStatusCodeValue());
	}

	//@Test
	void testAuthentificationClient() throws URISyntaxException {

		// Préparation de la requête et de son URL.
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + portServeur
				+ "/SeptiemeArche.webservice.fr/userinterface/client/authentificationClient";
		URI uri = new URI(baseUrl);

		// Préparation du header.
		HttpHeaders headers = new HttpHeaders();

		// Préparation de l'objet à envoyer
		DemandeAuthentification demandeAuthentification = new DemandeAuthentification("mathile@lamour.com", "LeCinemaCestLaVie");

		// Préparation du corps de la requête.
		HttpEntity<DemandeAuthentification> request = new HttpEntity<>(demandeAuthentification, headers);

		// Envoi de la requête et récupération du résultat.
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		// Test sur le code status.
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	//@Test
	void testAuthentificationClientEchec() throws URISyntaxException {

		// Préparation de la requête et de son URL.
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + portServeur
				+ "/SeptiemeArche.webservice.fr/userinterface/client/authentificationClient";
		URI uri = new URI(baseUrl);

		// Préparation du header.
		HttpHeaders headers = new HttpHeaders();

		// Préparation de l'objet à envoyer
		DemandeAuthentification demandeAuthentification = new DemandeAuthentification("mathile@lamour.com", "NImporteQuoi");

		// Préparation du corps de la requête.
		HttpEntity<DemandeAuthentification> request = new HttpEntity<>(demandeAuthentification, headers);

		// Envoi de la requête et récupération du résultat.
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		// Test sur le code status.
		Assertions.assertEquals(204, result.getStatusCodeValue());
	}

	//@Test
	void testModificationCompteClient() throws URISyntaxException {

		// Préparation de la requête et de son URL.
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + portServeur
				+ "/SeptiemeArche.webservice.fr/userinterface/client/modificationCompteClient";
		URI uri = new URI(baseUrl);

		// Préparation du header.
		HttpHeaders headers = new HttpHeaders();

		// Préparation de l'objet à envoyer
		ArrayList<Consultation> consultations = new ArrayList<Consultation>();
		ArrayList<Commande> commandes = new ArrayList<Commande>();
		Adresse adresseFacturation = adresseRepository.findById((long) 8).orElse(null);
		Adresse adresseLivraison = adresseRepository.findById((long) 7).orElse(null);
		
		Client clientModifie = new Client(4, "Mme", "Renard", "Marguerite", "marguerite.renard@protonmail.com", "ViveLe7eArt", "4354-1961-9711-0489", "01/21", "178", adresseFacturation, adresseLivraison, consultations, commandes);

		// Préparation du corps de la requête.
		HttpEntity<Client> request = new HttpEntity<>(clientModifie, headers);

		// Envoi de la requête et récupération du résultat.
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		// Test sur le code status.
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}

	//@Test
	void testEnregistrementCommande() throws URISyntaxException {

		// Préparation de la requête et de son URL.
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + portServeur
				+ "/SeptiemeArche.webservice.fr/userinterface/commande/enregistrementCommande";
		URI uri = new URI(baseUrl);

		// Préparation du header.
		HttpHeaders headers = new HttpHeaders();

		// Prépartion de l'objet à envoyer.
		List<LigneCommande> lignesCommande = new ArrayList<LigneCommande>();
		
		// Obtention des articles.
		Article article1 = articleRepository.getById((long) 3);
		Article article2 = articleRepository.getById((long) 6);
		Article article3 = articleRepository.getById((long) 8);
		
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
		
		CommandeAvecIdClient commandeAEnregistrer = new CommandeAvecIdClient(commande, (long) 1);

		// Préparation du corps de la requête.
		HttpEntity<CommandeAvecIdClient> request = new HttpEntity<>(commandeAEnregistrer, headers);

		// Envoi de la requête et récupération du résultat.
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		// Test sur le code status.
		Assertions.assertEquals(201, result.getStatusCodeValue());
	}

	//@Test
	void testEnregistrementInformationsBancaires() throws URISyntaxException {

		// Préparation de la requête et de son URL.
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + portServeur
				+ "/SeptiemeArche.webservice.fr/userinterface/paiement/enregistrementInformationsBancaires";
		URI uri = new URI(baseUrl);

		// Préparation du header.
		HttpHeaders headers = new HttpHeaders();

		// Préparation de l'objet à envoyer
		InformationsPaiement informationsPaiement = new InformationsPaiement("7510-4167-6722-0236", "03/22", "570", (long) 3);

		// Préparation du corps de la requête.
		HttpEntity<InformationsPaiement> request = new HttpEntity<>(informationsPaiement, headers);

		// Envoi de la requête et récupération du résultat.
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		// Test sur le code status.
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}

}
