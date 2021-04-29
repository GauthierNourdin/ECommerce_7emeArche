package org.eclipse.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consultation {
/** Classe pour les consultations d'articles*/
	
	// Attributs
	private int id;
	private LocalDateTime date;
	private Client client = null;
	private Article article = null;
	
	// Constructeurs
	public Consultation() {
		super();
	}

	public Consultation(int id) {
		super();
		this.id = id;
	}

	public Consultation(LocalDateTime date, Client client, Article article) {
		super();
		this.date = date;
		this.client = client;
		this.article = article;
	}

	public Consultation(int id, LocalDateTime date, Client client, Article article) {
		super();
		this.id = id;
		this.date = date;
		this.client = client;
		this.article = article;
	}
	
	// Getters et Setters
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return this.date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	// toString
	@Override
	public String toString() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return "Consultation [id=" + this.id + ", date=" + this.date.format(format) + ", client=" + this.client.toStringWithoutLinks() + ", article=" + this.article.toStringWithoutLinks() + "]";
	}

	// toString sans les objets associés
	public String toStringWithoutLinks() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return "Consultation [id=" + this.id + ", date=" + this.date.format(format) + "]";
	}
}
