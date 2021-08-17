package fr.pythie.webservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Auteur extends Personne {
	/** Classe pour les auteurs */

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "auteurs")
	List<Livre> livres;

	// Constructeur complet mis à part l'ID
	public Auteur(String civilite, @NonNull String nom, String prenom, List<Livre> livres) {
		super(civilite, nom, prenom);
		this.livres = livres;
	}

	// Constructeur complet
	public Auteur(long id, String civilite, @NonNull String nom, String prenom, List<Livre> livres) {
		super(id, civilite, nom, prenom);
		this.livres = livres;
	}

	// Constructeur avec tous les paramètres obligatoires
	public Auteur(@NonNull String nom) {
		super(nom);
	}
	
}
