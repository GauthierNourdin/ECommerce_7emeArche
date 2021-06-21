package org.eclipse.model;

import java.util.ArrayList;

public class Auteur extends Personne {
	/** Classe pour les auteurs */

	// Attributs
	private int id;
	private ArrayList<Livre> livres = new ArrayList<Livre>();

	// Constructeurs
	public Auteur() {
		super();
	}

	public Auteur(int id) {
		super();
		this.id = id;
	}

	public Auteur(String nom, String prenom, ArrayList<Livre> livres) {
		super(nom, prenom);
		this.livres = livres;
	}

	public Auteur(String nom, String prenom, int id, ArrayList<Livre> livres) {
		super(nom, prenom);
		this.id = id;
		this.livres = livres;
	}

	// Getters et Setters
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Livre> getLivres() {
		return this.livres;
	}

	public void setLivres(ArrayList<Livre> livres) {
		this.livres = livres;
	}

	// toString
	@Override
	public String toString() {
		return "Auteur [id=" + this.id + ", livres=" + this.affichageLivres() + ", nom=" + this.getNom() + ", prenom="
				+ this.getPrenom() + "]";
	}

	// toString sans les objets associés
	public String toStringWithoutLinks() {
		return "Auteur [id=" + this.id + ", nom=" + this.getNom() + ", prenom=" + this.getPrenom() + "]";
	}

	// Affichage des livres associés sans information sur les objets qui y sont liés
	public String affichageLivres() {
		int nombreLivres = this.livres.size();
		if (nombreLivres > 0) {
			String stringLivres = "[";
			for (int i = 0; i < nombreLivres - 1; i++) {
				stringLivres += this.livres.get(i).toStringWithoutLinks() + ", ";
			}
			stringLivres += this.livres.get(nombreLivres - 1).toStringWithoutLinks() + "]";
			return stringLivres;
		}
		return "[]";
	}
}
