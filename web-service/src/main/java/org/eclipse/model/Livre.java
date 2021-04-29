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
	private Date dateDepotLegal;
	private ArrayList<Genre> genres = new ArrayList<Genre>();
	private ArrayList<Auteur> auteurs = new ArrayList<Auteur>();
	private Editeur editeur = null;
	
	// Constructeurs
	public Livre() {
		super();
	}

	public Livre(String titre, String resume, int prixHT, int prixTTC, ArrayList<Consultation> consultations,
			ArrayList<LigneCommande> lignesCommande, String isbn13, String titreLivre, String format, String lienImage,
			int nombrePages, Date dateDepotLegal, ArrayList<Genre> genres, ArrayList<Auteur> auteurs,
			Editeur editeur) {
		super(titre, resume, prixHT, prixTTC, consultations, lignesCommande);
		this.isbn13 = isbn13;
		this.titreLivre = titreLivre;
		this.format = format;
		this.lienImage = lienImage;
		this.nombrePages = nombrePages;
		this.dateDepotLegal = dateDepotLegal;
		this.genres = genres;
		this.auteurs = auteurs;
		this.editeur = editeur;
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

	public Editeur getEditeur() {
		return this.editeur;
	}

	public void setEditeur(Editeur editeurs) {
		this.editeur = editeurs;
	}

	// toString
	@Override
	public String toString() {
		return "Livre [titre=" + this.getTitre() + ", resume=" + this.getResume() + ", prixHT=" + this.getPrixHT() + ", prixTTC=" + this.getPrixTTC()
				+ ", consultations=" + this.affichageConsultations() + ", lignesCommande=" + this.affichageLignesCommande() + ", isbn13=" + this.isbn13 + ", titreLivre=" + this.titreLivre + ", format=" + this.format + ", lienImage="
				+ this.lienImage + ", nombrePages=" + this.nombrePages + ", dateDepotLegal=" + this.dateDepotLegal
				+ ", genres=" + affichageGenres() + ", auteurs=" + affichageAuteurs() + ", editeurs=" + this.editeur.toStringWithoutLinks() + "]";
	}

	// toString sans les autres objets
	@Override
	public String toStringWithoutLinks() {
		return "Livre [titre=" + this.getTitre() + ", resume=" + this.getResume() + ", prixHT=" + this.getPrixHT() + ", prixTTC=" + this.getPrixTTC()
				+ ", isbn13=" + this.isbn13 + ", titreLivre=" + this.titreLivre + ", format=" + this.format + ", lienImage="
				+ this.lienImage + ", nombrePages=" + this.nombrePages + ", dateDepotLegal=" + this.dateDepotLegal
				+ "]";
	}
	
	// Affichage des auteurs associés sans information sur les objets qui y sont liés
	public String affichageAuteurs() {
		int nombreAuteurs = this.auteurs.size();
		if (nombreAuteurs > 0) {
			String stringAuteurs = "[";
			for (int i = 0; i < nombreAuteurs - 1; i++) {
				stringAuteurs += this.auteurs.get(i).toStringWithoutLinks() + ", ";
			}
			stringAuteurs += this.auteurs.get(nombreAuteurs - 1).toStringWithoutLinks() + "]";
			return stringAuteurs;
		}
		return "[]";
	}
	
	// Affichage des genres associés sans information sur les objets qui y sont liés
	public String affichageGenres() {
		int nombreGenres = this.genres.size();
		if (nombreGenres > 0) {
			String stringGenres = "[";
			for (int i = 0; i < nombreGenres - 1; i++) {
				stringGenres += this.genres.get(i).toStringWithoutLinks() + ", ";
			}
			stringGenres += this.genres.get(nombreGenres - 1).toStringWithoutLinks() + "]";
			return stringGenres;
		}
		return "[]";
	}
}
