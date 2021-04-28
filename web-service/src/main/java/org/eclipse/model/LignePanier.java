package org.eclipse.model;

public class LignePanier {
	/** Classe pour les lignes de panier*/

	// Attributs
	private int id;
	private int quantiteSouhaitee;
	private Article article;
	private Panier panier;

	// Constructeurs
	public LignePanier() {
		super();
	}

	public LignePanier(int id) {
		super();
		this.id = id;
	}

	public LignePanier(int quantiteSouhaitee, Article article, Panier panier) {
		super();
		this.quantiteSouhaitee = quantiteSouhaitee;
		this.article = article;
		this.panier = panier;
	}

	public LignePanier(int id, int quantiteSouhaitee, Article article, Panier panier) {
		super();
		this.id = id;
		this.quantiteSouhaitee = quantiteSouhaitee;
		this.article = article;
		this.panier = panier;
	}

	// Getters et Setters
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantiteSouhaitee() {
		return this.quantiteSouhaitee;
	}

	public void setQuantiteSouhaitee(int quantiteSouhaitee) {
		this.quantiteSouhaitee = quantiteSouhaitee;
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Panier getPanier() {
		return this.panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	// toString	
	@Override
	public String toString() {
		return "LignePanier [id=" + this.id + ", quantiteSouhaitee=" + this.quantiteSouhaitee + ", article=" + this.article.toStringWithoutLinks()
				+ "]";
	}

	// toString sans les objets associés
	public String toStringWithoutLinks() {
		return "LignePanier [id=" + this.id + ", quantiteSouhaitee=" + this.quantiteSouhaitee + "]";
	}
}
