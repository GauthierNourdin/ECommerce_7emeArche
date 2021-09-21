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

/**
 * Classe modèle pour sauvegarder les genres des livres.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@NonNull
	String nom;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "genres")
	List<Livre> livres;

	/**
	 * Constructeur permettant de construire un genre vide.
	 * 
	 * @since 1.0
	 */
	public Genre() {
		super();
	}

	/**
	 * Constructeur permettant de construire un genre avec les informations obligatoires.
	 * 
	 * @param nom Le nom.
	 * 
	 * @since 1.0
	 */
	public Genre(@NonNull String nom) {
		super();
		this.nom = nom;
	}
	
	/**
	 * Constructeur permettant de construire un genre complet mais sans identifiant.
	 * 
	 * @param nom Le nom.
	 * @param livres La liste des livres possédant ce genre.
	 * 
	 * @since 1.0
	 */
	public Genre(@NonNull String nom, List<Livre> livres) {
		super();
		this.nom = nom;
		this.livres = livres;
	}
	
	/**
	 * Constructeur permettant de construire un genre complet.
	 * 
	 * @param id L'identifiant.
	 * @param nom Le nom.
	 * @param livres La liste des livres possédant ce genre.
	 * 
	 * @since 1.0
	 */
	public Genre(long id, @NonNull String nom, List<Livre> livres) {
		super();
		this.id = id;
		this.nom = nom;
		this.livres = livres;
	}

	/**
	 * Retourne l'identifiant.
	 * 
	 * @return id L'identifiant.
	 * 
	 * @see Genre#setId(long)
	 * 
	 * @since 1.0
	 */
	public long getId() {
		return id;
	}

	/**
	 * Modifie l'identifiant.
	 * 
	 * @param id Le nouvel identifiant.
	 * 
	 * @see Genre#getId()
	 * 
	 * @since 1.0
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Retourne le nom.
	 * 
	 * @return nom Le nom.
	 * 
	 * @see Genre#setNom(String)
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
	 * @see Genre#getNom()
	 * 
	 * @since 1.0
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Retourne la liste des livres possédant ce genre.
	 * 
	 * @return livres La liste des livres possédant ce genre.
	 * 
	 * @see Genre#setLivres(List)
	 * 
	 * @since 1.0
	 */
	public List<Livre> getLivres() {
		return livres;
	}

	/**
	 * Modifie la liste des livres possédant ce genre.
	 * 
	 * @param livres La nouvelle liste des livres possédant ce genre.
	 * 
	 * @see Genre#getLivres()
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
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((livres == null) ? 0 : livres.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}
	
	/**
	 * Définit les conditions dans lesquelles un autre objet est égal à ce genre.
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
	 * Produit la chaîne de caractères réprésentant le genre.
	 * On n'y intègre pas les livres associés.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "Genre [id=" + id + ", nom=" + nom + "]";
	}
	
}
