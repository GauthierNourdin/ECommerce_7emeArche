package fr.pythie.webservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * Classe modèle abstraite servant de base aux classes Client et Auteur.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public abstract class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String civilite;
	@NonNull
	String nom;
	String prenom;

	/**
	 * @since 1.0
	 */
	public Personne() {
		super();
	}

	/**
	 * 
	 * @param nom
	 * 
	 * @since 1.0
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
	 * 
	 * @since 1.0
	 */
	public Personne(String civilite, @NonNull String nom, String prenom) {
		super();
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	/**
	 * 
	 * @param id L'identifiant.
	 * @param civilite La civilité.
	 * @param nom Le nom.
	 * @param prenom Le ou les prénoms.
	 * 
	 * @since 1.0
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
	 * @return id
	 * 
	 * @see Personne#setId(long)
	 * 
	 * @since 1.0
	 */
	public long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 * 
	 * @see Personne#getId()
	 * 
	 * @since 1.0
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return civilite
	 * 
	 * @see Personne#setCivilite(String)
	 * 
	 * @since 1.0
	 */
	public String getCivilite() {
		return civilite;
	}

	/**
	 * 
	 * @param civilite
	 * 
	 * @see Personne#getCivilite()
	 * 
	 * @since 1.0
	 */
	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	/**
	 * 
	 * @return nom
	 * 
	 * @see Personne#setNom(String)
	 * 
	 * @since 1.0
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * 
	 * @param nom
	 * 
	 * @see Personne#getNom()
	 * 
	 * @since 1.0
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * 
	 * @return prenom
	 * 
	 * @see Personne#setPrenom(String)
	 * 
	 * @since 1.0
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom
	 * 
	 * @see Personne#getPrenom()
	 * 
	 * @since 1.0
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @since 1.0
	 */
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

	/**
	 * @since 1.0
	 */
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

	/**
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "Personne [id=" + id + ", civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
}
