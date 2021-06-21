package org.eclipse.model;

public abstract class Personne {
	/** Classe abstraite servent de base aux classes Client et Auteur */

	// Attributs
	private String nom;
	private String prenom;

	// Constructeurs
	public Personne() {
		super();
	}

	// Getters et Setters
	public Personne(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	// toString
	@Override
	public String toString() {
		return "Personne [nom=" + this.nom + ", prenom=" + this.prenom + "]";
	}
}
