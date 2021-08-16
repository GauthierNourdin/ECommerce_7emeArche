package fr.pythie.webservice.model;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@ToString(of = { "id", "email", "motDePasse", "numeroCarte", "dateDeValidite", "cvc" })
@EqualsAndHashCode(callSuper = true)
@Entity
public class Client extends Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@NonNull
	String email;
	@NonNull
	String motDePasse;
	String numeroCarte;
	String dateDeValidite;
	String cvc;
	@NonNull
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	Adresse adresseFacturation = new Adresse();
	@NonNull
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	Adresse adresseLivraison = new Adresse();
	@JsonIgnoreProperties( "client" )
	Panier panier = new Panier();
	@JsonIgnoreProperties( "client" )
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
	ArrayList<Consultation> consultations = new ArrayList<Consultation>();
	@JsonIgnoreProperties( "client" )
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
	ArrayList<Commande> commandes = new ArrayList<Commande>();

}
