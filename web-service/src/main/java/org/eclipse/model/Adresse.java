package org.eclipse.model;

import java.util.ArrayList;

public class Adresse {
	/** Classe pour les adresses */

	// Attributs
	private int id;
	private String numeroRue;
	private String nomRue;
	private String codePostal;
	private String ville;
	private String pays;
	private String complement;

	// Constructeurs
	public Adresse() {
		super();
	}

	public Adresse(int id) {
		super();
		this.id = id;
	}

	public Adresse(String numeroRue, String nomRue, String codePostal, String ville, String pays, String complement) {
		super();
		this.numeroRue = numeroRue;
		this.nomRue = nomRue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
		this.complement = complement;
	}

	public Adresse(int id, String numeroRue, String nomRue, String codePostal, String ville, String pays,
			String complement) {
		super();
		this.id = id;
		this.numeroRue = numeroRue;
		this.nomRue = nomRue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
		this.complement = complement;
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

	// toString
	@Override
	public String toString() {
		return "Adresse [id=" + this.id + ", numeroRue=" + this.numeroRue + ", nomRue=" + this.nomRue + ", codePostal="
				+ this.codePostal + ", ville=" + this.ville + ", pays=" + this.pays + ", complement=" + this.complement
				+ "]";
	}

}
