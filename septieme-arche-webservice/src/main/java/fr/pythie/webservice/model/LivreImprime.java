package fr.pythie.webservice.model;

import java.time.LocalDate;

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
@ToString(of = { "quantiteStock", "dateFinTirage", "dateReimpression", "poids", "unitePoids", "longueur", "largeur", "epaisseur", "uniteLongueur" }) 
@EqualsAndHashCode(callSuper = true)
@Entity
public class LivreImprime extends Livre {
	/** Classe pour les livres imprimés */

	int quantiteStock;
	@NonNull
	LocalDate dateFinTirage = LocalDate.now();
	@NonNull
	LocalDate dateReimpression = LocalDate.now();
	double poids;
	String unitePoids;
	double longueur;
	double largeur;
	double epaisseur;
	String uniteLongueur;

}
