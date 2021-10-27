package fr.pythie.webservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	Long id;
	int quantiteCommandee;
	int prixHT;
	int prixTTC;
	@NonNull
	@JsonIgnoreProperties( { "consultations", "lignesCommande" } )
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Article article;
	@JsonIgnore
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Commande commande = new Commande();

	/**
	 * Constructeur permettant de construire une ligne de commande vide.
	 * 
	 * @since 1.0
	 */
	public LigneCommande() {
		super();
	}

	/**
	 * Constructeur permettant de construire une ligne de commande avec les informations obligatoires.
	 * 
	 * @param article L'article référencé par cette ligne.
	 * 
	 * @since 1.0
	 */
	public LigneCommande(@NonNull Article article) {
		super();
		this.article = article;
	}

	/**
	 * Constructeur permettant de construire une ligne de commande complète mais sans identifiant.
	 * 
	 * @param quantiteCommandee La quantité commandée.
	 * @param prixHT Le prix unitaire hors taxe.
	 * @param prixTTC Le prix unitaire toutes taxes comprises.
	 * @param article L'article référencé par cette ligne.
	 * @param commande La commande contenant cette ligne.
	 * 
	 * @since 1.0
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
	 * Constructeur permettant de construire une ligne de commande complète.
	 * 
	 * @param id L'identifiant.
	 * @param quantiteCommandee La quantité commandée.
	 * @param prixHT Le prix unitaire hors taxe.
	 * @param prixTTC Le prix unitaire toutes taxes comprises.
	 * @param article L'article référencé par cette ligne.
	 * @param commande La commande contenant cette ligne.
	 * 
	 * @since 1.0
	 */
	public LigneCommande(Long id, int quantiteCommandee, int prixHT, int prixTTC, @NonNull Article article,
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
	 * Retourne l'identifiant.
	 * 
	 * @return id L'identifiant.
	 * 
	 * @see LigneCommande#setId(long)
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
	 * @see LigneCommande#getId()
	 * 
	 * @since 1.0
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retourne la quantité commandée.
	 * 
	 * @return quantiteCommandee La quantité commandée.
	 * 
	 * @see LigneCommande#setQuantiteCommandee(int)
	 * 
	 * @since 1.0
	 */
	public int getQuantiteCommandee() {
		return quantiteCommandee;
	}

	/**
	 * Modifie la quantité commandée.
	 *  
	 * @param quantiteCommandee La nouvelle quantité commandée.
	 * 
	 * @see LigneCommande#getQuantiteCommandee()
	 * 
	 * @since 1.0
	 */
	public void setQuantiteCommandee(int quantiteCommandee) {
		this.quantiteCommandee = quantiteCommandee;
	}
	
	/**
	 * Retourne le prix unitaire hors taxe.
	 * 
	 * @return prixHT Le prix unitaire hors taxe.
	 * 
	 * @see LigneCommande#setPrixHT(int)
	 * 
	 * @since 1.0
	 */
	public int getPrixHT() {
		return prixHT;
	}

	/**
	 * Modifie le prix unitaire hors taxe.
	 * 
	 * @param prixHT Le nouveau prix unitaire hors taxe.
	 * 
	 * @see LigneCommande#getPrixHT()
	 * 
	 * @since 1.0
	 */
	public void setPrixHT(int prixHT) {
		this.prixHT = prixHT;
	}

	/**
	 * Retourne le prix unitaire toutes taxes comprises.
	 *  
	 * @return prixTTC Le prix unitaire toutes taxes comprises.
	 * 
	 * @see LigneCommande#setPrixTTC(int)
	 * 
	 * @since 1.0
	 */
	public int getPrixTTC() {
		return prixTTC;
	}

	/**
	 * Modifie le prix unitaire toutes taxes comprises.
	 * 
	 * @param prixTTC Le nouveau prix unitaire toutes taxes comprises.
	 * 
	 * @see LigneCommande#getPrixTTC()
	 * 
	 * @since 1.0
	 */
	public void setPrixTTC(int prixTTC) {
		this.prixTTC = prixTTC;
	}

	/**
	 * Retourne l'article référencé par cette ligne.
	 * 
	 * @return article L'article référencé par cette ligne.
	 * 
	 * @see LigneCommande#setArticle(Article)
	 * 
	 * @since 1.0
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * Modifie l'article référencé par cette ligne.
	 * 
	 * @param article Le nouvel article référencé par cette ligne.
	 * 
	 * @see LigneCommande#getArticle()
	 * 
	 * @since 1.0
	 */
	public void setArticle(Article article) {
		this.article = article;
	}

	/**
	 * Retourne la commande contenant cette ligne.
	 * 
	 * @return commande La commande contenant cette ligne.
	 * 
	 * @see LigneCommande#setCommande(Commande)
	 * 
	 * @since 1.0
	 */
	public Commande getCommande() {
		return commande;
	}

	/**
	 * Modifie la commande contenant cette ligne.
	 * 
	 * @param commande La nouvelle commande contenant cette ligne.
	 * 
	 * @see LigneCommande#getCommande()
	 * 
	 * @since 1.0
	 */
	public void setCommande(Commande commande) {
		this.commande = commande;
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
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + ((commande == null) ? 0 : commande.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + prixHT;
		result = prime * result + prixTTC;
		result = prime * result + quantiteCommandee;
		return result;
	}

	/**
	 * Définit les conditions dans lesquelles un autre objet est égal à cette ligne de commande.
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
	 * Produit la chaîne de caractères réprésentant le genre.
	 * On n'y intègre pas la commande et l'article associés.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "LigneCommande [id=" + id + ", quantiteCommandee=" + quantiteCommandee + ", prixHT=" + prixHT
				+ ", prixTTC=" + prixTTC + "]";
	}
	
}
