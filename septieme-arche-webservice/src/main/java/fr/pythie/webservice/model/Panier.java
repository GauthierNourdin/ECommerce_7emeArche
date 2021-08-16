package fr.pythie.webservice.model;

import java.util.ArrayList;

import javax.persistence.Entity;

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
@ToString(of = { }) 
@EqualsAndHashCode
@Entity
public class Panier {
	/** Classe pour représenter le panier d'un client */

	@NonNull
	@JsonIgnore
	Client client = new Client();
	@JsonIgnoreProperties( "panier" )
	ArrayList<LignePanier> lignesPanier = new ArrayList<LignePanier>();

}
