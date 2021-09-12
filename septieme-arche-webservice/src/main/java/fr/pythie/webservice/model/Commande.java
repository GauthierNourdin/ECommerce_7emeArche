package fr.pythie.webservice.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ToString(of = { "id", "numero", "status", "date" }) 
@EqualsAndHashCode
@Entity
public class Commande {
	/** Classe pour les commandes */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String numero;
	@NonNull
	String status;
	@NonNull
	LocalDateTime date = LocalDateTime.now();
	@JsonIgnoreProperties( "commande" )
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "commande")
	List<LigneCommande> lignesCommande;
	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Client client = new Client();
	@JsonIgnoreProperties( "commande" )
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "commande")
	List<Facture> factures;

	/*
	 * Méthode pour construire le numéro de commande à partir des autres
	 * informations. On veut que le numéro contienne douze chiffres permettant de
	 * reconstruire la date et l'heure du passage ainsi que 8 chiffres pour l'id du
	 * client. Les deux groupes sont séparés par un tiret. S'établit avant la sauvegarde
	 * en base de données.
	 */
	public void definirNumero() {
		String stringNumero;
		DateTimeFormatter formatNumero = DateTimeFormatter.ofPattern("yyMMddHHmmss");
		stringNumero = date.format(formatNumero) + "-"
				+ String.format("%8s", String.valueOf(client.getId())).replace(' ', '0');
		this.setNumero(stringNumero);
	}

	// Constructeur complet mis à part l'ID
	public Commande(String numero, @NonNull String status, @NonNull LocalDateTime date,
			List<LigneCommande> lignesCommande, Client client, List<Facture> factures) {
		super();
		this.numero = numero;
		this.status = status;
		this.date = date;
		this.lignesCommande = lignesCommande;
		this.client = client;
		this.factures = factures;
	}
	
}
