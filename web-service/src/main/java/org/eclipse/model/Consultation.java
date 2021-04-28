package org.eclipse.model;

import java.time.LocalDateTime;

public class Consultation {
/** Classe pour les consultations d'articles*/
	
	// Attributs
	private int id;
	private LocalDateTime date;
	private Client client = new Client();
	private Article article;
	
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
		return "Consultation [id=" + this.id + ", date=" + this.date + ", client=" + this.client + ", article=" + this.article + "]";
	}

	// toString sans les objets associés
	public String toStringWithoutLinks() {
		return "Consultation [id=" + this.id + ", date=" + this.date + "]";
	}
}
