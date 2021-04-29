package org.eclipse.service;

import java.util.ArrayList;

import org.eclipse.model.Article;
import org.eclipse.model.Livre;

public class LivreService {
	
	// Retourne la liste d'Articles par défaut (selon les ventes totales)
	ArrayList<Article> listeArticleParDefaut() {
		return null;
	}
	
	// Retourne une liste de livres contenant dans le titre ou l'auteur la chaîne de caractère, avec leurs informations basiques
	ArrayList<Livre> rechercheLivreParAuteurTitre (String auteurTitre) {
		return null;
	}
	
	// Ajoute une consultation anonyme
	int ajoutConsultationAnonyme (int idArticle) {
		return -1;
	}

	// Ajoute une consultation d'un client identifié
	int ajoutConsultationClient (int idArticle, int idClient) {
		return -1;
	}
	
	// Associe un client à une consultation anonyme
	void ajoutClientConsultation (int idConsultation, int idClient) {
		
	}
	
	// Retourne les informations de second ordre d'un livre
	Livre informationsComplementairesLivre (int idLivre) {
		return null;
	}
	
	// Retourne le stock actuel d'un livre imprimé
	int VerifierStockLivreImprime (int idLivre) {
		return -1;
	}
	
}
