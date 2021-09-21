package fr.pythie.webservice.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import fr.pythie.webservice.communication.LivreDeserializer;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/** 
 * Classe modèle abstraite servent de base à tous les livres.
 * 
 * @see Article
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@JsonDeserialize(using = LivreDeserializer.class)
public abstract class Livre extends Article {

	@NonNull
	String isbn13;
	@NonNull
	String titreLivre;
	@NonNull
	String format;
	@NonNull
	String lienImage;
	int nombrePages;
	@NonNull
	LocalDate dateDepotLegal = LocalDate.now();
	@NonNull
	@JsonIgnoreProperties( "livres" )
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	List<Genre> genres;
	@NonNull
	@JsonIgnoreProperties( "livres" )
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	List<Auteur> auteurs;
	@NonNull
	@JsonIgnoreProperties( "livres" )
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Editeur editeur = new Editeur();

	/**
	 * Ce constructeur permet de construire un livre vide.
	 */
	public Livre() {
		super();
	}
	
	/**
	 * Ce constructeur permet de construire un livre avec toutes les informations obligatoires.
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
	public Livre(@NonNull String titre, @NonNull String resume, @NonNull String isbn13, @NonNull String titreLivre,
			@NonNull String format, @NonNull String lienImage, @NonNull LocalDate dateDepotLegal,
			@NonNull List<Genre> genres, @NonNull List<Auteur> auteurs, @NonNull Editeur editeur) {
		super(titre, resume);
		this.isbn13 = isbn13;
		this.titreLivre = titreLivre;
		this.format = format;
		this.lienImage = lienImage;
		this.dateDepotLegal = dateDepotLegal;
		this.genres = genres;
		this.auteurs = auteurs;
		this.editeur = editeur;
	}
	
	/**
	 * Ce constructeur permet de construire un livre complet mais sans identifiant.
	 * 
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
	 */
	public Livre(@NonNull String titre, @NonNull String resume, int prixHT, int prixTTC,
			List<Consultation> consultations, List<LigneCommande> lignesCommande, @NonNull String isbn13,
			@NonNull String titreLivre, @NonNull String format, @NonNull String lienImage, int nombrePages,
			LocalDate dateDepotLegal, @NonNull List<Genre> genres, @NonNull List<Auteur> auteurs,
			@NonNull Editeur editeur) {
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

	/**
	 * Ce constructeur permet de construire un livre complet.
	 * 
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
	 */
	public Livre(long id, @NonNull String titre, @NonNull String resume, int prixHT, int prixTTC,
			List<Consultation> consultations, List<LigneCommande> lignesCommande, @NonNull String isbn13,
			@NonNull String titreLivre, @NonNull String format, @NonNull String lienImage, int nombrePages,
			@NonNull LocalDate dateDepotLegal, @NonNull List<Genre> genres, @NonNull List<Auteur> auteurs,
			@NonNull Editeur editeur) {
		super(id, titre, resume, prixHT, prixTTC, consultations, lignesCommande);
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

	/**
	 * 
	 * @return
	 */
	public String getIsbn13() {
		return isbn13;
	}

	/**
	 * 
	 * @param isbn13
	 */
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	/**
	 * 
	 * @return
	 */
	public String getTitreLivre() {
		return titreLivre;
	}

	/**
	 * 
	 * @param titreLivre
	 */
	public void setTitreLivre(String titreLivre) {
		this.titreLivre = titreLivre;
	}

	/**
	 * 
	 * @return
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * 
	 * @param format
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * 
	 * @return
	 */
	public String getLienImage() {
		return lienImage;
	}

	/**
	 * 
	 * @param lienImage
	 */
	public void setLienImage(String lienImage) {
		this.lienImage = lienImage;
	}

	/**
	 * 
	 * @return
	 */
	public int getNombrePages() {
		return nombrePages;
	}

	/**
	 * 
	 * @param nombrePages
	 */
	public void setNombrePages(int nombrePages) {
		this.nombrePages = nombrePages;
	}

	/**
	 * 
	 * @return
	 */
	public LocalDate getDateDepotLegal() {
		return dateDepotLegal;
	}

	/**
	 * 
	 * @param dateDepotLegal
	 */
	public void setDateDepotLegal(LocalDate dateDepotLegal) {
		this.dateDepotLegal = dateDepotLegal;
	}

	/**
	 * 
	 * @return
	 */
	public List<Genre> getGenres() {
		return genres;
	}

	/**
	 * 
	 * @param genres
	 */
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	/**
	 * 
	 * @return
	 */
	public List<Auteur> getAuteurs() {
		return auteurs;
	}

	/**
	 * 
	 * @param auteurs
	 */
	public void setAuteurs(List<Auteur> auteurs) {
		this.auteurs = auteurs;
	}

	/**
	 * 
	 * @return
	 */
	public Editeur getEditeur() {
		return editeur;
	}

	/**
	 * 
	 * @param editeur
	 */
	public void setEditeur(Editeur editeur) {
		this.editeur = editeur;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((auteurs == null) ? 0 : auteurs.hashCode());
		result = prime * result + ((dateDepotLegal == null) ? 0 : dateDepotLegal.hashCode());
		result = prime * result + ((editeur == null) ? 0 : editeur.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((genres == null) ? 0 : genres.hashCode());
		result = prime * result + ((isbn13 == null) ? 0 : isbn13.hashCode());
		result = prime * result + ((lienImage == null) ? 0 : lienImage.hashCode());
		result = prime * result + nombrePages;
		result = prime * result + ((titreLivre == null) ? 0 : titreLivre.hashCode());
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
		Livre other = (Livre) obj;
		if (auteurs == null) {
			if (other.auteurs != null)
				return false;
		} else if (!auteurs.equals(other.auteurs))
			return false;
		if (dateDepotLegal == null) {
			if (other.dateDepotLegal != null)
				return false;
		} else if (!dateDepotLegal.equals(other.dateDepotLegal))
			return false;
		if (editeur == null) {
			if (other.editeur != null)
				return false;
		} else if (!editeur.equals(other.editeur))
			return false;
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		if (genres == null) {
			if (other.genres != null)
				return false;
		} else if (!genres.equals(other.genres))
			return false;
		if (isbn13 == null) {
			if (other.isbn13 != null)
				return false;
		} else if (!isbn13.equals(other.isbn13))
			return false;
		if (lienImage == null) {
			if (other.lienImage != null)
				return false;
		} else if (!lienImage.equals(other.lienImage))
			return false;
		if (nombrePages != other.nombrePages)
			return false;
		if (titreLivre == null) {
			if (other.titreLivre != null)
				return false;
		} else if (!titreLivre.equals(other.titreLivre))
			return false;
		return true;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Livre [isbn13=" + isbn13 + ", titreLivre=" + titreLivre + ", format=" + format + ", lienImage="
				+ lienImage + ", nombrePages=" + nombrePages + ", dateDepotLegal=" + dateDepotLegal + ", genres="
				+ genres + ", auteurs=" + auteurs + ", editeur=" + editeur + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
