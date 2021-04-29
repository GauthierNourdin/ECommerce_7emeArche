package org.eclipse.main;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.eclipse.model.Adresse;
import org.eclipse.model.Auteur;
import org.eclipse.model.Client;
import org.eclipse.model.Commande;
import org.eclipse.model.Consultation;
import org.eclipse.model.Editeur;
import org.eclipse.model.Facture;
import org.eclipse.model.Genre;
import org.eclipse.model.LigneCommande;
import org.eclipse.model.LignePanier;
import org.eclipse.model.Livre;
import org.eclipse.model.LivreImprime;
import org.eclipse.model.LivreNumerique;
import org.eclipse.model.Panier;

public class TestClasses {

	public static void main(String[] args) {
		/** Test de toutes les classes concrètes*/ 

		Adresse adresse1 = new Adresse(1, "12", "Route de Londres", "65125", "Nice", "France", "3ème étage", new ArrayList<Editeur>(), new ArrayList<Client>());
		Adresse adresse2 = new Adresse(2, "44", "Allée des Acacias", "91001", "Evry", "France", "", new ArrayList<Editeur>(), new ArrayList<Client>());
		
		Auteur auteur = new Auteur ("Hobbes", "Thomas", new ArrayList<Livre>());
		
		Client client = new Client ("Igamori", "Kito", 1, "kito@igamori.jpn", "hirito", "0458-9689-4522-2196", "08/23", "741", adresse1, adresse1, new Panier(), new ArrayList<Consultation>(), new ArrayList<Commande>());
		
		Commande commande = new Commande (LocalDateTime.now(), new ArrayList<LigneCommande>(), client, new Facture());
		
		Consultation consultation1 = new Consultation (1, LocalDateTime.now(), client, new LivreImprime());
		Consultation consultation2 = new Consultation (2, LocalDateTime.now().minusHours(2), client, new LivreNumerique());
		
		Editeur editeur = new Editeur(1, "Philosophiae", adresse2, new ArrayList<Livre>());
		
		Facture facture = new Facture(commande); 
		
		Genre genre = new Genre(1, "Philosophie", new ArrayList<Livre>());
		
		LigneCommande ligneCommande = new LigneCommande(1, 2, 1413, 1599, new LivreImprime(), commande);
		
		LignePanier lignePanier = new LignePanier(1, 1, new LivreNumerique(), new Panier());
		
		ArrayList<Consultation> livreImprimeConsultations = new ArrayList<Consultation>();
		livreImprimeConsultations.add(consultation1);
		ArrayList<Genre> livreImprimeGenres = new ArrayList<Genre>();
		livreImprimeGenres.add(genre);
		ArrayList<Auteur> livreImprimeAuteurs = new ArrayList<Auteur>();
		livreImprimeAuteurs.add(auteur);
		LivreImprime livreImprime = new LivreImprime("Leviathan, 5ème Edition", "Ouvrage philosophique traitant des relations sociales", 1413, 1599, livreImprimeConsultations,
				new ArrayList<LigneCommande>(), "2-4874-1548184-1", "Leviathan", "Poche", "image1.png",
				198, new Date(156156418000l), livreImprimeGenres, livreImprimeAuteurs,
				editeur, 1, 4, new Date(170914852000l), new Date(167914854000l), 61.5,
				"g", 19.2, 13.4, 2.8, "cm");
		
		ArrayList<Consultation> livreNumeriqueConsultations = new ArrayList<Consultation>();
		livreNumeriqueConsultations.add(consultation2);
		ArrayList<LigneCommande> livreNumeriqueLignesCommande = new ArrayList<LigneCommande>();
		livreNumeriqueLignesCommande.add(ligneCommande);
		ArrayList<Genre> livreNumeriqueGenres = new ArrayList<Genre>();
		livreNumeriqueGenres.add(genre);
		ArrayList<Auteur> livreNumeriqueAuteurs = new ArrayList<Auteur>();
		livreNumeriqueAuteurs.add(auteur);
		LivreNumerique livreNumerique = new LivreNumerique("Notes diverses de Thomas Hobbes", "Notes écrites de la main de Thomas Hobbes. Originaux stockées à la grande bibliothèque nationale de Londres", 2613, 2999, livreNumeriqueConsultations,
				livreNumeriqueLignesCommande, "3-15-787151572-6", "Notes de Thomax Hobbes 2", "Scans de documents originaux", "image2.png",
				301, new Date(204575502000l), livreNumeriqueGenres, livreNumeriqueAuteurs,
				editeur, 2, 193.24, "Mo");
		
		ArrayList<LignePanier> panierLignesPanier = new ArrayList<LignePanier>();
		panierLignesPanier.add(lignePanier);
		Panier panier = new Panier(client, panierLignesPanier);
		
		ArrayList<Client> adresse1Clients = new ArrayList<Client>();
		adresse1Clients.add(client);
		adresse1.setClients(adresse1Clients);
		
		ArrayList<Editeur> adresse2Editeurs = new ArrayList<Editeur>();
		adresse2Editeurs.add(editeur);
		adresse2.setEditeurs(adresse2Editeurs);
		
		ArrayList<Livre> auteurLivres = new ArrayList<Livre>();
		auteurLivres.add(livreImprime);
		auteurLivres.add(livreNumerique);
		auteur.setLivres(auteurLivres);
		
		client.setPanier(panier);
		ArrayList<Consultation> clientConsultations = new ArrayList<Consultation>();
		clientConsultations.add(consultation1);
		clientConsultations.add(consultation2);
		client.setConsultations(clientConsultations);
		ArrayList<Commande> clientCommandes = new ArrayList<Commande>();
		clientCommandes.add(commande);
		client.setCommandes(clientCommandes);
		
		commande.creatingNumero();
		commande.setFacture(facture);
		
		consultation1.setArticle(livreImprime);
		
		consultation2.setArticle(livreNumerique);
		
		ArrayList<Livre> editeurLivres = new ArrayList<Livre>();
		editeurLivres.add(livreImprime);
		editeurLivres.add(livreNumerique);
		editeur.setLivres(editeurLivres);
		
		facture.setNumero(facture.getCommande().getNumero() + "-01");
		
		ArrayList<Livre> genreLivres = new ArrayList<Livre>();
		genreLivres.add(livreImprime);
		genreLivres.add(livreNumerique);
		genre.setLivres(genreLivres);
		
		ligneCommande.setArticle(livreImprime);
		
		lignePanier.setArticle(livreNumerique);
		
		System.out.println("adresse1 :" + adresse1);
		System.out.println("adresse2 :" + adresse2);
		System.out.println("auteur :" + auteur);
		System.out.println("client :" + client);
		System.out.println("commande :" + commande);
		System.out.println("consultation1 :" + consultation1);
		System.out.println("consultation2 :" + consultation2);
		System.out.println("editeur :" + editeur);
		System.out.println("facture :" + facture);
		System.out.println("genre :" + genre);
		System.out.println("ligneCommande :" + ligneCommande);
		System.out.println("lignePanier :" + lignePanier);
		System.out.println("livreImprime :" + livreImprime);
		System.out.println("ligneNumerique :" + livreNumerique);
		System.out.println("panier :" + panier);
	}
}
