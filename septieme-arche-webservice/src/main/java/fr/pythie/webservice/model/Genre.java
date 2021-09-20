package fr.pythie.webservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Genre {
	/**
	 * Classe pour sauvegarder les genres des livres.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@NonNull
	String nom;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "genres")
	List<Livre> livres;

	/**
	 * 	
	 */
	public Genre() {
		super();
	}

	/**
	 * 
	 * @param nom
	 */
	public Genre(@NonNull String nom) {
		super();
		this.nom = nom;
	}
	
	/**
	 *  Constructeur complet mis à part l'ID
	 * @param nom
	 * @param livres
	 */
	public Genre(@NonNull String nom, List<Livre> livres) {
		super();
		this.nom = nom;
		this.livres = livres;
	}
	
	/**
	 * 
	 * @param id
	 * @param nom
	 * @param livres
	 */
	public Genre(long id, @NonNull String nom, List<Livre> livres) {
		super();
		this.id = id;
		this.nom = nom;
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
		Genre other = (Genre) obj;
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
		return "Genre [id=" + id + ", nom=" + nom + "]";
	}
	
}
