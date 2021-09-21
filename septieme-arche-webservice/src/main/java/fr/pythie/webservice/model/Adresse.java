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
	 * 
	 */
	public Adresse() {
		super();
	}
	
	/**
	 * 
	 * @param numeroRue
	 * @param nomRue
	 * @param codePostal
	 * @param ville
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
	 * 
	 * @param numeroRue
	 * @param nomRue
	 * @param codePostal
	 * @param ville
	 * @param pays
	 * @param complement
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
	 * 
	 * @param id
	 * @param numeroRue
	 * @param nomRue
	 * @param codePostal
	 * @param ville
	 * @param pays
	 * @param complement
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
	public String getNumeroRue() {
		return numeroRue;
	}
	
	/**
	 * 
	 * 
	 */
	public void setNumeroRue(String numeroRue) {
		this.numeroRue = numeroRue;
	}

	/**
	 * 
	 * @return
	 */
	public String getNomRue() {
		return nomRue;
	}

	/**
	 * 
	 * @param nomRue
	 */
	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}

	/**
	 * 
	 * @return
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * 
	 * @param codePostal
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * 
	 * @return
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * 
	 * @param ville
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * 
	 * @return
	 */
	public String getPays() {
		return pays;
	}

	/**
	 * 
	 * @param pays
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}

	/**
	 * 
	 * @return
	 */
	public String getComplement() {
		return complement;
	}

	/**
	 * 
	 * @param complement
	 */
	public void setComplement(String complement) {
		this.complement = complement;
	}

	/**
	 * 
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
	 * 
	 */
	@Override
	public String toString() {
		return "Adresse [id=" + id + ", numeroRue=" + numeroRue + ", nomRue=" + nomRue + ", codePostal=" + codePostal
				+ ", ville=" + ville + ", pays=" + pays + ", complement=" + complement + "]";
	}

}
