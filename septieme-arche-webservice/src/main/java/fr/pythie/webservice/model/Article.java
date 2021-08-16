package fr.pythie.webservice.model;

import java.util.ArrayList;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@ToString(of = { "titre", "resume", "prixHT", "prixTTC" })
@EqualsAndHashCode
public abstract class Article {
	/** Classe abstraite servent de base à tous les types d'articles */

	@NonNull
	String titre;
	@NonNull
	String resume;
	int prixHT;
	int prixTTC;
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "article")
	ArrayList<Consultation> consultations = new ArrayList<Consultation>();
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "article")
	ArrayList<LigneCommande> lignesCommande = new ArrayList<LigneCommande>();

}
