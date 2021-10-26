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
	 * Constructeur permettant de construire un livre imprimé vide.
	 * 
	 * @since 1.0
	 */
	public LivreImprime() {
		super();
	}
	
	/**
	 * Constructeur permettant de construire un livre imprimé avec toutes les informations obligatoires.
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
	 * @param dateFinTirage La date de fin du dernier tirage.
	 * 
	 * @since 1.0
	 */
	public LivreImprime(@NonNull String titre, @NonNull String resume, @NonNull String isbn13,
			@NonNull String titreLivre, @NonNull String format, @NonNull String lienImage,
			@NonNull LocalDate dateDepotLegal, @NonNull List<Genre> genres, @NonNull List<Auteur> auteurs,
			@NonNull Editeur editeur, @NonNull LocalDate dateFinTirage) {
		super(titre, resume, isbn13, titreLivre, format, lienImage, dateDepotLegal, genres, auteurs, editeur);
		this.dateFinTirage = dateFinTirage;
	}
	
	/**
	 * Constructeur permettant permet de construire un livre imprimé complet mais sans identifiant.
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
	 * @param quantiteStock La quantité en stock.
	 * @param dateFinTirage La date de fin du dernier tirage.
	 * @param dateReimpression La date du début de la dernière réimpression.
	 * @param poids Le poids du livre.
	 * @param unitePoids L'unité de poids du livre.
	 * @param longueur La longueur du livre.
	 * @param largeur La largeur du livre.
	 * @param epaisseur L'épaisseur du livre.
	 * @param uniteLongueur L'unité de longueur du livre.
	 * 
	 * @since 1.0
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
	 * Constructeur permettant permet de construire un livre imprimé complet.
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
	 * @param quantiteStock La quantité en stock.
	 * @param dateFinTirage La date de fin du dernier tirage.
	 * @param dateReimpression La date du début de la dernière réimpression.
	 * @param poids Le poids du livre.
	 * @param unitePoids L'unité de poids du livre.
	 * @param longueur La longueur du livre.
	 * @param largeur La largeur du livre.
	 * @param epaisseur L'épaisseur du livre.
	 * @param uniteLongueur L'unité de longueur du livre.
	 * 
	 * @since 1.0
	 */
	public LivreImprime(Long id, @NonNull String titre, @NonNull String resume, int prixHT, int prixTTC,
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
	 * Retourne la quantité en stock.
	 * 
	 * @return quantiteStock La quantité en stock.
	 * 
	 * @see LivreImprime#setQuantiteStock(int)
	 * 
	 * @since 1.0
	 */
	public int getQuantiteStock() {
		return quantiteStock;
	}

	/**
	 * Modifie la quantité en stock.
	 * 
	 * @param quantiteStock La nouvelle quantité en stock.
	 * 
	 * @see LivreImprime#getQuantiteStock()
	 * 
	 * @since 1.0
	 */
	public void setQuantiteStock(int quantiteStock) {
		this.quantiteStock = quantiteStock;
	}

	/**
	 * Retourne la date de fin du dernier tirage.
	 * 
	 * @return dateFinTirage La date de fin du dernier tirage.
	 * 
	 * @see LivreImprime#setDateFinTirage(LocalDate)
	 * 
	 * @since 1.0
	 */
	public LocalDate getDateFinTirage() {
		return dateFinTirage;
	}

	/**
	 * Modifie la date de fin du dernier tirage.
	 * 
	 * @param dateFinTirage La nouvelle date de fin du dernier tirage.
	 * 
	 * @see LivreImprime#getDateFinTirage()
	 * 
	 * @since 1.0
	 */
	public void setDateFinTirage(LocalDate dateFinTirage) {
		this.dateFinTirage = dateFinTirage;
	}

	/**
	 * Retourne la date du début de la dernière réimpression.
	 * 
	 * @return dateReimpression La date du début de la dernière réimpression.
	 * 
	 * @see LivreImprime#setDateReimpression(LocalDate)
	 * 
	 * @since 1.0
	 */
	public LocalDate getDateReimpression() {
		return dateReimpression;
	}

	/**
	 * Modifie la date du début de la dernière réimpression.
	 * 
	 * @param dateReimpression La nouvelle date du début de la dernière réimpression.
	 * 
	 * @see LivreImprime#getDateReimpression()
	 * 
	 * @since 1.0
	 */
	public void setDateReimpression(LocalDate dateReimpression) {
		this.dateReimpression = dateReimpression;
	}

	/**
	 * Retourne le poids du livre.
	 * 
	 * @return poids Le poids du livre.
	 *  
	 * @see LivreImprime#setPoids(double)
	 * 
	 * @since 1.0
	 */
	public double getPoids() {
		return poids;
	}

	/**
	 * Modifie le poids du livre.
	 * 
	 * @param poids Le nouveau poids du livre.
	 * 
	 * @see LivreImprime#getPoids()
	 * 
	 * @since 1.0
	 */
	public void setPoids(double poids) {
		this.poids = poids;
	}

	/**
	 * Retourne l'unité de poids du livre.
	 * 
	 * @return unitePoids L'unité de poids du livre.
	 * 
	 * @see LivreImprime#setUnitePoids(String)
	 * 
	 * @since 1.0
	 */
	public String getUnitePoids() {
		return unitePoids;
	}

	/**
	 * Modifie l'unité de poids du livre.
	 * 
	 * @param unitePoids La nouvelle unité de poids du livre.
	 * 
	 * @see LivreImprime#getUnitePoids()
	 * 
	 * @since 1.0
	 */
	public void setUnitePoids(String unitePoids) {
		this.unitePoids = unitePoids;
	}

	/**
	 * Retourne la longueur du livre.
	 * 
	 * @return longueur La longueur du livre.
	 * 
	 * @see LivreImprime#setLongueur(double)
	 * 
	 * @since 1.0
	 */
	public double getLongueur() {
		return longueur;
	}

	/**
	 * Modifie la longueur du livre.
	 * 
	 * @param longueur La nouvelle longueur du livre.
	 * 
	 * @see LivreImprime#getLongueur()
	 * 
	 * @since 1.0
	 */
	public void setLongueur(double longueur) {
		this.longueur = longueur;
	}

	/**
	 * Retourne la largeur du livre.
	 * 
	 * @return largeur La largeur du livre.
	 * 
	 * @see LivreImprime#setLargeur(double)
	 * 
	 * @since 1.0
	 */
	public double getLargeur() {
		return largeur;
	}

	/**
	 * Modifie la largeur du livre.
	 * 
	 * @param largeur La nouvelle largeur du livre.
	 * 
	 * @see LivreImprime#getLargeur()
	 * 
	 * @since 1.0
	 */
	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

	/**
	 * Retourne l'épaisseur du livre.
	 * 
	 * @return epaisseur L'épaisseur du livre.
	 * 
	 * @see LivreImprime#setEpaisseur(double)
	 * 
	 * @since 1.0
	 */
	public double getEpaisseur() {
		return epaisseur;
	}

	/**
	 * Modifie l'épaisseur du livre.
	 * 
	 * @param epaisseur La nouvelle épaisseur du livre.
	 * 
	 * @see LivreImprime#getEpaisseur()
	 * 
	 * @since 1.0
	 */
	public void setEpaisseur(double epaisseur) {
		this.epaisseur = epaisseur;
	}

	/**
	 * Retourne l'unité de longueur du livre.
	 * 
	 * @return uniteLongueur L'unité de longueur du livre.
	 * 
	 * @see LivreImprime#setUniteLongueur(String)
	 * 
	 * @since 1.0
	 */
	public String getUniteLongueur() {
		return uniteLongueur;
	}

	/**
	 * Modifie l'unité de longueur du livre.
	 * 
	 * @param uniteLongueur La nouvelle unité de longueur du livre.
	 * 
	 * @see LivreImprime#getUniteLongueur()
	 * 
	 * @since 1.0
	 */
	public void setUniteLongueur(String uniteLongueur) {
		this.uniteLongueur = uniteLongueur;
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
	 * Définit les conditions dans lesquelles un autre objet est égal à ce livre imprimé.
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
	 * Produit la chaîne de caractères réprésentant le livre imprimé.
	 * On n'y intègre pas l'éditeur, les consultations, les lignes de commandes, les genres et les auteurs associés.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "LivreImprime [quantiteStock=" + quantiteStock + ", dateFinTirage=" + dateFinTirage
				+ ", dateReimpression=" + dateReimpression + ", poids=" + poids + ", unitePoids=" + unitePoids
				+ ", longueur=" + longueur + ", largeur=" + largeur + ", epaisseur=" + epaisseur + ", uniteLongueur="
				+ uniteLongueur + ", toString()=" + super.toString() + "]";
	}
	
}
