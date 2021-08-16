package fr.pythie.webservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@ToString(of = { "email", "motDePasse", "numeroCarte", "dateDeValidite", "cvc" })
@EqualsAndHashCode(callSuper = true)
@Entity
public class Client extends Personne {

	@NonNull
	String email;
	@NonNull
	String motDePasse;
	String numeroCarte;
	String dateDeValidite;
	String cvc;
	@NonNull
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Adresse adresseFacturation = new Adresse();
	@NonNull
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Adresse adresseLivraison = new Adresse();
//	@JsonIgnoreProperties( "client" )
//	Panier panier = new Panier();
	@JsonIgnoreProperties( "client" )
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	List<Consultation> consultations;
	@JsonIgnoreProperties( "client" )
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	List<Commande> commandes;

}
