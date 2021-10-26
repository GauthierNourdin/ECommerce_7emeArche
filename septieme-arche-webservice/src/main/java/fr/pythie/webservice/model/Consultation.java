package fr.pythie.webservice.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/** 
 * Classe modèle pour les consultations d'articles.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Consultation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	LocalDateTime dateEnregistrement = LocalDateTime.now();
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Client client;
	@NonNull
	@JsonIgnoreProperties( "consultations" )
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Article article;

	/**
	 * Constructeur permettant de construire une consultation vide.
	 * 
	 * @since 1.0
	 */
	public Consultation() {
		super();
	}
	
	/**
	 * Constructeur permettant de construire une consultation avec les informations obligatoires.
	 * 
	 * @param article L'article dont la page a été consultée.
	 * 
	 * @since 1.0
	 */
	public Consultation(@NonNull Article article) {
		super();
		this.article = article;
	}
	
	/**
	 * Constructeur permettant de construire une consultation complète mais sans identifiant.
	 * 
	 * @param dateEnregistrement La date d'enregistrement.
	 * @param client Le client ayant fait cette consultation.
	 * @param article L'article dont la page a été consultée.
	 * 
	 * @since 1.0
	 */
	public Consultation(@NonNull LocalDateTime dateEnregistrement, Client client, @NonNull Article article) {
		super();
		this.dateEnregistrement = dateEnregistrement;
		this.client = client;
		this.article = article;
	}

	/**
	 * Constructeur permettant de construire une consultation complète.
	 * 
	 * @param id L'identifiant.
	 * @param dateEnregistrement La date d'enregistrement.
	 * @param client Le client ayant fait cette consultation.
	 * @param article L'article dont la page a été consultée.
	 * 
	 * @since 1.0
	 */
	public Consultation(Long id, LocalDateTime dateEnregistrement, Client client, @NonNull Article article) {
		super();
		this.id = id;
		this.dateEnregistrement = dateEnregistrement;
		this.client = client;
		this.article = article;
	}

	/**
	 * Retourne l'identifiant.
	 * 
	 * @return id L'identifiant.
	 * 
	 * @see Consultation#setId(long)
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
	 * @see Consultation#getId()
	 * 
	 * @since 1.0
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retourne la date d'enregistrement.
	 * 
	 * @return dateEnregistrement La date d'enregistrement.
	 * 
	 * @see Consultation#setDateEnregistrement(LocalDateTime)
	 * 
	 * @since 1.0
	 */
	public LocalDateTime getDateEnregistrement() {
		return dateEnregistrement;
	}

	/**
	 * Modifie la date d'enregistrement.
	 * 
	 * @param dateEnregistrement La nouvelle date d'enregistrement.
	 * 
	 * @see Consultation#getDateEnregistrement()
	 * 
	 * @since 1.0
	 */
	public void setDateEnregistrement(LocalDateTime dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}

	/**
	 * Retourne le client ayant fait cette consultation.
	 * 
	 * @return client Le client ayant fait cette consultation.
	 * 
	 * @see Consultation#setClient(Client)
	 * 
	 * @since 1.0
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Modifie le client ayant fait cette consultation.
	 * 
	 * @param client Le nouveau client ayant fait cette consultation.
	 * 
	 * @see Consultation#getClient()
	 * 
	 * @since 1.0
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Retourne l'article dont la page a été consultée.
	 * 
	 * @return article L'article dont la page a été consultée.
	 * 
	 * @see Consultation#setArticle(Article)
	 * 
	 * @since 1.0
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * Modifie l'article dont la page a été consultée.
	 * 
	 * @param article Le nouvel article dont la page a été consultée.
	 * 
	 * @see Consultation#getArticle()
	 * 
	 * @since 1.0
	 */
	public void setArticle(Article article) {
		this.article = article;
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
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((dateEnregistrement == null) ? 0 : dateEnregistrement.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/**
	 * Définit les conditions dans lesquelles un autre objet est égal à cette consultation.
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
		Consultation other = (Consultation) obj;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (dateEnregistrement == null) {
			if (other.dateEnregistrement != null)
				return false;
		} else if (!dateEnregistrement.equals(other.dateEnregistrement))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * Produit la chaîne de caractères réprésentant la commande.
	 * On n'y intègre pas le client et l'article associés.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "Consultation [id=" + id + ", dateEnregistrement=" + dateEnregistrement + "]";
	}
	
}
