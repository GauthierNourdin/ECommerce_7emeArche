package fr.pythie.webservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@ToString(of = { "id", "civilite", "nom", "prenom" }) 
@EqualsAndHashCode
@Entity
public abstract class Personne {
	/** Classe abstraite servent de base aux classes Client et Auteur */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String civilite;
	@NonNull
	String nom;
	String prenom;

	// Constructeur complet mis à part l'ID
	public Personne(String civilite, @NonNull String nom, String prenom) {
		super();
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
	}
	
}
