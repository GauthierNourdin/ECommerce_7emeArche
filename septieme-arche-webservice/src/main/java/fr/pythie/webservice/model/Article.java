package fr.pythie.webservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import fr.pythie.webservice.communication.ArticleDeserializer;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/** 
 * Classe modèle abstraite servant de base à tous les types d'articles.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@JsonDeserialize(using = ArticleDeserializer.class)
public abstract class Article {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@NonNull
	String titre;
	@NonNull
	String resume;
	int prixHT;
	int prixTTC;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
	List<Consultation> consultations;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
	List<LigneCommande> lignesCommande;

	/** 
	 * Ce constructeur permet de construire un article vide.
	 * 
	 * @since 1.0
	 */
	public Article() {
		super();
	}
	
	/**
	 * Ce constructeur permet de construire un article avec les informations obligatoires.
	 * 
	 * @param titre Le titre affiché de l'article.
	 * @param resume Le résumé associé à l'article.
	 * 
	 * @since 1.0
	 */
	public Article(@NonNull String titre, @NonNull String resume) {
		super();
		this.titre = titre;
		this.resume = resume;
	}
	
	/** 
	 * Ce constructeur permet de construire un article complet mais sans identifiant
	 * 
	 * @param titre Le titre affiché de l'article.
	 * @param resume Le résumé associé à l'article.
	 * @param prixHT Le prix unitaire hors taxe de l'article multiplié par 100.
	 * @param prixTTC Le prix unitaire toutes taxes comprises de l'article multiplié par 100.
	 * @param consultations La liste de consultations de la page de cette article.
	 * @param lignesCommandes La liste des lignes de commande associées à cet article.
	 * 
	 * @since 1.0
	 */
	public Article(@NonNull String titre, @NonNull String resume, int prixHT, int prixTTC,
			List<Consultation> consultations, List<LigneCommande> lignesCommande) {
		super();
		this.titre = titre;
		this.resume = resume;
		this.prixHT = prixHT;
		this.prixTTC = prixTTC;
		this.consultations = consultations;
		this.lignesCommande = lignesCommande;
	}
	
	/**
	 * Ce constructeur permet de construire un article complet
	 * 
	 * @param id L'identifiant de cet article.
	 * @param titre Le titre affiché de l'article.
	 * @param resume Le résumé associé à l'article.
	 * @param prixHT Le prix unitaire hors taxe de l'article multiplié par 100.
	 * @param prixTTC Le prix unitaire toutes taxes comprises de l'article multiplié par 100.
	 * @param consultations La liste de consultations de la page de cette article.
	 * @param lignesCommande La liste des lignes de commande associées à cet article.
	 * 
	 * @since 1.0
	 */
	public Article(long id, @NonNull String titre, @NonNull String resume, int prixHT, int prixTTC,
			List<Consultation> consultations, List<LigneCommande> lignesCommande) {
		super();
		this.id = id;
		this.titre = titre;
		this.resume = resume;
		this.prixHT = prixHT;
		this.prixTTC = prixTTC;
		this.consultations = consultations;
		this.lignesCommande = lignesCommande;
	}

	/**
	 * Retourne l'identifiant de l'article.
	 * 
	 * @return id L'identifiant de l'article.
	 * 
	 * @see Article#setId(long)
	 * 
	 * @since 1.0
	 */
	public long getId() {
		return id;
	}

	/**
	 * Modifie l'identifiant de l'article.
	 * 
	 * @param id Le nouvel identifiant de l'article.
	 * 
	 * @see Article#getId()
	 * 
	 * @since 1.0
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Retourne le titre de l'article.
	 * 
	 * @return titre Le titre de l'article.
	 * 
	 * @see Article#setTitre(String)
	 * 
	 * @since 1.0
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * Modifie le titre de l'article.
	 * 
	 * @param titre Le nouveau titre de l'article.
	 * 
	 * @see Article#getTitre()
	 * 
	 * @since 1.0
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * Retourne le résumé de l'article.
	 * 
	 * @return resume Le résumé de l'article.
	 * 
	 * @see Article#setResume(String)
	 * 
	 * @since 1.0
	 */
	public String getResume() {
		return resume;
	}

	/**
	 * Modifie le résumé de l'article.
	 * 
	 * @param resume Le nouveau résumé de l'article.
	 * 
	 * @see Article#getResume()
	 * 
	 * @since 1.0
	 */
	public void setResume(String resume) {
		this.resume = resume;
	}

	/**
	 * Retourne le prix unitaire hors taxe de l'article.
	 * 
	 * @return prixHT Le prix unitiare hors taxe de l'article.
	 * 
	 * @see Article#setPrixHT(int)
	 * 
	 * @since 1.0
	 */
	public int getPrixHT() {
		return prixHT;
	}

	/**
	 * Modifie le prix unitaire hors taxe de l'article.
	 * 
	 * @param prixHT Le nouveau prix unitaire hors taxe de l'article.
	 * 
	 * @see Article#getPrixHT()
	 * 
	 * @since 1.0
	 */
	public void setPrixHT(int prixHT) {
		this.prixHT = prixHT;
	}

	/**
	 * Retourne le prix unitaire toutes taxes comprises de l'article.
	 * 
	 * @return prixTTC Le prix unitaire toutes taxes comprises de l'article.
	 * 
	 * @see Article#setPrixTTC(int)
	 * 
	 * @since 1.0
	 */
	public int getPrixTTC() {
		return prixTTC;
	}

	/**
	 * Modifie le prix unitaire toutes taxes comprises de l'article.
	 * 
	 * @param prixTTC Le nouveau prix unitaire toutes taxes comprises de l'article.
	 * 
	 * @see Article#getPrixTTC()
	 * 
	 * @since 1.0
	 */
	public void setPrixTTC(int prixTTC) {
		this.prixTTC = prixTTC;
	}

	/**
	 * Retourne la liste des consultations associées à cet article.
	 * 
	 * @return consultations La liste des consultations associées à cet article.
	 * 
	 * @see Article#setConsultations(List<Consultation>)
	 * 
	 * @since 1.0
	 */
	public List<Consultation> getConsultations() {
		return consultations;
	}

	/**
	 * Modifie la liste des consultations associées à cet article.
	 * 
	 * @param consultations La nouvelle liste des consultations associées à cet article.
	 * 
	 * @see Article#getConsultations()
	 * 
	 * @since 1.0
	 */
	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}

	/** 
	 * Retourne la liste des lignes de commande associées à cet article.
	 * 
	 * @return lignesCommande La liste des lignes de commande associées à cet article.
	 * 
	 * @see Article#setLignesCommande(List<LigneCommande>)
	 * 
	 * @since 1.0
	 */
	public List<LigneCommande> getLignesCommande() {
		return lignesCommande;
	}

	/**
	 * Modifie la liste des lignes de commande associées à cet article.
	 * 
	 * @param lignesCommande La nouvelle liste des lignes de commande associées à cet article.
	 * 
	 * @see Article#getLignesCommande()
	 * 
	 * @since 1.0
	 */
	public void setLignesCommande(List<LigneCommande> lignesCommande) {
		this.lignesCommande = lignesCommande;
	}

	/**
	 * Cette fonction définit les conditions dans lesquelles un autre objet est égal à cet article.
	 *
	 * @param obj L'objet auquel on veut comparer celui-ci.
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
		Article other = (Article) obj;
		if (consultations == null) {
			if (other.consultations != null)
				return false;
		} else if (!consultations.equals(other.consultations))
			return false;
		if (id != other.id)
			return false;
		if (lignesCommande == null) {
			if (other.lignesCommande != null)
				return false;
		} else if (!lignesCommande.equals(other.lignesCommande))
			return false;
		if (prixHT != other.prixHT)
			return false;
		if (prixTTC != other.prixTTC)
			return false;
		if (resume == null) {
			if (other.resume != null)
				return false;
		} else if (!resume.equals(other.resume))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}

	/**
	 * Cette fonction permet de calculer le code hash de cet objet.
	 * 
	 * @return result Le code hash de cet objet.
	 * 
	 * @since 1.0
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consultations == null) ? 0 : consultations.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lignesCommande == null) ? 0 : lignesCommande.hashCode());
		result = prime * result + prixHT;
		result = prime * result + prixTTC;
		result = prime * result + ((resume == null) ? 0 : resume.hashCode());
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		return result;
	}
	
	/**
	 * Produit la chaîne de caractères réprésentant l'article.
	 * On n'y intègre pas les consultations et les lignes de commandes associées.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "Article [id=" + id + ", titre=" + titre + ", resume=" + resume + ", prixHT=" + prixHT + ", prixTTC="
				+ prixTTC + "]";
	}



}
