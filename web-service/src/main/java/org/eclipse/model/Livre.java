package org.eclipse.model;

import java.sql.Date;
import java.util.ArrayList;

public abstract class Livre extends Article {
/** Classe abstraite servent de base à tous les livres*/

	// Attributs
	private String isbn13;
	private String titreLivre;
	private String format;
	private String lienImage;
	private int nombrePages;
	private String issn;
	private Date dateDepotLegal;
	private ArrayList<Genre> genres;
	private ArrayList<Auteur> auteurs;
	private Editeur editeurs;
	
	// Constructeurs
	public Livre() {
		super();
	}

	public Livre(String titre, String resume, int prixHT, int prixTTC, ArrayList<Consultation> consultations,
			ArrayList<LigneCommande> lignesCommande, String isbn13, String titreLivre, String format, String lienImage,
			int nombrePages, String issn, Date dateDepotLegal, ArrayList<Genre> genres, ArrayList<Auteur> auteurs,
			Editeur editeurs) {
		super(titre, resume, prixHT, prixTTC, consultations, lignesCommande);
		this.isbn13 = isbn13;
		this.titreLivre = titreLivre;
		this.format = format;
		this.lienImage = lienImage;
		this.nombrePages = nombrePages;
		this.issn = issn;
		this.dateDepotLegal = dateDepotLegal;
		this.genres = genres;
		this.auteurs = auteurs;
		this.editeurs = editeurs;
	}

	// Getters et Setters
	public String getIsbn13() {
		return this.isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getTitreLivre() {
		return this.titreLivre;
	}

	public void setTitreLivre(String titreLivre) {
		this.titreLivre = titreLivre;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getLienImage() {
		return this.lienImage;
	}

	public void setLienImage(String lienImage) {
		this.lienImage = lienImage;
	}

	public int getNombrePages() {
		return this.nombrePages;
	}

	public void setNombrePages(int nombrePages) {
		this.nombrePages = nombrePages;
	}

	public String getIssn() {
		return this.issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public Date getDateDepotLegal() {
		return this.dateDepotLegal;
	}

	public void setDateDepotLegal(Date dateDepotLegal) {
		this.dateDepotLegal = dateDepotLegal;
	}

	public ArrayList<Genre> getGenres() {
		return this.genres;
	}

	public void setGenres(ArrayList<Genre> genres) {
		this.genres = genres;
	}

	public ArrayList<Auteur> getAuteurs() {
		return this.auteurs;
	}

	public void setAuteurs(ArrayList<Auteur> auteurs) {
		this.auteurs = auteurs;
	}

	public Editeur getEditeurs() {
		return this.editeurs;
	}

	public void setEditeurs(Editeur editeurs) {
		this.editeurs = editeurs;
	}

	// toString
	@Override
	public String toString() {
		return "Livre [titre=" + this.getTitre() + ", resume=" + this.getResume() + ", prixHT=" + this.getPrixHT() + ", prixTTC=" + this.getPrixTTC()
				+ ", consultations=" + this.affichageConsultations() + ", lignesCommande=" + this.affichageLignesCommande() + ", isbn13=" + this.isbn13 + ", titreLivre=" + this.titreLivre + ", format=" + this.format + ", lienImage="
				+ this.lienImage + ", nombrePages=" + this.nombrePages + ", issn=" + this.issn + ", dateDepotLegal=" + this.dateDepotLegal
				+ ", genres=" + this.genres + ", auteurs=" + this.auteurs + ", editeurs=" + this.editeurs + "]";
	}

	// toString sans information client	
	public String toStringWithoutLinks() {
		
	}
}
