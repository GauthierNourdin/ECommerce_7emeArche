package fr.pythie.webservice.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
	@Fetch(value = FetchMode.SUBSELECT)
	//@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	List<Genre> genres;
	@NonNull
	@JsonIgnoreProperties( "livres" )
	@Fetch(value = FetchMode.SUBSELECT)
	//@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	List<Auteur> auteurs;
	@NonNull
	@JsonIgnoreProperties( "livres" )
	//@Fetch(value = FetchMode.SUBSELECT)
	//@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Editeur editeur = new Editeur();

	/**
	 * Constructeur permettant de construire un livre vide.
	 * 
	 * @since 1.0
	 */
	public Livre() {
		super();
	}
	
	/**
	 * Constructeur permettant de construire un livre avec toutes les informations obligatoires.
	 * 
	 * @param titre Le titre général.
	 * @param resume Le résumé.
	 * @param isbn13 Le numéro isbn13.
	 * @param titreLivre Le titre du livre.
	 * @param format Le format.
	 * @param lienImage Le lien vers l'image du livre.
	 * @param dateDepotLegal La date du dépot légal du livre.
	 * @param genres La liste des genres du livre.
	 * @param auteurs La liste des auteurs du livre.
	 * @param editeur L'éditeur du livre.
	 * 
	 * @since 1.0
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
	 * Constructeur permettant permet de construire un livre complet mais sans identifiant.
	 * 
	 * @param titre Le titre général.
	 * @param resume Le résumé.
	 * @param prixHT Le prix unitaire hors taxe multiplié par 100.
	 * @param prixTTC Le prix unitaire toutes taxes comprises multiplié par 100.
	 * @param consultations La liste de consultations de la page de cet article.
	 * @param lignesCommande La liste des lignes de commande associée à cet article.
	 * @param isbn13 Le numéro isbn13.
	 * @param titreLivre Le titre du livre.
	 * @param format Le format.
	 * @param lienImage Le lien vers l'image du livre.
	 * @param nombrePages Le nombre de pages.
	 * @param dateDepotLegal La date du dépot légal du livre.
	 * @param genres La liste des genres du livre.
	 * @param auteurs La liste des auteurs du livre.
	 * @param editeur L'éditeur du livre.
	 * 
	 * @since 1.0
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
	 * Constructeur permettant permet de construire un livre complet.
	 * 
	 * @param id L'identifiant.
	 * @param titre Le titre général.
	 * @param resume Le résumé.
	 * @param prixHT Le prix unitaire hors taxe multiplié par 100.
	 * @param prixTTC Le prix unitaire toutes taxes comprises multiplié par 100.
	 * @param consultations La liste de consultations de la page de cet article.
	 * @param lignesCommande La liste des lignes de commande associée à cet article.
	 * @param isbn13 Le numéro isbn13.
	 * @param titreLivre Le titre du livre.
	 * @param format Le format.
	 * @param lienImage Le lien vers l'image du livre.
	 * @param nombrePages Le nombre de pages.
	 * @param dateDepotLegal La date du dépot légal du livre.
	 * @param genres La liste des genres du livre.
	 * @param auteurs La liste des auteurs du livre.
	 * @param editeur L'éditeur du livre.
	 * 
	 * @since 1.0
	 */
	public Livre(Long id, @NonNull String titre, @NonNull String resume, int prixHT, int prixTTC,
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
	 * Retourne le numéro isbn13.
	 * 
	 * @return isbn13 Le numéro isbn13.
	 * 
	 * @see Livre#setIsbn13(String)
	 * 
	 * @since 1.0
	 */
	public String getIsbn13() {
		return isbn13;
	}

	/**
	 * Modifie le numéro isbn13.
	 * 
	 * @param isbn13 Le nouveau numéro isbn13.
	 * 
	 * @see Livre#getIsbn13()
	 * 
	 * @since 1.0
	 */
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	/**
	 * Retourne le titre du livre.
	 * 
	 * @return titreLivre Le titre du livre.
	 * 
	 * @see Livre#setTitreLivre(String)
	 * 
	 * @since 1.0
	 */
	public String getTitreLivre() {
		return titreLivre;
	}

	/**
	 * Modifie le titre du livre.
	 * 
	 * @param titreLivre Le nouveau titre du livre.
	 * 
	 * @see Livre#getTitreLivre()
	 * 
	 * @since 1.0
	 */
	public void setTitreLivre(String titreLivre) {
		this.titreLivre = titreLivre;
	}

	/**
	 * Retourne le format.
	 * 
	 * @return format Le format.
	 * 
	 * @see Livre#setFormat(String)
	 * 
	 * @since 1.0
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Modifie le format.
	 * 
	 * @param format Le nouveau format.
	 * 
	 * @see Livre#getFormat() 
	 *  
	 * @since 1.0
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * Retourne le lien vers l'image du livre.
	 * 
	 * @return lienImage Le lien vers l'image du livre.
	 * 
	 * @see Livre#setLienImage(String)
	 * 
	 * @since 1.0
	 */
	public String getLienImage() {
		return lienImage;
	}

	/**
	 * Modifie le lien vers l'image du livre.
	 * 
	 * @param lienImage Le nouveau lien vers l'image du livre.
	 * 
	 * @see Livre#getLienImage()
	 * 
	 * @since 1.0
	 */
	public void setLienImage(String lienImage) {
		this.lienImage = lienImage;
	}

	/**
	 * Retourne le nombre de pages.
	 * 
	 * @return nombrePages Le nombre de pages.
	 * 
	 * @see Livre#setNombrePages(int)
	 * 
	 * @since 1.0
	 */
	public int getNombrePages() {
		return nombrePages;
	}

	/**
	 * Modifie le nombre de pages.
	 * 
	 * @param nombrePages Le nouveau nombre de pages.
	 * 
	 * @see Livre#getNombrePages()
	 * 
	 * @since 1.0
	 */
	public void setNombrePages(int nombrePages) {
		this.nombrePages = nombrePages;
	}

	/**
	 * Retourne la date du dépot légal du livre.
	 * 
	 * @return dateDepotLegal La date du dépot légal du livre.
	 * 
	 * @see Livre#setDateDepotLegal(LocalDate)
	 * 
	 * @since 1.0
	 */
	public LocalDate getDateDepotLegal() {
		return dateDepotLegal;
	}

	/**
	 * Modifie la date du dépot légal du livre.
	 * 
	 * @param dateDepotLegal La nouvelle date du dépot légal du livre.
	 * 
	 * @see Livre#getDateDepotLegal()
	 * 
	 * @since 1.0
	 */
	public void setDateDepotLegal(LocalDate dateDepotLegal) {
		this.dateDepotLegal = dateDepotLegal;
	}

	/**
	 * Retourne la liste des genres du livre.
	 * 
	 * @return genres La liste des genres du livre.
	 * 
	 * @see Livre#setGenres(List)
	 * 
	 * @since 1.0
	 */
	public List<Genre> getGenres() {
		return genres;
	}

	/**
	 * Modifie la liste des genres du livre.
	 * 
	 * @param genres La nouvelle liste des genres du livre.
	 * 
	 * @see Livre#getGenres()
	 * 
	 * @since 1.0
	 */
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	/**
	 * Retourne la liste des auteurs du livre.
	 * 
	 * @return auteurs La liste des auteurs du livre.
	 * 
	 * @see Livre#setAuteurs(List)
	 * 
	 * @since 1.0
	 */
	public List<Auteur> getAuteurs() {
		return auteurs;
	}

	/**
	 * Modifie la liste des auteurs du livre.
	 * 
	 * @param auteurs La nouvelle liste des auteurs du livre.
	 * 
	 * @see Livre#getAuteurs()
	 * 
	 * @since 1.0
	 */
	public void setAuteurs(List<Auteur> auteurs) {
		this.auteurs = auteurs;
	}

	/**
	 * Retourne l'éditeur du livre.
	 * 
	 * @return editeur L'éditeur du livre.
	 * 
	 * @see Livre#setEditeur(Editeur)
	 * 
	 * @since 1.0
	 */
	public Editeur getEditeur() {
		return editeur;
	}

	/**
	 * Modifie l'éditeur du livre.
	 * 
	 * @param editeur Le nouvel éditeur du livre.
	 * 
	 * @see Livre#getEditeur()
	 * 
	 * @since 1.0
	 */
	public void setEditeur(Editeur editeur) {
		this.editeur = editeur;
	}

	/**
	 * Calcule le code hash.
	 * 
	 * @return result Le code hash.
	 * 
	 * @since 1.0
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
	 * Définit les conditions dans lesquelles un autre objet est égal à ce livre.
	 *
	 * @param obj L'objet auquel on veut comparer.
	 *
	 * @return true Si les deux objets sont identiques, false sinon.
	 * 
	 * @since 1.0
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
	 * Produit la chaîne de caractères réprésentant le livre.
	 * On n'y intègre pas l'éditeur, les consultations, les lignes de commandes, les genres et les auteurs associés.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "Livre [isbn13=" + isbn13 + ", titreLivre=" + titreLivre + ", format=" + format + ", lienImage="
				+ lienImage + ", nombrePages=" + nombrePages + ", dateDepotLegal=" + dateDepotLegal + ", toString()=" + super.toString() + "]";
	}
	
}
