package fr.pythie.webservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
@ToString(of = { "id", "quantiteCommandee", "prixHT", "prixTTC" }) 
@EqualsAndHashCode
@Entity
public class LigneCommande {
	/** Classe pour les lignes de commande */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	int quantiteCommandee;
	int prixHT;
	int prixTTC;
	@NonNull
	@JsonIgnoreProperties( { "consultations", "lignesCommande" } )
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Article article;
	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Commande commande = new Commande();

	// Constructeur complet mis à part l'ID
	public LigneCommande(int quantiteCommandee, int prixHT, int prixTTC, @NonNull Article article,
			Commande commande) {
		super();
		this.quantiteCommandee = quantiteCommandee;
		this.prixHT = prixHT;
		this.prixTTC = prixTTC;
		this.article = article;
		this.commande = commande;
	}
	
}
