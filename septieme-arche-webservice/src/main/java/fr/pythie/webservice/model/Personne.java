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
	Long id;
	String civilite;
	@NonNull
	String nom;
	String prenom;

	/**
	 * Constructeur permettant de construire une personne vide.
	 * 
	 * @since 1.0
	 */
	public Personne() {
		super();
	}

	/**
	 * Constructeur permettant de construire une personne avec toutes les informations obligatoires.
	 * 
	 * @param nom Le nom.
	 * 
	 * @since 1.0
	 */
	public Personne(@NonNull String nom) {
		super();
		this.nom = nom;
	}

	/** 
	 * Constructeur permettant permet de construire une personne complète mais sans identifiant.
	 * 
	 * @param civilite La civilité.
	 * @param nom Le nom.
	 * @param prenom Le ou les prénoms.
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
	 * Constructeur permettant permet de construire une personne complète.
	 * 
	 * @param id L'identifiant.
	 * @param civilite La civilité.
	 * @param nom Le nom.
	 * @param prenom Le ou les prénoms.
	 * 
	 * @since 1.0
	 */
	public Personne(Long id, String civilite, @NonNull String nom, String prenom) {
		super();
		this.id = id;
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
	}

	/**
	 * Retourne l'identifiant.
	 * 
	 * @return id L'identifiant.
	 * 
	 * @see Personne#setId(long)
	 * 
	 * @since 1.0
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Modifie l'identifiant.
	 * 
	 * @param id Le nouvel identifiant.
	 * 
	 * @see Personne#getId()
	 * 
	 * @since 1.0
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retourne la civilité.
	 * 
	 * @return civilite La civilité.
	 * 
	 * @see Personne#setCivilite(String)
	 * 
	 * @since 1.0
	 */
	public String getCivilite() {
		return civilite;
	}

	/**
	 * Modifie la civilité.
	 * 
	 * @param civilite La nouvelle civilité.
	 * 
	 * @see Personne#getCivilite()
	 * 
	 * @since 1.0
	 */
	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	/**
	 * Retourne le nom.
	 * 
	 * @return nom Le nom.
	 * 
	 * @see Personne#setNom(String)
	 * 
	 * @since 1.0
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie le nom.
	 * 
	 * @param nom Le nouveau nom.
	 * 
	 * @see Personne#getNom()
	 * 
	 * @since 1.0
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Retourne le ou les prénoms.
	 * 
	 * @return prenom Le ou les prénoms.
	 * 
	 * @see Personne#setPrenom(String)
	 * 
	 * @since 1.0
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Modifie le ou les prénoms.
	 * 
	 * @param prenom Le nouveau ou les nouveaux prénoms.
	 * 
	 * @see Personne#getPrenom()
	 * 
	 * @since 1.0
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Calcule le code hash.
	 * 
	 * @return result Le code hash.
	 * 
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
	 * Définit les conditions dans lesquelles un autre objet est égal à cette personne.
	 *
	 * @param obj L'objet auquel on veut comparer.
	 *
	 * @return true Si les deux objets sont identiques, false sinon.
	 * 
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
	 * Produit la chaîne de caractères réprésentant la personne.
	 * On n'y intègre pas l'éditeur, les consultations, les lignes de commandes, les genres et les auteurs associés.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "Personne [id=" + id + ", civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
}
