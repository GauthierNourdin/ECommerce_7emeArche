package fr.pythie.webservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Auteur extends Personne {
	/** 
	 * Classe pour les auteurs.
	 */

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "auteurs")
	List<Livre> livres;

	/**
	 * 
	 */
	public Auteur() {
		super();
	}
	
	/**
	 * 
	 * @param nom
	 */
	// Constructeur avec tous les paramètres obligatoires
	public Auteur(@NonNull String nom) {
		super(nom);
	}
	
	/**
	 * Constructeur complet mis à part l'ID
	 * @param civilite
	 * @param nom
	 * @param prenom
	 * @param livres
	 */
	public Auteur(String civilite, @NonNull String nom, String prenom, List<Livre> livres) {
		super(civilite, nom, prenom);
		this.livres = livres;
	}

	/**
	 * 
	 * @param id
	 * @param civilite
	 * @param nom
	 * @param prenom
	 * @param livres
	 */
	// Constructeur complet
	public Auteur(long id, String civilite, @NonNull String nom, String prenom, List<Livre> livres) {
		super(id, civilite, nom, prenom);
		this.livres = livres;
	}

	/**
	 * 
	 * @return
	 */
	public List<Livre> getLivres() {
		return livres;
	}

	/**
	 * 
	 * @param livres
	 */
	public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((livres == null) ? 0 : livres.hashCode());
		return result;
	}

	/**
	 * 
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
	 * 
	 */
	@Override
	public String toString() {
		return "Auteur [livres=" + livres + ", toString()=" + super.toString() + "]";
	}
	
}
