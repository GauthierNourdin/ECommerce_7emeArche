package fr.pythie.webservice.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(of = { "quantiteStock", "dateFinTirage", "dateReimpression", "poids", "unitePoids", "longueur", "largeur", "epaisseur", "uniteLongueur" }) 
@EqualsAndHashCode(callSuper = true)
@Entity
public class LivreImprime extends Livre {
	/** Classe pour les livres imprimés */


	int quantiteStock;
	@NonNull
	LocalDate dateFinTirage = LocalDate.now();
	@NonNull
	LocalDate dateReimpression = LocalDate.now();
	double poids;
	String unitePoids;
	double longueur;
	double largeur;
	double epaisseur;
	String uniteLongueur;

	// Constructeur complet mis à part l'ID
	public LivreImprime(@NonNull String titre, @NonNull String resume, int prixHT, int prixTTC,
			List<Consultation> consultations, List<LigneCommande> lignesCommande, @NonNull String isbn13,
			@NonNull String titreLivre, @NonNull String format, @NonNull String lienImage, int nombrePages,
			LocalDate dateDepotLegal, @NonNull List<Genre> genres, @NonNull List<Auteur> auteurs,
			@NonNull Editeur editeur, int quantiteStock, @NonNull LocalDate dateFinTirage,
			@NonNull LocalDate dateReimpression, double poids, String unitePoids, double longueur, double largeur,
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

	// Constructeur complet
	public LivreImprime(long id, @NonNull String titre, @NonNull String resume, int prixHT, int prixTTC,
			List<Consultation> consultations, List<LigneCommande> lignesCommande, @NonNull String isbn13,
			@NonNull String titreLivre, @NonNull String format, @NonNull String lienImage, int nombrePages,
			LocalDate dateDepotLegal, @NonNull List<Genre> genres, @NonNull List<Auteur> auteurs,
			@NonNull Editeur editeur, int quantiteStock, @NonNull LocalDate dateFinTirage,
			@NonNull LocalDate dateReimpression, double poids, String unitePoids, double longueur, double largeur,
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

	// Constructeur avec tous les paramètres obligatoires
	public LivreImprime(@NonNull String titre, @NonNull String resume, @NonNull String isbn13,
			@NonNull String titreLivre, @NonNull String format, @NonNull String lienImage,
			@NonNull LocalDate dateDepotLegal, @NonNull List<Genre> genres, @NonNull List<Auteur> auteurs,
			@NonNull Editeur editeur, @NonNull LocalDate dateFinTirage,
			@NonNull LocalDate dateReimpression) {
		super(titre, resume, isbn13, titreLivre, format, lienImage, dateDepotLegal, genres, auteurs, editeur);
		this.dateFinTirage = dateFinTirage;
		this.dateReimpression = dateReimpression;
	}
	
}
