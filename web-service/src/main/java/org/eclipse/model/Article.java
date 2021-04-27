package org.eclipse.model;

import java.util.ArrayList;

public abstract class Article {
/** Classe abstraite servent de base à tous les types d'articles*/

	// Attributs
	private String titre;
	private String resume;
	private int prixHT;
	private int prixTTC;
	private ArrayList<Consultation> consultations;
	private ArrayList<LigneCommande> lignesCommande;
	
	// Constructeurs
	public Article() {
		super();
	}
	
	public Article(String titre, String resume, int prixHT, int prixTTC, ArrayList<Consultation> consultations,
			ArrayList<LigneCommande> lignesCommande) {
		super();
		this.titre = titre;
		this.resume = resume;
		this.prixHT = prixHT;
		this.prixTTC = prixTTC;
		this.consultations = consultations;
		this.lignesCommande = lignesCommande;
	}

	// Getters et Setters
	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public int getPrixHT() {
		return this.prixHT;
	}

	public void setPrixHT(int prixHT) {
		this.prixHT = prixHT;
	}

	public int getPrixTTC() {
		return this.prixTTC;
	}

	public void setPrixTTC(int prixTTC) {
		this.prixTTC = prixTTC;
	}

	public ArrayList<Consultation> getConsultations() {
		return this.consultations;
	}

	public void setConsultations(ArrayList<Consultation> consultations) {
		this.consultations = consultations;
	}

	public ArrayList<LigneCommande> getLignesCommande() {
		return this.lignesCommande;
	}

	public void setLignesCommande(ArrayList<LigneCommande> lignesCommande) {
		this.lignesCommande = lignesCommande;
	}

	// toString
	@Override
	public String toString() {
		return "Article [titre=" + this.titre + ", resume=" + this.resume + ", prixHT=" + this.prixHT + ", prixTTC=" + this.prixTTC
				+ ", consultations=" + affichageConsultations() + ", lignesCommande=" + affichageLignesCommande() + "]";
	}
	
	// toString sans les autres objets
	public String toStringWithoutLinks() {
		return "Article [titre=" + this.titre + ", resume=" + this.resume + ", prixHT=" + this.prixHT + ", prixTTC=" + this.prixTTC
				+ "]";
	}
	
	// Affichage des consultations sans information sur les objets qui y sont liés
	public String affichageConsultations() {
		int nombreConsultations = this.consultations.size();
		if (nombreConsultations > 0) {
			String stringLignesPanier = "[";
			for (int i = 0; i < nombreConsultations - 1; i++) {
				stringLignesPanier += this.consultations.get(i).toStringWithoutLinks() + ", ";
			}
			stringLignesPanier += this.consultations.get(nombreConsultations - 1).toStringWithoutLinks() + "]";
			return stringLignesPanier;
		}
		return "[]";
	}
	
	// Affichage des lignes de commandes associées sans information sur les objets qui y sont liés
	public String affichageLignesCommande() {
		int nombreLignesCommande = this.lignesCommande.size();
		if (nombreLignesCommande > 0) {
			String stringLignesPanier = "[";
			for (int i = 0; i < nombreLignesCommande - 1; i++) {
				stringLignesPanier += this.lignesCommande.get(i).toStringWithoutLinks() + ", ";
			}
			stringLignesPanier += this.lignesCommande.get(nombreLignesCommande - 1).toStringWithoutLinks() + "]";
			return stringLignesPanier;
		}
		return "[]";
	}
	
}
