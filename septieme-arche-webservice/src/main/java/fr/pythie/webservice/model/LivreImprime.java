package fr.pythie.webservice.model;

import java.time.LocalDate;

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
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(of = { "id", "quantiteStock", "dateFinTirage", "dateReimpression", "poids", "unitePoids", "longueur", "largeur", "epaisseur", "uniteLongueur" }) 
@EqualsAndHashCode(callSuper = true)
@Entity
public class LivreImprime extends Livre {
	/** Classe pour les livres imprimés */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
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
