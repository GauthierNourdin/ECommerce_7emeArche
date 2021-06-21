package org.eclipse.model;

public class Facture {
	/** Classe pour les factures */

	// Attributs
	private String numero;
	private String status;
	private Commande commande = null;

	// Constructeurs
	public Facture() {
		super();
	}

	public Facture(String numero) {
		super();
		this.numero = numero;
	}

	public Facture(String status, Commande commande) {
		super();
		this.status = status;
		this.commande = commande;
	}

	public Facture(String numero, String status, Commande commande) {
		super();
		this.numero = numero;
		this.status = status;
		this.commande = commande;
	}

	// Getters et Setters
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return "Facture [numero=" + this.numero + ", status=" + this.status + ", commande="
				+ this.commande.toStringWithoutLinks() + "]";
	}

	// toString sans les objets associés
	public String toStringWithoutLinks() {
		return "Facture [numero=" + this.numero + ", status=" + this.status + "]";
	}
}
