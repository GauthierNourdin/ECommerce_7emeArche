package org.eclipse.model;

import java.util.ArrayList;

public class Genre {
/** Classe pour sauvegarder le genre des livres*/
	
	// Attributs
	private int id;
	private String nom;
	private ArrayList<Livre> livres = new ArrayList<Livre>();
	
	// Constructeurs
	public Genre() {
		super();
	}
	
	public Genre(int id) {
		super();
		this.id = id;
	}
	
	public Genre(String nom, ArrayList<Livre> livres) {
		super();
		this.nom = nom;
		this.livres = livres;
	}
	
	public Genre(int id, String nom, ArrayList<Livre> livres) {
		super();
		this.id = id;
		this.nom = nom;
		this.livres = livres;
	}

	// Getters et Setters
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public ArrayList<Livre> getLivres() {
		return livres;
	}

	public void setLivres(ArrayList<Livre> livres) {
		this.livres = livres;
	}

	// toString
	@Override
	public String toString() {
		return "Genre [id=" + this.id + ", nom=" + this.nom + ", livres=" + this.affichageLivres() + "]";
	}

	// toString sans les objets associés
	public String toStringWithoutLinks() {
		return "Genre [id=" + this.id + ", nom=" + this.nom + "]";
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

