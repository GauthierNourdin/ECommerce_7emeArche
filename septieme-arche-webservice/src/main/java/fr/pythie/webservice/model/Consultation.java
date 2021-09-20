package fr.pythie.webservice.model;

import java.time.LocalDateTime;

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

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Consultation {
	/** 
	 * Classe pour les consultations d'articles.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	LocalDateTime dateEnregistrement = LocalDateTime.now();
	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Client client;
	@NonNull
	@JsonIgnoreProperties( "consultations" )
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Article article;

	/**
	 * 
	 */
	public Consultation() {
		super();
	}
	
	/**
	 * 
	 * @param article
	 */
	public Consultation(@NonNull Article article) {
		super();
		this.article = article;
	}
	
	/**
	 *  Constructeur complet mis à part l'ID
	 * @param dateEnregistrement
	 * @param client
	 * @param article
	 */
	public Consultation(@NonNull LocalDateTime dateEnregistrement, Client client, @NonNull Article article) {
		super();
		this.dateEnregistrement = dateEnregistrement;
		this.client = client;
		this.article = article;
	}

	/**
	 * 
	 * @param id
	 * @param dateEnregistrement
	 * @param client
	 * @param article
	 */
	public Consultation(long id, LocalDateTime dateEnregistrement, Client client, @NonNull Article article) {
		super();
		this.id = id;
		this.dateEnregistrement = dateEnregistrement;
		this.client = client;
		this.article = article;
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
	public LocalDateTime getDateEnregistrement() {
		return dateEnregistrement;
	}

	/**
	 * 
	 * @param dateEnregistrement
	 */
	public void setDateEnregistrement(LocalDateTime dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}

	/**
	 * 
	 * @return
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * 
	 * @param client
	 */
	public void setClient(Client client) {
		this.client = client;
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
	 * 
	 */
	@Override
	public String toString() {
		return "Consultation [id=" + id + ", dateEnregistrement=" + dateEnregistrement + "]";
	}
	
}
