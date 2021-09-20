package fr.pythie.webservice.service.userinterface;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pythie.webservice.communication.CommandeAvecIdClient;
import fr.pythie.webservice.dao.ArticleRepository;
import fr.pythie.webservice.dao.ClientRepository;
import fr.pythie.webservice.dao.CommandeRepository;
import fr.pythie.webservice.dao.LigneCommandeRepository;
import fr.pythie.webservice.dao.LivreImprimeRepository;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.exception.StockInsuffisantException;
import fr.pythie.webservice.interfaces.service.userinterface.CommandeService;
import fr.pythie.webservice.model.Article;
import fr.pythie.webservice.model.Client;
import fr.pythie.webservice.model.Commande;
import fr.pythie.webservice.model.LigneCommande;
import fr.pythie.webservice.model.LivreImprime;

/**
 * Classe service implémentant les méthodes demandées par CommandeService afin
 * de traîter les demandes concernant les commandes faites par l'interface
 * utilisateur
 */
@Component
public class CommandeServiceImpl implements CommandeService {

	@Autowired
	CommandeRepository commandeRepository;
	
	@Autowired
	LigneCommandeRepository ligneCommandeRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	LivreImprimeRepository livreImprimeRepository;
	
	@Autowired
	ArticleRepository articleRepository;

	/**
	 * Enregistre une commande et modifie les stocks des articles correspondants s'ils ont une information de stock.
	 */
	public CommandeAvecIdClient enregistrementCommande(CommandeAvecIdClient commandeAvecIdClient)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, StockInsuffisantException,
			ClientInconnuException {

		// On doit tout d'abord vérifier l'identité du client
		Client client;

		// On essaie d'accéder aux informations du client avec l'identifiant envoyé
		try {
			client = clientRepository.getById(commandeAvecIdClient.getIdClient());
		} catch (Exception e) {
			// En cas d'erreur lors de la lecture de la base de données, on lève une
			// exception
			throw new LectureBaseDonneesException();
		}

		// Si l'identifiant du client n'est pas connu on lève une exception
		if (client == null) {
			throw new ClientInconnuException();
		}

		/*
		 * On va ensuite pour chaque ligne de commande faire une série d'opérations
		 * réversibles afin d'ajuster les stocks et au cas où on trouverait un problème
		 * on annule les opérations déjà faites avant de lancer l'exception
		 */
		
		ArrayList<LigneCommande> lignesCommandeAyantDiminueStock = new ArrayList<LigneCommande>();

		// Boucle sur les lignes de commandes
		for (LigneCommande lignComm : commandeAvecIdClient.getCommande().getLignesCommande()) {

			// Vérification que la ligne de commande correspond à un livre imprimé
			if (lignComm.getArticle() instanceof LivreImprime) {
				// On essaie d'extraire les informations de l'article
				LivreImprime livreImprime;
				try {
					livreImprime = livreImprimeRepository.findById(lignComm.getArticle().getId()).orElse(null);
				} catch (Exception e) {
					// Si on a un problème à la lecture on annule les opérations précédentes et on lève une exception
					annulationDiminutionStocks(lignesCommandeAyantDiminueStock);
					
					throw new LectureBaseDonneesException();
				}

				// On vérifie que les stocks soient suffisants sinon on lève une exception
				if (livreImprime.getQuantiteStock() < lignComm.getQuantiteCommandee()) {
					annulationDiminutionStocks(lignesCommandeAyantDiminueStock);
					
					throw new StockInsuffisantException();
				}
				
				// On ajuste le stock et on enregistre.
				livreImprime.setQuantiteStock(livreImprime.getQuantiteStock() - lignComm.getQuantiteCommandee());
				
				try {
					livreImprimeRepository.save(livreImprime);
				} catch (Exception exception) {
					// Si on a un problème à la lecture on annule les opérations précédentes et on lève une exception
					annulationDiminutionStocks(lignesCommandeAyantDiminueStock);
					
					throw new EcritureBaseDonneesException();
				}
				
				// On ajoute la ligne de commande à la liste des lignes de commandes ayant provoqué une diminution du stock.
				lignesCommandeAyantDiminueStock.add(lignComm);
				
			}
			
		}
		
		// On doit maintenant mettre en cohérence les différents objets avant de les enregistrer.
		commandeAvecIdClient.getCommande().setClient(client);
		
		ArrayList<Commande> commandesClient = (ArrayList<Commande>) client.getCommandes();
		commandesClient.add(commandeAvecIdClient.getCommande());
		client.setCommandes(commandesClient);
	    
		// Liste d'articles à sauvegarder
		ArrayList<Article> articles = new ArrayList<Article>();
		
		ArrayList<LigneCommande> lignesCommande = new ArrayList<LigneCommande>();
		
		// Boucle sur les lignes de commande
		for (LigneCommande lignComm : commandeAvecIdClient.getCommande().getLignesCommande()) {
			lignComm.setCommande(commandeAvecIdClient.getCommande());
			
			Article article = lignComm.getArticle();
			ArrayList<LigneCommande> articleLignesCommandes = (ArrayList<LigneCommande>) article.getLignesCommande();
			articleLignesCommandes.add(lignComm);
			article.setLignesCommande(articleLignesCommandes);

			articles.add(article);
			
			lignesCommande.add(lignComm);
		}
		
		// On prépare la commande
		commandeAvecIdClient.getCommande().setDate(LocalDateTime.now());
		commandeAvecIdClient.getCommande().definirNumero();
		
		// On essaie d'enregistrer la nouvelle commande dans la base de données
		Commande nouvelleCommande;
		
		try {
			nouvelleCommande = commandeRepository.save(commandeAvecIdClient.getCommande());
		} catch (Exception exception) {
			// Si on a un problème à l'écriture on annule les opérations précédentes et on lève une exception
			annulationDiminutionStocks(lignesCommandeAyantDiminueStock);
			
			throw new EcritureBaseDonneesException();
		}

		// On essaie d'enregistrer les nouvelles lignes de commande dans la base de données
		ArrayList<LigneCommande> nouvellesLignesCommande;
		
		try {
			nouvellesLignesCommande = (ArrayList<LigneCommande>) ligneCommandeRepository.saveAll(lignesCommande);
		} catch (Exception exception) {
			// Si on a un problème à l'écriture on annule les opérations précédentes et on lève une exception
			annulationDiminutionStocks(lignesCommandeAyantDiminueStock);
			
			commandeRepository.delete(nouvelleCommande);
			
			throw new EcritureBaseDonneesException();
		}
					
		// On essaie de mettre à jour les articles dans la base de données
		try {
			articleRepository.saveAll(articles);
		} catch (Exception exception) {
			// Si on a un problème à l'écriture on annule les opérations précédentes et on lève une exception
			annulationDiminutionStocks(lignesCommandeAyantDiminueStock);
			
			commandeRepository.delete(nouvelleCommande);
			
			ligneCommandeRepository.deleteAll(nouvellesLignesCommande);
			
			throw new EcritureBaseDonneesException();
		}
		
		// On essaie de mettre à jour le client dans la base de données
		Client clientModifie;
		
		try {
			clientModifie = clientRepository.save(client);
		} catch (Exception exception) {
			// Si on a un problème à l'écriture on annule les opérations précédentes et on lève une exception
			annulationDiminutionStocks(lignesCommandeAyantDiminueStock);
			
			commandeRepository.delete(nouvelleCommande);
			
			ligneCommandeRepository.deleteAll(nouvellesLignesCommande);
			
			throw new EcritureBaseDonneesException();
		}
		
		return new CommandeAvecIdClient(nouvelleCommande, clientModifie.getId());
	}

	/**
	 *
	 * @param lignesCommande
	 * @throws LectureBaseDonneesException
	 * @throws EcritureBaseDonneesException
	 */
	public void annulationDiminutionStocks (List<LigneCommande> lignesCommande) throws LectureBaseDonneesException, EcritureBaseDonneesException {
		// Fonction spéciale qui permet d'annuler la diminution des stocks en cas de problème
		
		// Boucle sur les lignes de commandes ayant provoqué une diminution des stocks
		for (LigneCommande lignComm : lignesCommande) {
			
			// Lecture des informations du livre imprimé correspondant
			LivreImprime livreImprime;
			
			try {
				livreImprime = livreImprimeRepository.getById(lignComm.getArticle().getId());
			} catch (Exception exception) {
				// En cas d'erreur on lève une exception
				throw new LectureBaseDonneesException();
			}
			
			// Ajustement du stock et enregistrement.
			livreImprime.setQuantiteStock(livreImprime.getQuantiteStock() + lignComm.getQuantiteCommandee());
			
			try {
				livreImprimeRepository.save(livreImprime);
			} catch (Exception exception) {
				// En cas d'erreur on lève une exception
				throw new EcritureBaseDonneesException();
			}
			
		}
		
	}

}
