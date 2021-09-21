package fr.pythie.webservice.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/** 
 * Classe modèle pour les livres imprimés.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@JsonDeserialize(as = LivreImprime.class)
public class LivreImprime extends Livre {

	int quantiteStock;
	@NonNull
	LocalDate dateFinTirage = LocalDate.now();
	LocalDate dateReimpression = LocalDate.now();
	double poids;
	String unitePoids;
	double longueur;
	double largeur;
	double epaisseur;
	String uniteLongueur;

	/**
	 * 
	 */
	public LivreImprime() {
		super();
	}
	
	/**
	 * Constructeur avec tous les paramètres obligatoires
	 * @param titre
	 * @param resume
	 * @param isbn13
	 * @param titreLivre
	 * @param format
	 * @param lienImage
	 * @param dateDepotLegal
	 * @param genres
	 * @param auteurs
	 * @param editeur
	 * @param dateFinTirage
	 */
	public LivreImprime(@NonNull String titre, @NonNull String resume, @NonNull String isbn13,
			@NonNull String titreLivre, @NonNull String format, @NonNull String lienImage,
			@NonNull LocalDate dateDepotLegal, @NonNull List<Genre> genres, @NonNull List<Auteur> auteurs,
			@NonNull Editeur editeur, @NonNull LocalDate dateFinTirage) {
		super(titre, resume, isbn13, titreLivre, format, lienImage, dateDepotLegal, genres, auteurs, editeur);
		this.dateFinTirage = dateFinTirage;
	}
	
	/**
	 *  Constructeur complet mis à part l'ID
	 * @param titre
	 * @param resume
	 * @param prixHT
	 * @param prixTTC
	 * @param consultations
	 * @param lignesCommande
	 * @param isbn13
	 * @param titreLivre
	 * @param format
	 * @param lienImage
	 * @param nombrePages
	 * @param dateDepotLegal
	 * @param genres
	 * @param auteurs
	 * @param editeur
	 * @param quantiteStock
	 * @param dateFinTirage
	 * @param dateReimpression
	 * @param poids
	 * @param unitePoids
	 * @param longueur
	 * @param largeur
	 * @param epaisseur
	 * @param uniteLongueur
	 */
	public LivreImprime(@NonNull String titre, @NonNull String resume, int prixHT, int prixTTC,
			List<Consultation> consultations, List<LigneCommande> lignesCommande, @NonNull String isbn13,
			@NonNull String titreLivre, @NonNull String format, @NonNull String lienImage, int nombrePages,
			LocalDate dateDepotLegal, @NonNull List<Genre> genres, @NonNull List<Auteur> auteurs,
			@NonNull Editeur editeur, int quantiteStock, @NonNull LocalDate dateFinTirage,
			LocalDate dateReimpression, double poids, String unitePoids, double longueur, double largeur,
			double epaisseur, String uniteLongueur) {
		super(titre, resume, prixHT, prixTTC, consultations, lignesCommande, isbn13, titreLivre, format, lienImage,
				nombrePages, dateDepotLegal, genres, auteurs, editeur);
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

	/**
	 * Constructeur complet
	 * @param id
	 * @param titre
	 * @param resume
	 * @param prixHT
	 * @param prixTTC
	 * @param consultations
	 * @param lignesCommande
	 * @param isbn13
	 * @param titreLivre
	 * @param format
	 * @param lienImage
	 * @param nombrePages
	 * @param dateDepotLegal
	 * @param genres
	 * @param auteurs
	 * @param editeur
	 * @param quantiteStock
	 * @param dateFinTirage
	 * @param dateReimpression
	 * @param poids
	 * @param unitePoids
	 * @param longueur
	 * @param largeur
	 * @param epaisseur
	 * @param uniteLongueur
	 */
	public LivreImprime(long id, @NonNull String titre, @NonNull String resume, int prixHT, int prixTTC,
			List<Consultation> consultations, List<LigneCommande> lignesCommande, @NonNull String isbn13,
			@NonNull String titreLivre, @NonNull String format, @NonNull String lienImage, int nombrePages,
			LocalDate dateDepotLegal, @NonNull List<Genre> genres, @NonNull List<Auteur> auteurs,
			@NonNull Editeur editeur, int quantiteStock, @NonNull LocalDate dateFinTirage,
			LocalDate dateReimpression, double poids, String unitePoids, double longueur, double largeur,
			double epaisseur, String uniteLongueur) {
		super(id, titre, resume, prixHT, prixTTC, consultations, lignesCommande, isbn13, titreLivre, format, lienImage,
				nombrePages, dateDepotLegal, genres, auteurs, editeur);
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

	/**
	 * 
	 * @return
	 */
	public int getQuantiteStock() {
		return quantiteStock;
	}

	/**
	 * 
	 * @param quantiteStock
	 */
	public void setQuantiteStock(int quantiteStock) {
		this.quantiteStock = quantiteStock;
	}

	/**
	 * 
	 * @return
	 */
	public LocalDate getDateFinTirage() {
		return dateFinTirage;
	}

	/**
	 * 
	 * @param dateFinTirage
	 */
	public void setDateFinTirage(LocalDate dateFinTirage) {
		this.dateFinTirage = dateFinTirage;
	}

	/**
	 * 
	 * @return
	 */
	public LocalDate getDateReimpression() {
		return dateReimpression;
	}

	/**
	 * 
	 * @param dateReimpression
	 */
	public void setDateReimpression(LocalDate dateReimpression) {
		this.dateReimpression = dateReimpression;
	}

	/**
	 * 
	 * @return
	 */
	public double getPoids() {
		return poids;
	}

	/**
	 * 
	 * @param poids
	 */
	public void setPoids(double poids) {
		this.poids = poids;
	}

	/**
	 * 
	 * @return
	 */
	public String getUnitePoids() {
		return unitePoids;
	}

	/**
	 * 
	 * @param unitePoids
	 */
	public void setUnitePoids(String unitePoids) {
		this.unitePoids = unitePoids;
	}

	/**
	 * 
	 * @return
	 */
	public double getLongueur() {
		return longueur;
	}

	/**
	 * 
	 * @param longueur
	 */
	public void setLongueur(double longueur) {
		this.longueur = longueur;
	}

	/**
	 * 
	 * @return
	 */
	public double getLargeur() {
		return largeur;
	}

	/**
	 * 
	 * @param largeur
	 */
	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

	/**
	 * 
	 * @return
	 */
	public double getEpaisseur() {
		return epaisseur;
	}

	/**
	 * 
	 * @param epaisseur
	 */
	public void setEpaisseur(double epaisseur) {
		this.epaisseur = epaisseur;
	}

	/**
	 * 
	 * @return
	 */
	public String getUniteLongueur() {
		return uniteLongueur;
	}

	/**
	 * 
	 * @param uniteLongueur
	 */
	public void setUniteLongueur(String uniteLongueur) {
		this.uniteLongueur = uniteLongueur;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dateFinTirage == null) ? 0 : dateFinTirage.hashCode());
		result = prime * result + ((dateReimpression == null) ? 0 : dateReimpression.hashCode());
		long temp;
		temp = Double.doubleToLongBits(epaisseur);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(largeur);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longueur);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(poids);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantiteStock;
		result = prime * result + ((uniteLongueur == null) ? 0 : uniteLongueur.hashCode());
		result = prime * result + ((unitePoids == null) ? 0 : unitePoids.hashCode());
		return result;
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LivreImprime other = (LivreImprime) obj;
		if (dateFinTirage == null) {
			if (other.dateFinTirage != null)
				return false;
		} else if (!dateFinTirage.equals(other.dateFinTirage))
			return false;
		if (dateReimpression == null) {
			if (other.dateReimpression != null)
				return false;
		} else if (!dateReimpression.equals(other.dateReimpression))
			return false;
		if (Double.doubleToLongBits(epaisseur) != Double.doubleToLongBits(other.epaisseur))
			return false;
		if (Double.doubleToLongBits(largeur) != Double.doubleToLongBits(other.largeur))
			return false;
		if (Double.doubleToLongBits(longueur) != Double.doubleToLongBits(other.longueur))
			return false;
		if (Double.doubleToLongBits(poids) != Double.doubleToLongBits(other.poids))
			return false;
		if (quantiteStock != other.quantiteStock)
			return false;
		if (uniteLongueur == null) {
			if (other.uniteLongueur != null)
				return false;
		} else if (!uniteLongueur.equals(other.uniteLongueur))
			return false;
		if (unitePoids == null) {
			if (other.unitePoids != null)
				return false;
		} else if (!unitePoids.equals(other.unitePoids))
			return false;
		return true;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "LivreImprime [quantiteStock=" + quantiteStock + ", dateFinTirage=" + dateFinTirage
				+ ", dateReimpression=" + dateReimpression + ", poids=" + poids + ", unitePoids=" + unitePoids
				+ ", longueur=" + longueur + ", largeur=" + largeur + ", epaisseur=" + epaisseur + ", uniteLongueur="
				+ uniteLongueur + ", toString()=" + super.toString() + "]";
	}
	
}
