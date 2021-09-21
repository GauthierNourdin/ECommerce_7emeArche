package fr.pythie.webservice.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/** 
 * Classe modèle pour les livres numériques.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@JsonDeserialize(as = LivreNumerique.class)
public class LivreNumerique extends Livre {

	double espace;
	String unite;

	/**
	 * 
	 */
	public LivreNumerique() {
		super();
	}
	
	/**
	 *  Constructeur avec tous les paramètres obligatoires
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
	 */
	public LivreNumerique(@NonNull String titre, @NonNull String resume, @NonNull String isbn13,
			@NonNull String titreLivre, @NonNull String format, @NonNull String lienImage,
			@NonNull LocalDate dateDepotLegal, @NonNull List<Genre> genres, @NonNull List<Auteur> auteurs,
			@NonNull Editeur editeur) {
		super(titre, resume, isbn13, titreLivre, format, lienImage, dateDepotLegal, genres, auteurs, editeur);
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
	 * @param espace
	 * @param unite
	 */
	public LivreNumerique(@NonNull String titre, @NonNull String resume, int prixHT, int prixTTC,
			List<Consultation> consultations, List<LigneCommande> lignesCommande, @NonNull String isbn13,
			@NonNull String titreLivre, @NonNull String format, @NonNull String lienImage, int nombrePages,
			LocalDate dateDepotLegal, @NonNull List<Genre> genres, @NonNull List<Auteur> auteurs,
			@NonNull Editeur editeur, double espace, String unite) {
		super(titre, resume, prixHT, prixTTC, consultations, lignesCommande, isbn13, titreLivre, format, lienImage,
				nombrePages, dateDepotLegal, genres, auteurs, editeur);
		this.espace = espace;
		this.unite = unite;
	}

	/**
	 *  Constructeur complet
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
	 * @param espace
	 * @param unite
	 */
	public LivreNumerique(long id, @NonNull String titre, @NonNull String resume, int prixHT, int prixTTC,
			List<Consultation> consultations, List<LigneCommande> lignesCommande, @NonNull String isbn13,
			@NonNull String titreLivre, @NonNull String format, @NonNull String lienImage, int nombrePages,
			@NonNull LocalDate dateDepotLegal, @NonNull List<Genre> genres, @NonNull List<Auteur> auteurs,
			@NonNull Editeur editeur, double espace, String unite) {
		super(id, titre, resume, prixHT, prixTTC, consultations, lignesCommande, isbn13, titreLivre, format, lienImage,
				nombrePages, dateDepotLegal, genres, auteurs, editeur);
		this.espace = espace;
		this.unite = unite;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getEspace() {
		return espace;
	}

	/**
	 * 
	 * @param espace
	 */
	public void setEspace(double espace) {
		this.espace = espace;
	}

	/**
	 * 
	 * @return
	 */
	public String getUnite() {
		return unite;
	}

	/**
	 * 
	 * @param unite
	 */
	public void setUnite(String unite) {
		this.unite = unite;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(espace);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((unite == null) ? 0 : unite.hashCode());
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
		LivreNumerique other = (LivreNumerique) obj;
		if (Double.doubleToLongBits(espace) != Double.doubleToLongBits(other.espace))
			return false;
		if (unite == null) {
			if (other.unite != null)
				return false;
		} else if (!unite.equals(other.unite))
			return false;
		return true;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "LivreNumerique [espace=" + espace + ", unite=" + unite + ", toString()=" + super.toString() + "]";
	}

}
