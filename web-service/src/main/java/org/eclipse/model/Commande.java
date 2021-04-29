package org.eclipse.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Commande {
	/** Classe pour les commandes */

	// Attributs
	private String numero;
	private String status;
	private LocalDateTime date;
	private ArrayList<LigneCommande> lignesCommande = new ArrayList<LigneCommande>();
	private Client client = null;
	private Facture facture = null;

	// Constructeurs
	public Commande() {
		super();
	}

	public Commande(String numero) {
		super();
		this.numero = numero;
	}

	public Commande(String status, LocalDateTime date, ArrayList<LigneCommande> lignesCommande, Client client, Facture facture) {
		super();
		this.status = status;
		this.date = date;
		this.lignesCommande = lignesCommande;
		this.client = client;
		this.facture = facture;
	}

	public Commande(String numero, String status, LocalDateTime date, ArrayList<LigneCommande> lignesCommande, Client client,
			Facture facture) {
		super();
		this.numero = numero;
		this.status = status;
		this.date = date;
		this.lignesCommande = lignesCommande;
		this.client = client;
		this.facture = facture;
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

	public LocalDateTime getDate() {
		return this.date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public ArrayList<LigneCommande> getLignesCommande() {
		return this.lignesCommande;
	}

	public void setLignesCommande(ArrayList<LigneCommande> lignesCommande) {
		this.lignesCommande = lignesCommande;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Facture getFacture() {
		return this.facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	/*
	 * Méthode pour construire le numéro de commande à partir des autres
	 * informations. On veut que le numéro contienne douze chiffres permettant de
	 * reconstruire la date et l'heure du passage ainsi que 8 chiffres pour l'id du
	 * client. Les deux groupes sont séparés par un tiret
	 */
	public void creatingNumero() {
		String stringNumero;
		DateTimeFormatter formatNumero = DateTimeFormatter.ofPattern("yyMMddHHmmss");
		stringNumero = date.format(formatNumero) + "-"
				+ String.format("%8s", String.valueOf(client.getId())).replace(' ', '0');
		this.setNumero(stringNumero);
	}

	// toString
	@Override
	public String toString() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return "Commande [numero=" + this.numero + ", status=" + this.status + ", date=" + this.date.format(format) + ", lignesCommande="
				+ this.affichageLignesCommande() + ", client=" + this.client.toStringWithoutLinks() + ", facture=" + this.facture.toStringWithoutLinks() + "]";
	}

	// toString sans les objets associés
	public String toStringWithoutLinks() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return "Commande [numero=" + this.numero + ", status=" + this.status + ", date=" + this.date.format(format) + "]";
	}

	// Affichage des lignes de commandes associées sans information sur les objets
	// qui y sont liés
	public String affichageLignesCommande() {
		int nombreLignesCommande = this.lignesCommande.size();
		if (nombreLignesCommande > 0) {
			String stringLignesCommande = "[";
			for (int i = 0; i < nombreLignesCommande - 1; i++) {
				stringLignesCommande += this.lignesCommande.get(i).toStringWithoutLinks() + ", ";
			}
			stringLignesCommande += this.lignesCommande.get(nombreLignesCommande - 1).toStringWithoutLinks() + "]";
			return stringLignesCommande;
		}
		return "[]";
	}
}
