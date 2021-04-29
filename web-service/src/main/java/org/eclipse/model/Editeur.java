package org.eclipse.model;

import java.util.ArrayList;

public class Editeur {
/** Classe pour les éditeurs*/

	// Attributs
	private int id;
	private String nom;
	private Adresse adresse = null;
	private ArrayList<Livre> livres = new ArrayList<Livre>();
	
	// Constructeurs
	public Editeur() {
		super();
	}

	public Editeur(int id) {
		super();
		this.id = id;
	}

	public Editeur(String nom, Adresse adresse, ArrayList<Livre> livres) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.livres = livres;
	}

	public Editeur(int id, String nom, Adresse adresse, ArrayList<Livre> livres) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
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

	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
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
		return "Editeur [id=" + this.id + ", nom=" + this.nom + ", adresse=" + this.adresse.toStringWithoutLinks() + ", livres=" + this.affichageLivres() + "]";
	}

	// toString sans les objets associés
	public String toStringWithoutLinks() {
		return "Editeur [id=" + this.id + ", nom=" + this.nom + "]";
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
