package org.eclipse.model;

import java.sql.Date;
import java.util.ArrayList;

public class LivreNumerique extends Livre {
/** Classe pour les livres numériques*/ 

	// Attributs
	private int id;
	private double espace;
	private String unite;
	
	// Constructeurs	
	public LivreNumerique() {
		super();
	}

	public LivreNumerique(int id) {
		super();
		this.id = id;
	}

	public LivreNumerique(String titre, String resume, int prixHT, int prixTTC, ArrayList<Consultation> consultations,
			ArrayList<LigneCommande> lignesCommande, String isbn13, String titreLivre, String format, String lienImage,
			int nombrePages, Date dateDepotLegal, ArrayList<Genre> genres, ArrayList<Auteur> auteurs,
			Editeur editeur, double espace, String unite) {
		super(titre, resume, prixHT, prixTTC, consultations, lignesCommande, isbn13, titreLivre, format, lienImage,
				nombrePages, dateDepotLegal, genres, auteurs, editeur);
		this.espace = espace;
		this.unite = unite;
	}

	public LivreNumerique(String titre, String resume, int prixHT, int prixTTC, ArrayList<Consultation> consultations,
			ArrayList<LigneCommande> lignesCommande, String isbn13, String titreLivre, String format, String lienImage,
			int nombrePages, Date dateDepotLegal, ArrayList<Genre> genres, ArrayList<Auteur> auteurs,
			Editeur editeur, int id, double espace, String unite) {
		super(titre, resume, prixHT, prixTTC, consultations, lignesCommande, isbn13, titreLivre, format, lienImage,
				nombrePages, dateDepotLegal, genres, auteurs, editeur);
		this.id = id;
		this.espace = espace;
		this.unite = unite;
	}

	// Getters et Setters
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getEspace() {
		return this.espace;
	}

	public void setEspace(double espace) {
		this.espace = espace;
	}

	public String getUnite() {
		return this.unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	// toString
	@Override
	public String toString() {
		return "LivreNumerique [id=" + this.id + ", espace=" + this.espace + ", unite=" + this.unite + ", isbn13=" + this.getIsbn13()
				+ ", titreLivre=" + this.getTitreLivre() + ", format=" + this.getFormat() + ", lienImage="
				+ this.getLienImage() + ", nombrePages=" + this.getNombrePages() 
				+ ", dateDepotLegal=" + this.getDateDepotLegal() + ", editeur=" + this.getEditeur()
				+ ", auteur=" + this.affichageAuteurs() + ", genres=" + this.affichageGenres()
				+ ", titre=" + this.getTitre() + ", resume=" + this.getResume() + ", prixHT=" + this.getPrixHT()
				+ ", prixTTC=" + this.getPrixTTC() + ", consultations=" + this.affichageConsultations()
				+ ", lignesCommande=" + this.affichageLignesCommande() + "]";
	}
	
	@Override
	// toString sans les objets associés
	public String toStringWithoutLinks() {
		return "LivreNumerique [id=" + this.id + ", espace=" + this.espace + ", unite=" + this.unite + ", isbn13=" + this.getIsbn13()
		+ ", titreLivre=" + this.getTitreLivre() + ", format=" + this.getFormat() + ", lienImage="
		+ this.getLienImage() + ", nombrePages=" + this.getNombrePages() 
		+ ", dateDepotLegal=" + this.getDateDepotLegal() + ", genres=" + this.affichageGenres()
		+ ", titre=" + this.getTitre() + ", resume=" + this.getResume() + ", prixHT=" + this.getPrixHT()
		+ ", prixTTC=" + this.getPrixTTC() + "]";
	}
}
