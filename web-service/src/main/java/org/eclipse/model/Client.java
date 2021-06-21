package org.eclipse.model;

import java.util.ArrayList;

public class Client extends Personne {

	// Attributs
	private int id;
	private String email;
	private String motDePasse;
	private String numeroCarte;
	private String dateDeValidite;
	private String cvc;
	private Adresse adresseFacturation = null;
	private Adresse adresseLivraison = null;
	private Panier panier = null;
	private ArrayList<Consultation> consultations = new ArrayList<Consultation>();
	private ArrayList<Commande> commandes = new ArrayList<Commande>();

	// Constructeurs
	public Client() {
		super();
	}

	public Client(int id) {
		super();
		this.id = id;
	}

	public Client(String nom, String prenom, String email, String motDePasse, String numeroCarte, String dateDeValidite,
			String cvc, Adresse adresseFacturation, Adresse adresseLivraison, Panier panier,
			ArrayList<Consultation> consultations, ArrayList<Commande> commandes) {
		super(nom, prenom);
		this.email = email;
		this.motDePasse = motDePasse;
		this.numeroCarte = numeroCarte;
		this.dateDeValidite = dateDeValidite;
		this.cvc = cvc;
		this.adresseFacturation = adresseFacturation;
		this.adresseLivraison = adresseLivraison;
		this.panier = panier;
		this.consultations = consultations;
		this.commandes = commandes;
	}

	public Client(String nom, String prenom, int id, String email, String motDePasse, String numeroCarte,
			String dateDeValidite, String cvc, Adresse adresseFacturation, Adresse adresseLivraison, Panier panier,
			ArrayList<Consultation> consultations, ArrayList<Commande> commandes) {
		super(nom, prenom);
		this.id = id;
		this.email = email;
		this.motDePasse = motDePasse;
		this.numeroCarte = numeroCarte;
		this.dateDeValidite = dateDeValidite;
		this.cvc = cvc;
		this.adresseFacturation = adresseFacturation;
		this.adresseLivraison = adresseLivraison;
		this.panier = panier;
		this.consultations = consultations;
		this.commandes = commandes;
	}

	// Getters et Setters
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return this.motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getNumeroCarte() {
		return this.numeroCarte;
	}

	public void setNumeroCarte(String numeroCarte) {
		this.numeroCarte = numeroCarte;
	}

	public String getDateDeValidite() {
		return this.dateDeValidite;
	}

	public void setDateDeValidite(String dateDeValidite) {
		this.dateDeValidite = dateDeValidite;
	}

	public String getCvc() {
		return this.cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public Adresse getAdresseFacturation() {
		return this.adresseFacturation;
	}

	public void setAdresseFacturation(Adresse adresseFacturation) {
		this.adresseFacturation = adresseFacturation;
	}

	public Adresse getAdresseLivraison() {
		return this.adresseLivraison;
	}

	public void setAdresseLivraison(Adresse adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}

	public Panier getPanier() {
		return this.panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public ArrayList<Consultation> getConsultations() {
		return this.consultations;
	}

	public void setConsultations(ArrayList<Consultation> consultations) {
		this.consultations = consultations;
	}

	public ArrayList<Commande> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(ArrayList<Commande> commandes) {
		this.commandes = commandes;
	}

	// toString
	@Override
	public String toString() {
		return "Client [id=" + this.id + ", prenom=" + this.getPrenom() + ", nom=" + this.getNom() + ", email="
				+ this.email + ", motDePasse=" + this.motDePasse + ", numeroCarte=" + this.numeroCarte
				+ ", dateDeValidite=" + this.dateDeValidite + ", cvc=" + this.cvc + ", adresseFacturation="
				+ this.adresseFacturation + ", adresseLivraison="
				+ this.adresseLivraison + ", panier=" + this.panier.toStringWithoutClient()
				+ ", consultations=" + this.affichageConsultations() + ", commandes=" + this.affichageCommandes() + "]";
	}

	// toString sans les autres objets
	public String toStringWithoutLinks() {
		return "Client [id=" + this.id + ", prenom=" + this.getPrenom() + ", nom=" + this.getNom() + ", email="
				+ this.email + ", motDePasse=" + this.motDePasse + ", numeroCarte=" + this.numeroCarte
				+ ", dateDeValidite=" + this.dateDeValidite + ", cvc=" + this.cvc + "]";
	}

	// Affichage des consultations sans information sur les objets qui y sont liés
	public String affichageConsultations() {
		int nombreConsultations = this.consultations.size();
		if (nombreConsultations > 0) {
			String stringConsultations = "[";
			for (int i = 0; i < nombreConsultations - 1; i++) {
				stringConsultations += this.consultations.get(i).toStringWithoutLinks() + ", ";
			}
			stringConsultations += this.consultations.get(nombreConsultations - 1).toStringWithoutLinks() + "]";
			return stringConsultations;
		}
		return "[]";
	}

	// Affichage des commandes sans information sur les objets qui y sont liés
	public String affichageCommandes() {
		int nombreCommandes = this.commandes.size();
		if (nombreCommandes > 0) {
			String stringCommandes = "[";
			for (int i = 0; i < nombreCommandes - 1; i++) {
				stringCommandes += this.commandes.get(i).toStringWithoutLinks() + ", ";
			}
			stringCommandes += this.commandes.get(nombreCommandes - 1).toStringWithoutLinks() + "]";
			return stringCommandes;
		}
		return "[]";
	}

}
