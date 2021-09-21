package fr.pythie.webservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/** 
 * Classe modèle pour les éditeurs.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Editeur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@NonNull
	String nom;
	@NonNull
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Adresse adresse = new Adresse();
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "editeur")
	List<Livre> livres;

	/**
	 * 
	 */
	public Editeur() {
		super();
	}
	
	/**
	 * 
	 * @param nom
	 * @param adresse
	 */
	public Editeur(@NonNull String nom, @NonNull Adresse adresse) {
		super();
		this.nom = nom;
		this.adresse = adresse;
	}
	
	/** 
	 * Constructeur complet mis à part l'ID
	 * @param nom
	 * @param adresse
	 * @param livres
	 */
	public Editeur(@NonNull String nom, @NonNull Adresse adresse, List<Livre> livres) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.livres = livres;
	}

	/**
	 * 
	 * @param id
	 * @param nom
	 * @param adresse
	 * @param livres
	 */
	public Editeur(long id, @NonNull String nom, @NonNull Adresse adresse, List<Livre> livres) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.livres = livres;
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
	public Adresse getAdresse() {
		return adresse;
	}

	/**
	 * 
	 * @param adresse
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
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
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((livres == null) ? 0 : livres.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Editeur other = (Editeur) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (id != other.id)
			return false;
		if (livres == null) {
			if (other.livres != null)
				return false;
		} else if (!livres.equals(other.livres))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Editeur [id=" + id + ", nom=" + nom + "]";
	}
	
}
