package fr.pythie.webservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
	Long id;
	@NonNull
	String titre;
	@NonNull
	String resume;
	int prixHT;
	int prixTTC;
	@JsonIgnore
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
	List<Consultation> consultations;
	@JsonIgnore
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
	List<LigneCommande> lignesCommande;

	/** 
	 * Constructeur permettant de construire un article vide.
	 * 
	 * @since 1.0
	 */
	public Article() {
		super();
	}
	
	/**
	 * Constructeur permettant de construire un article avec les informations obligatoires.
	 * 
	 * @param titre Le titre général.
	 * @param resume Le résumé.
	 * 
	 * @since 1.0
	 */
	public Article(@NonNull String titre, @NonNull String resume) {
		super();
		this.titre = titre;
		this.resume = resume;
	}
	
	/** 
	 * Constructeur permettant de construire un article complet mais sans identifiant.
	 * 
	 * @param titre Le titre général.
	 * @param resume Le résumé.
	 * @param prixHT Le prix unitaire hors taxe multiplié par 100.
	 * @param prixTTC Le prix unitaire toutes taxes comprises multiplié par 100.
	 * @param consultations La liste de consultations de la page de cet article.
	 * @param lignesCommande La liste des lignes de commande associée à cet article.
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
	 * Constructeur permettant de construire un article complet
	 * 
	 * @param id L'identifiant.
	 * @param titre Le titre général.
	 * @param resume Le résumé.
	 * @param prixHT Le prix unitaire hors taxe multiplié par 100.
	 * @param prixTTC Le prix unitaire toutes taxes comprises multiplié par 100.
	 * @param consultations La liste de consultations de la page de cet article.
	 * @param lignesCommande La liste des lignes de commande associée à cet article.
	 * 
	 * @since 1.0
	 */
	public Article(Long id, @NonNull String titre, @NonNull String resume, int prixHT, int prixTTC,
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
	 * Retourne l'identifiant.
	 * 
	 * @return id L'identifiant.
	 * 
	 * @see Article#setId(long)
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
	 * @see Article#getId()
	 * 
	 * @since 1.0
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retourne le titre général.
	 * 
	 * @return titre Le titre général.
	 * 
	 * @see Article#setTitre(String)
	 * 
	 * @since 1.0
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * Modifie le titre général.
	 * 
	 * @param titre Le nouveau titre général.
	 * 
	 * @see Article#getTitre()
	 * 
	 * @since 1.0
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * Retourne le résumé.
	 * 
	 * @return resume Le résumé.
	 * 
	 * @see Article#setResume(String)
	 * 
	 * @since 1.0
	 */
	public String getResume() {
		return resume;
	}

	/**
	 * Modifie le résumé.
	 * 
	 * @param resume Le nouveau résumé.
	 * 
	 * @see Article#getResume()
	 * 
	 * @since 1.0
	 */
	public void setResume(String resume) {
		this.resume = resume;
	}

	/**
	 * Retourne le prix unitaire hors taxe.
	 * 
	 * @return prixHT Le prix unitiare hors taxe.
	 * 
	 * @see Article#setPrixHT(int)
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
	 * @see Article#getPrixHT()
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
	 * @see Article#setPrixTTC(int)
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
	 * @see Article#getPrixTTC()
	 * 
	 * @since 1.0
	 */
	public void setPrixTTC(int prixTTC) {
		this.prixTTC = prixTTC;
	}

	/**
	 * Retourne la liste des consultations associée à cet article.
	 * 
	 * @return consultations La liste des consultations associée à cet article.
	 * 
	 * @see Article#setConsultations(List)
	 * 
	 * @since 1.0
	 */
	public List<Consultation> getConsultations() {
		return consultations;
	}

	/**
	 * Modifie la liste des consultations associée à cet article.
	 * 
	 * @param consultations La nouvelle liste des consultations associée à cet article.
	 * 
	 * @see Article#getConsultations()
	 * 
	 * @since 1.0
	 */
	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}

	/** 
	 * Retourne la liste des lignes de commande associée à cet article.
	 * 
	 * @return lignesCommande La liste des lignes de commande associée à cet article.
	 * 
	 * @see Article#setLignesCommande(List)
	 * 
	 * @since 1.0
	 */
	public List<LigneCommande> getLignesCommande() {
		return lignesCommande;
	}

	/**
	 * Modifie la liste des lignes de commande associée à cet article.
	 * 
	 * @param lignesCommande La nouvelle liste des lignes de commande associée à cet article.
	 * 
	 * @see Article#getLignesCommande()
	 * 
	 * @since 1.0
	 */
	public void setLignesCommande(List<LigneCommande> lignesCommande) {
		this.lignesCommande = lignesCommande;
	}

	/**
	 * Définit les conditions dans lesquelles un autre objet est égal à cet article.
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
