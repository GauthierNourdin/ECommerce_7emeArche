package fr.pythie.webservice.model;

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
@ToString(of = { "civilite", "nom", "prenom" }) 
@EqualsAndHashCode
public abstract class Personne {
	/** Classe abstraite servent de base aux classes Client et Auteur */

	String civilite;
	@NonNull
	String nom;
	String prenom;

}
