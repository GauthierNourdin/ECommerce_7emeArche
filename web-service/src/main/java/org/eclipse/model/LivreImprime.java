package org.eclipse.model;

import java.sql.Date;
import java.util.ArrayList;

public class LivreImprime extends Livre {
/** Classe pour les livres imprimés*/ 

	// Attributs
	private int id;
	private int quantiteStock;
	private Date dateFinTirage;
	private Date dateReimpression;
	private double poids;
	private String unitePoids;
	private double longueur;
	private double largeur;
	private double epaisseur;
	private String uniteLongueur;

	// Constructeurs
	public LivreImprime() {
		super();
	}

	public LivreImprime(int id) {
		super();
		this.id = id;
	}

	public LivreImprime(String titre, String resume, int prixHT, int prixTTC, ArrayList<Consultation> consultations,
			ArrayList<LigneCommande> lignesCommande, String isbn13, String titreLivre, String format, String lienImage,
			int nombrePages, Date dateDepotLegal, ArrayList<Genre> genres, ArrayList<Auteur> auteurs,
			Editeur editeurs, int quantiteStock, Date dateFinTirage, Date dateReimpression, double poids,
			String unitePoids, double longueur, double largeur, double epaisseur, String uniteLongueur) {
		super(titre, resume, prixHT, prixTTC, consultations, lignesCommande, isbn13, titreLivre, format, lienImage,
				nombrePages, dateDepotLegal, genres, auteurs, editeurs);
		this.quantiteStock = quantiteStock;
		this.dateFinTirage = dateFinTirage;
		this.dateReimpression = dateReimpression;
		this.poids = poids;
		this.unitePoids = unitePoids;
		this.longueur = longueur;
		this.largeur = largeur;
		this.epaisseur = epaisseur;
		this.uniteLongueur = uniteLongueur;
	}

	public LivreImprime(String titre, String resume, int prixHT, int prixTTC, ArrayList<Consultation> consultations,
			ArrayList<LigneCommande> lignesCommande, String isbn13, String titreLivre, String format, String lienImage,
			int nombrePages, Date dateDepotLegal, ArrayList<Genre> genres, ArrayList<Auteur> auteurs,
			Editeur editeurs, int id, int quantiteStock, Date dateFinTirage, Date dateReimpression, double poids,
			String unitePoids, double longueur, double largeur, double epaisseur, String uniteLongueur) {
		super(titre, resume, prixHT, prixTTC, consultations, lignesCommande, isbn13, titreLivre, format, lienImage,
				nombrePages, dateDepotLegal, genres, auteurs, editeurs);
		this.id = id;
		this.quantiteStock = quantiteStock;
		this.dateFinTirage = dateFinTirage;
		this.dateReimpression = dateReimpression;
		this.poids = poids;
		this.unitePoids = unitePoids;
		this.longueur = longueur;
		this.largeur = largeur;
		this.epaisseur = epaisseur;
		this.uniteLongueur = uniteLongueur;
	}

	// Getters et Setters
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantiteStock() {
		return this.quantiteStock;
	}

	public void setQuantiteStock(int quantiteStock) {
		this.quantiteStock = quantiteStock;
	}

	public Date getDateFinTirage() {
		return this.dateFinTirage;
	}

	public void setDateFinTirage(Date dateFinTirage) {
		this.dateFinTirage = dateFinTirage;
	}

	public Date getDateReimpression() {
		return this.dateReimpression;
	}

	public void setDateReimpression(Date dateReimpression) {
		this.dateReimpression = dateReimpression;
	}

	public double getPoids() {
		return this.poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public String getUnitePoids() {
		return this.unitePoids;
	}

	public void setUnitePoids(String unitePoids) {
		this.unitePoids = unitePoids;
	}

	public double getLongueur() {
		return this.longueur;
	}

	public void setLongueur(double longueur) {
		this.longueur = longueur;
	}

	public double getLargeur() {
		return this.largeur;
	}

	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

	public double getEpaisseur() {
		return this.epaisseur;
	}

	public void setEpaisseur(double epaisseur) {
		this.epaisseur = epaisseur;
	}

	public String getUniteLongueur() {
		return this.uniteLongueur;
	}

	public void setUniteLongueur(String uniteLongueur) {
		this.uniteLongueur = uniteLongueur;
	}

	// toString
	@Override
	public String toString() {
		return "LivreImprime [id=" + this.id + ", quantiteStock=" + this.quantiteStock + ", dateFinTirage=" + this.dateFinTirage
				+ ", dateReimpression=" + this.dateReimpression + ", poids=" + this.poids + ", unitePoids=" + this.unitePoids
				+ ", longueur=" + this.longueur + ", largeur=" + this.largeur + ", epaisseur=" + this.epaisseur + ", uniteLongueur="
				+ this.uniteLongueur + ", isbn13=" + this.getIsbn13() + ", titreLivre=" + this.getTitreLivre()
				+ ", format=" + this.getFormat() + ", lienImage=" + this.getLienImage() + ", nombrePages="
				+ this.getNombrePages() + ", dateDepotLegal=" + this.getDateDepotLegal()
				+ ", editeur=" + this.getEditeur() + ", auteurs=" + this.affichageAuteurs()
				+ ", genres=" + this.affichageGenres() + ", titre=" + this.getTitre() + ", resume="
				+ this.getResume() + ", prixHT=" + this.getPrixHT() + ", prixTTC=" + this.getPrixTTC()
				+ ", consultations=" + this.affichageConsultations() + ", lignesCommande="
				+ this.affichageLignesCommande() + "]";
	}
	
	// toString sans les objets associés
	@Override
	public String toStringWithoutLinks() {
		return "LivreImprime [id=" + this.id + ", quantiteStock=" + this.quantiteStock + ", dateFinTirage=" + this.dateFinTirage
				+ ", dateReimpression=" + this.dateReimpression + ", poids=" + this.poids + ", unitePoids=" + this.unitePoids
				+ ", longueur=" + this.longueur + ", largeur=" + this.largeur + ", epaisseur=" + this.epaisseur + ", uniteLongueur="
				+ this.uniteLongueur + ", isbn13=" + this.getIsbn13() + ", titreLivre=" + this.getTitreLivre()
				+ ", format=" + this.getFormat() + ", lienImage=" + this.getLienImage() + ", nombrePages="
				+ this.getNombrePages() + ", dateDepotLegal=" + this.getDateDepotLegal()
				+ ", genres=" + this.affichageGenres() + ", titre=" + this.getTitre() + ", resume="
				+ this.getResume() + ", prixHT=" + this.getPrixHT() + ", prixTTC=" + this.getPrixTTC()
				+ "]";
	}
	
}
