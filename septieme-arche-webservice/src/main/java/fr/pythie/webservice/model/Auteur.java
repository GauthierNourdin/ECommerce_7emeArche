package fr.pythie.webservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/** 
 * Classe modèle pour les auteurs.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Auteur extends Personne {

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "auteurs")
	List<Livre> livres;

	/**
	 * Constructeur permettant de construire un auteur vide.
	 * 
	 * @since 1.0
	 */
	public Auteur() {
		super();
	}
	
	/**
	 * Constructeur permettant de construire un auteur avec les informations obligatoires.
	 * 
	 * @param nom Le nom de famille.
	 * 
	 * @since 1.0
	 */
	public Auteur(@NonNull String nom) {
		super(nom);
	}
	
	/**
	 * Constructeur permettant de construire un auteur complet mais sans identifiant.
	 * 
	 * @param civilite La civilité.
	 * @param nom Le nom de famille.
	 * @param prenom Le ou les prénoms.
	 * @param livres La liste des livres écrits par cet auteur.
	 * 
	 * @since 1.0
	 */
	public Auteur(String civilite, @NonNull String nom, String prenom, List<Livre> livres) {
		super(civilite, nom, prenom);
		this.livres = livres;
	}

	/**
	 * Constructeur permettant de construire un auteur complet.
	 * 
	 * @param id L'identifiant.
	 * @param civilite La civilité.
	 * @param nom Le nom de famille.
	 * @param prenom Le ou les prénoms.
	 * @param livres La liste des livres écrits par cet auteur.
	 * 
	 * @since 1.0
	 */
	// Constructeur complet
	public Auteur(Long id, String civilite, @NonNull String nom, String prenom, List<Livre> livres) {
		super(id, civilite, nom, prenom);
		this.livres = livres;
	}

	/**
	 * Retourne la liste des livres écrits par cet auteur.
	 * 
	 * @return livres La liste des livres écrits par cet auteur.
	 * 
	 * @see Auteur#setLivres(List)
	 * 
	 * @since 1.0
	 */
	public List<Livre> getLivres() {
		return livres;
	}

	/**
	 * Modifie la liste des livres écrits par cet auteur.
	 * 
	 * @param livres La nouvelle liste des livres écrits par cet auteur.
	 * 
	 * @see Auteur#getLivres()
	 * 
	 * @since 1.0
	 */
	public void setLivres(List<Livre> livres) {
		this.livres = livres;
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
		int result = super.hashCode();
		result = prime * result + ((livres == null) ? 0 : livres.hashCode());
		return result;
	}

	/**
	 * Définit les conditions dans lesquelles un autre objet est égal à cet auteur.
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auteur other = (Auteur) obj;
		if (livres == null) {
			if (other.livres != null)
				return false;
		} else if (!livres.equals(other.livres))
			return false;
		return true;
	}

	/**
	 * Produit la chaîne de caractères réprésentant l'auteur.
	 * On n'y intègre pas les livres écrits associés.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "Auteur [toString()=" + super.toString() + "]";
	}
	
}
