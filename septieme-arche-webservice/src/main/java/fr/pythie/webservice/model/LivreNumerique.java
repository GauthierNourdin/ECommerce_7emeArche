package fr.pythie.webservice.model;

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
@ToString(of = { "espace", "unite" }) 
@EqualsAndHashCode(callSuper = true)
@Entity
public class LivreNumerique extends Livre {
	/** Classe pour les livres numériques */


	@NonNull
	String format;
	double espace;
	String unite;

}
