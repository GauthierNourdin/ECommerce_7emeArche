package fr.pythie.webservice.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@Entity
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

}
