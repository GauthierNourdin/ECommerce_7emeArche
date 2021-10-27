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
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	Long id;
	@NonNull
	String nom;
	@NonNull
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	//@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Adresse adresse = new Adresse();
	@JsonIgnore
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "editeur")
	List<Livre> livres;

	/**
	 * Constructeur permettant de construire un éditeur vide.
	 * 
	 * @since 1.0
	 */
	public Editeur() {
		super();
	}
	
	/**
	 * Constructeur permettant de construire un éditeur avec les informations obligatoires.
	 * 
	 * @param nom Le nom.
	 * @param adresse L'adresse commerciale.
	 * 
	 * @since 1.0
	 */
	public Editeur(@NonNull String nom, @NonNull Adresse adresse) {
		super();
		this.nom = nom;
		this.adresse = adresse;
	}
	
	/** 
	 * Constructeur permettant de construire un éditeur complet mais sans identifiant.
	 * 
	 * @param nom Le nom.
	 * @param adresse L'adresse commerciale.
	 * @param livres La liste des livres publiés par cet éditeur.
	 * 
	 * @since 1.0
	 */
	public Editeur(@NonNull String nom, @NonNull Adresse adresse, List<Livre> livres) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.livres = livres;
	}

	/**
	 * Constructeur permettant de construire un éditeur complet mais sans identifiant.
	 * 
	 * @param id L'identifiant.
	 * @param nom Le nom.
	 * @param adresse L'adresse commerciale.
	 * @param livres La liste des livres publiés par cet éditeur.
	 * 
	 * @since 1.0
	 */
	public Editeur(Long id, @NonNull String nom, @NonNull Adresse adresse, List<Livre> livres) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.livres = livres;
	}

	/**
	 * Retourne l'identifiant.
	 * 
	 * @return id L'identifiant.
	 * 
	 * @see Editeur#setId(long)
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
	 * @see Editeur#getId()
	 * 
	 * @since 1.0
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retourne le nom.
	 * 
	 * @return nom Le nom.
	 * 
	 * @see Editeur#setNom(String)
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
	 * @see Editeur#getNom()
	 *  
	 * @since 1.0
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Retourne l'adresse commerciale.
	 * 
	 * @return adresse L'adresse commerciale.
	 * 
	 * @see Editeur#setAdresse(Adresse)
	 * 
	 * @since 1.0
	 */
	public Adresse getAdresse() {
		return adresse;
	}

	/**
	 * Modifie l'adresse commerciale.
	 * 
	 * @param adresse La nouvelle adresse commerciale.
	 * 
	 * @see Editeur#getAdresse()
	 * 
	 * @since 1.0
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	/**
	 * Retourne la liste des livres publiés par cet éditeur.
	 * 
	 * @return livres La liste des livres publiés par cet éditeur.
	 * 
	 * @see Editeur#setLivres(List)
	 * 
	 * @since 1.0
	 */
	public List<Livre> getLivres() {
		return livres;
	}

	/**
	 * Modifie la liste des livres publiés par cet éditeur.
	 * 
	 * @param livres La nouvelle liste des livres publiés par cet éditeur.
	 * 
	 * @see Editeur#getLivres()
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
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((livres == null) ? 0 : livres.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	/**
	 * Définit les conditions dans lesquelles un autre objet est égal à cet éditeur.
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
	 * Produit la chaîne de caractères réprésentant l'éditeur.
	 * On n'y intègre pas l'adresse et les livres associés.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "Editeur [id=" + id + ", nom=" + nom + "]";
	}
	
}
