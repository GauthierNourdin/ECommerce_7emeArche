package fr.pythie.webservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@ToString(of = { "id", "titre", "resume", "prixHT", "prixTTC" })
@EqualsAndHashCode
@Entity
public abstract class Article {
	/** Classe abstraite servent de base à tous les types d'articles */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@NonNull
	String titre;
	@NonNull
	String resume;
	int prixHT;
	int prixTTC;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
	List<Consultation> consultations;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
	List<LigneCommande> lignesCommande;

	// Constructeur complet mis à part l'ID
	public Article(@NonNull String titre, @NonNull String resume, int prixHT, int prixTTC,
			List<Consultation> consultations, List<LigneCommande> lignesCommande) {
		super();
		this.titre = titre;
		this.resume = resume;
		this.prixHT = prixHT;
		this.prixTTC = prixTTC;
		this.consultations = consultations;
		this.lignesCommande = lignesCommande;
	}
	
}
