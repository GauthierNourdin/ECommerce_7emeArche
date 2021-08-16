package fr.pythie.webservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@ToString(of = { "id", "quantiteSouhaitee" }) 
@EqualsAndHashCode
@Entity
public class LignePanier {
	/** Classe pour les lignes de panier */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	int quantiteSouhaitee;
	@NonNull
	@JsonIgnoreProperties( { "consultations", "lignesCommande" } )
	Article article;
	@NonNull
	@JsonIgnore
	Panier panier;

}
