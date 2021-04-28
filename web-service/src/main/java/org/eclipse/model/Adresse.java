package org.eclipse.model;

import java.util.ArrayList;

public class Adresse {
/** Classe pour les adresses*/

	// Attributs
	private int id;
	private String numeroRue;
	private String nomRue;
	private String codePostal;
	private String ville;
	private String pays;
	private String complement;
	private ArrayList<Editeur> editeurs = new ArrayList<Editeur>();
	private ArrayList<Client> clients = new ArrayList<Client>();
	
	// Constructeurs
	public Adresse() {
		super();
	}

	public Adresse(int id) {
		super();
		this.id = id;
	}

	public Adresse(String numeroRue, String nomRue, String codePostal, String ville, String pays, String complement,
			ArrayList<Editeur> editeurs, ArrayList<Client> clients) {
		super();
		this.numeroRue = numeroRue;
		this.nomRue = nomRue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
		this.complement = complement;
		this.editeurs = editeurs;
		this.clients = clients;
	}

	public Adresse(int id, String numeroRue, String nomRue, String codePostal, String ville, String pays,
			String complement, ArrayList<Editeur> editeurs, ArrayList<Client> clients) {
		super();
		this.id = id;
		this.numeroRue = numeroRue;
		this.nomRue = nomRue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
		this.complement = complement;
		this.editeurs = editeurs;
		this.clients = clients;
	}
	
	// Getters et Setters
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroRue() {
		return this.numeroRue;
	}

	public void setNumeroRue(String numeroRue) {
		this.numeroRue = numeroRue;
	}

	public String getNomRue() {
		return this.nomRue;
	}

	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}

	public String getCodePostal() {
		return this.codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return this.pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getComplement() {
		return this.complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public ArrayList<Editeur> getEditeurs() {
		return this.editeurs;
	}

	public void setEditeurs(ArrayList<Editeur> editeurs) {
		this.editeurs = editeurs;
	}

	public ArrayList<Client> getClients() {
		return this.clients;
	}

	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}
	
	// toString
	@Override
	public String toString() {
		return "Adresse [id=" + this.id + ", numeroRue=" + this.numeroRue + ", nomRue=" + this.nomRue + ", codePostal=" + this.codePostal
				+ ", ville=" + this.ville + ", pays=" + this.pays + ", complement=" + this.complement + ", editeurs=" + this.affichageEditeurs()
				+ ", clients=" + this.affichageClients() + "]";
	}

	// toString sans les objets liés
	public String toStringWithoutLinks() {
		// TODO Auto-generated method stub
		return null;
	}

	// Affichage des éditeurs associés sans information sur les objets qui y sont liés
	public String affichageEditeurs() {
		int nombreEditeurs = this.editeurs.size();
		if (nombreEditeurs > 0) {
			String stringEditeurs = "[";
			for (int i = 0; i < nombreEditeurs - 1; i++) {
				stringEditeurs += this.editeurs.get(i).toStringWithoutLinks() + ", ";
			}
			stringEditeurs += this.editeurs.get(nombreEditeurs - 1).toStringWithoutLinks() + "]";
			return stringEditeurs;
		}
		return "[]";
	}
	
	// Affichage des clients associés sans information sur les objets qui y sont liés
	public String affichageClients() {
		int nombreClients = this.clients.size();
		if (nombreClients > 0) {
			String stringClients = "[";
			for (int i = 0; i < nombreClients - 1; i++) {
				stringClients += this.clients.get(i).toStringWithoutLinks() + ", ";
			}
			stringClients += this.clients.get(nombreClients - 1).toStringWithoutLinks() + "]";
			return stringClients;
		}
		return "[]";
	}
	
}
