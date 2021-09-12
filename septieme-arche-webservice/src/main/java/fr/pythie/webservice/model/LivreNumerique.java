package fr.pythie.webservice.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

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
@ToString(of = { "espace", "unite" }) 
@EqualsAndHashCode(callSuper = true)
@Entity
@JsonDeserialize(as = LivreNumerique.class)
public class LivreNumerique extends Livre {
	/** Classe pour les livres numériques */

	double espace;
	String unite;

	// Constructeur complet mis à part l'ID
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

	// Constructeur complet
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

	// Constructeur avec tous les paramètres obligatoires
	public LivreNumerique(@NonNull String titre, @NonNull String resume, @NonNull String isbn13,
			@NonNull String titreLivre, @NonNull String format, @NonNull String lienImage,
			@NonNull LocalDate dateDepotLegal, @NonNull List<Genre> genres, @NonNull List<Auteur> auteurs,
			@NonNull Editeur editeur) {
		super(titre, resume, isbn13, titreLivre, format, lienImage, dateDepotLegal, genres, auteurs, editeur);
	}

}
