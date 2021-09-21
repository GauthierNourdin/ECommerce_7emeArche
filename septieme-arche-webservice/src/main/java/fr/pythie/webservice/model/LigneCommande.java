package fr.pythie.webservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/** 
 * Classe modèle pour les lignes de commande.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class LigneCommande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	int quantiteCommandee;
	int prixHT;
	int prixTTC;
	@NonNull
	@JsonIgnoreProperties( { "consultations", "lignesCommande" } )
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Article article;
	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Commande commande = new Commande();

	/**
	 * 
	 */
	public LigneCommande() {
		super();
	}

	/**
	 * 
	 */
	public LigneCommande(@NonNull Article article) {
		super();
		this.article = article;
	}

	/**
	 * Constructeur complet mis à part l'ID
	 * @param quantiteCommandee
	 * @param prixHT
	 * @param prixTTC
	 * @param article
	 * @param commande
	 */
	public LigneCommande(int quantiteCommandee, int prixHT, int prixTTC, @NonNull Article article,
			Commande commande) {
		super();
		this.quantiteCommandee = quantiteCommandee;
		this.prixHT = prixHT;
		this.prixTTC = prixTTC;
		this.article = article;
		this.commande = commande;
	}
	
	/**
	 * 
	 * @param id
	 * @param quantiteCommandee
	 * @param prixHT
	 * @param prixTTC
	 * @param article
	 * @param commande
	 */
	public LigneCommande(long id, int quantiteCommandee, int prixHT, int prixTTC, @NonNull Article article,
			Commande commande) {
		super();
		this.id = id;
		this.quantiteCommandee = quantiteCommandee;
		this.prixHT = prixHT;
		this.prixTTC = prixTTC;
		this.article = article;
		this.commande = commande;
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
	public int getQuantiteCommandee() {
		return quantiteCommandee;
	}

	/**
	 * 
	 * @param quantiteCommandee
	 */
	public void setQuantiteCommandee(int quantiteCommandee) {
		this.quantiteCommandee = quantiteCommandee;
	}
	
	/**
	 * 	
	 */
	public int getPrixHT() {
		return prixHT;
	}

	/**
	 * 
	 * @param prixHT
	 */
	public void setPrixHT(int prixHT) {
		this.prixHT = prixHT;
	}

	/**
	 * 
	 * @return
	 */
	public int getPrixTTC() {
		return prixTTC;
	}

	/**
	 * 
	 * @param prixTTC
	 */
	public void setPrixTTC(int prixTTC) {
		this.prixTTC = prixTTC;
	}

	/**
	 * 
	 * @return
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * 
	 * @param article
	 */
	public void setArticle(Article article) {
		this.article = article;
	}

	/**
	 * 
	 * @return
	 */
	public Commande getCommande() {
		return commande;
	}

	/**
	 * 
	 * @param commande
	 */
	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + ((commande == null) ? 0 : commande.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + prixHT;
		result = prime * result + prixTTC;
		result = prime * result + quantiteCommandee;
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
		LigneCommande other = (LigneCommande) obj;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		if (commande == null) {
			if (other.commande != null)
				return false;
		} else if (!commande.equals(other.commande))
			return false;
		if (id != other.id)
			return false;
		if (prixHT != other.prixHT)
			return false;
		if (prixTTC != other.prixTTC)
			return false;
		if (quantiteCommandee != other.quantiteCommandee)
			return false;
		return true;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "LigneCommande [id=" + id + ", quantiteCommandee=" + quantiteCommandee + ", prixHT=" + prixHT
				+ ", prixTTC=" + prixTTC + "]";
	}
	
}
