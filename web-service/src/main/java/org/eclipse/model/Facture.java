package org.eclipse.model;

public class Facture {
/** Classe pour les factures*/

	// Attributs
	private String numero;
	private Commande commande = new Commande();
	
	// Constructeurs
	public Facture() {
		super();
	}
	
	public Facture(String numero) {
		super();
		this.numero = numero;
	}
	
	public Facture(Commande commande) {
		super();
		this.commande = commande;
	}
	
	public Facture(String numero, Commande commande) {
		super();
		this.numero = numero;
		this.commande = commande;
	}

	// Getters et Setters
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	// toString
	@Override
	public String toString() {
		return "Facture [numero=" + this.numero + ", commande=" + this.commande + "]";
	}
	
	// toString sans les objets associés
	public String toStringWithoutLinks() {
		return "Facture [numero=" + this.numero + "]";
	}
}
