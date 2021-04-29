package org.eclipse.model;

import java.util.ArrayList;

public class Panier {
/** Classe pour représenter le panier d'un client */
	
	// Attributs
	private Client client = null;
	private ArrayList<LignePanier> lignesPanier = new ArrayList<LignePanier>();
	
	// Constructeurs
	public Panier() {
		super();
	}
	
	public Panier(Client client, ArrayList<LignePanier> lignesPanier) {
		super();
		this.client = client;
		this.lignesPanier = lignesPanier;
	}

	// Getters et Setters
	public Client getClient() {
		return this.client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}

	public ArrayList<LignePanier> getLignesPanier() {
		return this.lignesPanier;
	}

	public void setLignesPanier(ArrayList<LignePanier> lignesPanier) {
		this.lignesPanier = lignesPanier;
	}

	// toString
	@Override
	public String toString() {
		return "Panier [client=" + this.client.toStringWithoutLinks() + ", lignesPanier=" + this.affichageLignesPanier() + "]";
	}

	// toString sans information client
	public String toStringWithoutClient() {
		return "Panier [lignesPanier=" + this.affichageLignesPanier() + "]";
	}
	
	// Affichage des lignes de panier sans information sur les objets qui y sont liés
	public String affichageLignesPanier() {
		int nombreLignesPanier = this.lignesPanier.size();
		if (nombreLignesPanier > 0) {
			String stringLignesPanier = "[";
			for (int i = 0; i < nombreLignesPanier - 1; i++) {
				stringLignesPanier += this.lignesPanier.get(i).toStringWithoutLinks() + ", ";
			}
			stringLignesPanier += this.lignesPanier.get(nombreLignesPanier - 1).toStringWithoutLinks() + "]";
			return stringLignesPanier;
		}
		return "[]";
	}
}
