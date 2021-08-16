package fr.pythie.webservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@ToString(of = { "id", "numero", "status" }) 
@EqualsAndHashCode
@Entity
public class Facture {
	/** Classe pour les factures */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String numero;
	@NonNull
	String status;
	@NonNull
	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Commande commande = new Commande();

	/*
	 * Méthode pour construire le numéro de facture à partir du numéro de la commande
	 * de référence et du rang de la facture pour la commande (à fin de permettre les
	 * relances ou le paiement en plusieurs fois). Le numéro se compose du numéro de 
	 * commande, d'un tiret de séparation et du rang de la facture sur deux chiffres
	 * (établi à l'aide du nombre de factures, actuelles, associées à la commande.
	 * Se génère avant la sauvegarde de la facture en base de données.
	 */
	public void definirNumero() {
		String stringNumero;
		stringNumero = commande.getNumero() + "-"
				+ String.format("%2s", String.valueOf(commande.getFactures().size())).replace(' ', '0');
		this.setNumero(stringNumero);
	}
	
}
