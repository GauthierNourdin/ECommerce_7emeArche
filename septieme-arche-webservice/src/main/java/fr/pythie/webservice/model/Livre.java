package fr.pythie.webservice.model;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	LocalDate dateDepotLegal = LocalDate.now();
	@NonNull
	@JsonIgnoreProperties( "livres" )
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	ArrayList<Genre> genres = new ArrayList<Genre>();
	@NonNull
	@JsonIgnoreProperties( "livres" )
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	ArrayList<Auteur> auteurs = new ArrayList<Auteur>();
	@NonNull
	@JsonIgnoreProperties( "livres" )
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	Editeur editeur = new Editeur();

}
