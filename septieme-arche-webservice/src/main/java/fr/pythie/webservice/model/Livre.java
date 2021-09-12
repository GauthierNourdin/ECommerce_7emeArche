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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString(of = { "isbn13", "titreLivre", "format", "lienImage", "nombrePages", "dateDepotLegal" })
@EqualsAndHashCode(callSuper = true)
@Entity
@JsonDeserialize(using = LivreDeserializer.class)
public abstract class Livre extends Article {
	/** Classe abstraite servent de base à tous les livres */

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

	// Constructeur complet mis à part l'ID
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

	// Constructeur complet
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

	// Constructeur avec tous les paramètres obligatoires
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
	
}
