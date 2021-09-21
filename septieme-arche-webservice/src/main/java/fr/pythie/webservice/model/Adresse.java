package fr.pythie.webservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/** 
 * Classe modèle pour les adresses.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Adresse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NonNull
	String numeroRue;
	@NonNull
	String nomRue;
	@NonNull
	String codePostal;
	@NonNull
	String ville;
	String pays;
	String complement;
	
	/**
	 * Constructeur permettant de construire une adresse vide.
	 * 
	 * @since 1.0
	 */
	public Adresse() {
		super();
	}
	
	/**
	 * Constructeur permettant de construire une adresse avec les informations obligatoires.
	 * 
	 * @param numeroRue Le numéro de rue.
	 * @param nomRue Le nom de la rue.
	 * @param codePostal Le code postal.
	 * @param ville La ville.
	 * 
	 * @since 1.0
	 */
	public Adresse(@NonNull String numeroRue, @NonNull String nomRue, @NonNull String codePostal,
			@NonNull String ville) {
		super();
		this.numeroRue = numeroRue;
		this.nomRue = nomRue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	/**
	 * Constructeur permettant de construire une adresse complète mais sans identifiant.
	 * 
	 * @param numeroRue Le numéro de rue.
	 * @param nomRue Le nom de la rue.
	 * @param codePostal Le code postal.
	 * @param ville La ville.
	 * @param pays Le pays.
	 * @param complement Le complément d'adresse.
	 * 
	 * @since 1.0
	 */
	public Adresse(@NonNull String numeroRue, @NonNull String nomRue, @NonNull String codePostal, @NonNull String ville,
			String pays, String complement) {
		super();
		this.numeroRue = numeroRue;
		this.nomRue = nomRue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
		this.complement = complement;
	}

	/**
	 * Constructeur permettant de construire une adresse complète.
	 * 
	 * @param id L'identifiant.
	 * @param numeroRue Le numéro de rue.
	 * @param nomRue Le nom de la rue.
	 * @param codePostal Le code postal.
	 * @param ville La ville.
	 * @param pays Le pays.
	 * @param complement Le complément d'adresse.
	 * 
	 * @since 1.0
	 */
	public Adresse(long id, @NonNull String numeroRue, @NonNull String nomRue, @NonNull String codePostal,
			@NonNull String ville, String pays, String complement) {
		super();
		this.id = id;
		this.numeroRue = numeroRue;
		this.nomRue = nomRue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
		this.complement = complement;
	}

	/**
	 * Retourne l'identifiant.
	 * 
	 * @return id L'identifiant.
	 * 
	 * @see Adresse#setId(long)
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
	 * @see Adresse#getId()
	 * 
	 * @since 1.0
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Retourne le numéro de rue.
	 * 
	 * @return numeroRue Le numéro de rue.
	 * 
	 * @see Adresse#setNumeroRue(String)
	 * 
	 * @since 1.0
	 */
	public String getNumeroRue() {
		return numeroRue;
	}
	
	/**
	 * Modifie le numéro de rue.
	 * 
	 * @param numeroRue Le nouveau numéro de rue.
	 * 
	 * @see Adresse#getNumeroRue()
	 * 
	 * @since 1.0
	 */
	public void setNumeroRue(String numeroRue) {
		this.numeroRue = numeroRue;
	}

	/**
	 * Retourne le nom de rue.
	 * 
	 * @return nomRue Le nom de rue.
	 * 
	 * @see Adresse#setNomRue(String)
	 * 
	 * @since 1.0
	 */
	public String getNomRue() {
		return nomRue;
	}

	/**
	 * Modifie le nom de rue.
	 * 
	 * @param nomRue Le nouveau nom de rue.
	 * 
	 * @see Adresse#getNomRue()
	 * 
	 * @since 1.0
	 */
	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}

	/**
	 * Retourne le code postal.
	 * 
	 * @return codePostal Le code postal.
	 * 
	 * @see Adresse#setCodePostal(String)
	 * 
	 * @since 1.0
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * Modifie le code postal.
	 * 
	 * @param codePostal Le nouveau code postal.
	 * 
	 * @see Adresse#getCodePostal()
	 * 
	 * @since 1.0
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * Retourne la ville.
	 * 
	 * @return ville La ville.
	 * 
	 * @see Adresse#setVille(String)
	 * 
	 * @since 1.0
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * Modifie la ville.
	 * 
	 * @param ville La nouvelle ville.
	 * 
	 * @see Adresse#getVille()
	 * 
	 * @since 1.0
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * Retourne le pays.
	 * 
	 * @return pays Le pays.
	 * 
	 * @see Adresse#setPays(String)
	 * 
	 * @since 1.0
	 */
	public String getPays() {
		return pays;
	}

	/**
	 * Modifie le pays.
	 * 
	 * @param pays Le nouveau pays.
	 * 
	 * @see Adresse#getPays()
	 * 
	 * @since 1.0
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}

	/**
	 * Retourne le complément d'adresse.
	 * 
	 * @return complement Le complément d'adresse.
	 * 
	 * @see Adresse#setComplement(String)
	 * 
	 * @since 1.0
	 */
	public String getComplement() {
		return complement;
	}

	/**
	 * Modifie le complément d'adresse.
	 * 
	 * @param complement Le nouveau complément d'adresse.
	 * 
	 * @see Adresse#getComplement()
	 * 
	 * @since 1.0
	 */
	public void setComplement(String complement) {
		this.complement = complement;
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
		result = prime * result + ((codePostal == null) ? 0 : codePostal.hashCode());
		result = prime * result + ((complement == null) ? 0 : complement.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nomRue == null) ? 0 : nomRue.hashCode());
		result = prime * result + ((numeroRue == null) ? 0 : numeroRue.hashCode());
		result = prime * result + ((pays == null) ? 0 : pays.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		return result;
	}

	/**
	 * Définit les conditions dans lesquelles un autre objet est égal à cette adresse.
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
		Adresse other = (Adresse) obj;
		if (codePostal == null) {
			if (other.codePostal != null)
				return false;
		} else if (!codePostal.equals(other.codePostal))
			return false;
		if (complement == null) {
			if (other.complement != null)
				return false;
		} else if (!complement.equals(other.complement))
			return false;
		if (id != other.id)
			return false;
		if (nomRue == null) {
			if (other.nomRue != null)
				return false;
		} else if (!nomRue.equals(other.nomRue))
			return false;
		if (numeroRue == null) {
			if (other.numeroRue != null)
				return false;
		} else if (!numeroRue.equals(other.numeroRue))
			return false;
		if (pays == null) {
			if (other.pays != null)
				return false;
		} else if (!pays.equals(other.pays))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		return true;
	}

	/**
	 * Produit la chaîne de caractères réprésentant l'adresse.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "Adresse [id=" + id + ", numeroRue=" + numeroRue + ", nomRue=" + nomRue + ", codePostal=" + codePostal
				+ ", ville=" + ville + ", pays=" + pays + ", complement=" + complement + "]";
	}

}
