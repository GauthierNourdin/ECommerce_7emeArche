package fr.pythie.webservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public abstract class Personne {
	/**
	 * Classe abstraite servant de base aux classes Client et Auteur.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String civilite;
	@NonNull
	String nom;
	String prenom;

	/**
	 * 
	 */
	public Personne() {
		super();
	}

	/**
	 * 
	 * @param nom
	 */
	public Personne(@NonNull String nom) {
		super();
		this.nom = nom;
	}

	/** 
	 * Constructeur complet mis à part l'ID.
	 * 
	 * @param civilite
	 * @param nom
	 * @param prenom
	 */
	public Personne(String civilite, @NonNull String nom, String prenom) {
		super();
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	/**
	 * 
	 * @param id
	 * @param civilite
	 * @param nom
	 * @param prenom
	 */
	public Personne(long id, String civilite, @NonNull String nom, String prenom) {
		super();
		this.id = id;
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
	}

	/**
	 * 
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getCivilite() {
		return civilite;
	}

	/**
	 * 
	 * @param civilite
	 */
	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	/**
	 * 
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * 
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * 
	 * @return
	 */
	public String getPrenom() {
		return prenom;
	}

	/**	
	 *
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((civilite == null) ? 0 : civilite.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (civilite == null) {
			if (other.civilite != null)
				return false;
		} else if (!civilite.equals(other.civilite))
			return false;
		if (id != other.id)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Personne [id=" + id + ", civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
}
