package org.eclipse.model;

public class LigneCommande {
/** Classe pour les lignes de commande*/
	
	// Attributs
	private int id;
	private int quantiteCommandee;
	private int prixHT;
	private int prixTTC;
	private Article article;
	private Commande commande;
	
	// Constructeurs
	public LigneCommande() {
		super();
	}
	
	public LigneCommande(int id) {
		super();
		this.id = id;
	}

	public LigneCommande(int quantiteCommandee, int prixHT, int prixTTC, Article article, Commande commande) {
		super();
		this.quantiteCommandee = quantiteCommandee;
		this.prixHT = prixHT;
		this.prixTTC = prixTTC;
		this.article = article;
		this.commande = commande;
	}

	public LigneCommande(int id, int quantiteCommandee, int prixHT, int prixTTC, Article article, Commande commande) {
		super();
		this.id = id;
		this.quantiteCommandee = quantiteCommandee;
		this.prixHT = prixHT;
		this.prixTTC = prixTTC;
		this.article = article;
		this.commande = commande;
	}

	// Getters et Setters
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantiteCommandee() {
		return this.quantiteCommandee;
	}

	public void setQuantiteCommandee(int quantiteCommandee) {
		this.quantiteCommandee = quantiteCommandee;
	}

	public int getPrixHT() {
		return this.prixHT;
	}

	public void setPrixHT(int prixHT) {
		this.prixHT = prixHT;
	}

	public int getPrixTTC() {
		return this.prixTTC;
	}

	public void setPrixTTC(int prixTTC) {
		this.prixTTC = prixTTC;
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Commande getCommande() {
		return this.commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	// toString
	@Override
	public String toString() {
		return "LigneCommande [id=" + this.id + ", quantiteCommandee=" + this.quantiteCommandee + ", prixHT=" + this.prixHT
				+ ", prixTTC=" + this.prixTTC + ", article=" + this.article.toStringWithoutLinks() + ", commande=" + this.commande.toStringWithoutLinks() + "]";
	}

	// toString sans les objets associés
	public String toStringWithoutLinks() {
		return "LigneCommande [id=" + this.id + ", quantiteCommandee=" + this.quantiteCommandee + ", prixHT=" + this.prixHT
				+ ", prixTTC=" + this.prixTTC + "]";
	}
}
