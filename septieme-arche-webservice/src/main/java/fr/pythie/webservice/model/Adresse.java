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
@ToString(of = { "id", "numeroRue", "nomRue", "codePostal", "ville", "pays", "complement" }) 
@EqualsAndHashCode
@Entity
public class Adresse {
	/** Classe pour les adresses */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@NonNull
	String numeroRue;
	@NonNull
	String nomRue;
	@NonNull
	String codePostal;
	@NonNull
	String ville;
	String pays;
	String complement;
	
	// Constructeur complet mis à part l'ID
	public Adresse(@NonNull String numeroRue, @NonNull String nomRue, @NonNull String codePostal, @NonNull String ville,
			String pays, String complement) {
		super();
		this.numeroRue = numeroRue;
		this.nomRue = nomRue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
		this.complement = complement;
	}

}
