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
	 * Constructeur permettant de construire un livre numérique vide.
	 * 
	 * @since 1.0
	 */
	public LivreNumerique() {
		super();
	}
	
	/**
	 * Constructeur permettant de construire un livre numérique avec toutes les informations obligatoires.
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
	public LivreNumerique(@NonNull String titre, @NonNull String resume, @NonNull String isbn13,
			@NonNull String titreLivre, @NonNull String format, @NonNull String lienImage,
			@NonNull LocalDate dateDepotLegal, @NonNull List<Genre> genres, @NonNull List<Auteur> auteurs,
			@NonNull Editeur editeur) {
		super(titre, resume, isbn13, titreLivre, format, lienImage, dateDepotLegal, genres, auteurs, editeur);
	}
	
	/**
	 * Constructeur permettant permet de construire un livre numérique complet mais sans identifiant.
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
	 * @param espace La taille numérique du livre.
	 * @param unite L'unité de taille du livre.
	 * 
	 * @since 1.0
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
	 * Constructeur permettant permet de construire un livre numérique complet.
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
	 * @param espace La taille numérique du livre.
	 * @param unite L'unité de taille du livre.
	 * 
	 * @since 1.0
	 */
	public LivreNumerique(Long id, @NonNull String titre, @NonNull String resume, int prixHT, int prixTTC,
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
	 * Retourne la taille numérique du livre.
	 * 
	 * @return espace La taille numérique du livre.
	 * 
	 * @see LivreNumerique#setEspace
	 * 
	 * @since 1.0
	 */
	public double getEspace() {
		return espace;
	}

	/**
	 * Modifie la taille numérique du livre.
	 * 
	 * @param espace La nouvelle taille numérique du livre.
	 * 
	 * @see LivreNumerique#getEspace()
	 * 
	 * @since 1.0
	 */
	public void setEspace(double espace) {
		this.espace = espace;
	}

	/**
	 * Retourne l'unité de taille du livre.
	 * 
	 * @return unite L'unité de taille du livre.
	 * 
	 * @see LivreNumerique#setUnite(String)
	 * 
	 * @since 1.0
	 */
	public String getUnite() {
		return unite;
	}

	/**
	 * Modifie l'unité de taille du livre.
	 * 
	 * @param unite La nouvelle unité de taille du livre.
	 * 
	 * @see LivreNumerique#getUnite()
	 * 
	 * @since 1.0
	 */
	public void setUnite(String unite) {
		this.unite = unite;
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
		long temp;
		temp = Double.doubleToLongBits(espace);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((unite == null) ? 0 : unite.hashCode());
		return result;
	}

	/**
	 * Définit les conditions dans lesquelles un autre objet est égal à ce livre numérique.
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
	 * Produit la chaîne de caractères réprésentant le livre numérique.
	 * On n'y intègre pas l'éditeur, les consultations, les lignes de commandes, les genres et les auteurs associés.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "LivreNumerique [espace=" + espace + ", unite=" + unite + ", toString()=" + super.toString() + "]";
	}

}
