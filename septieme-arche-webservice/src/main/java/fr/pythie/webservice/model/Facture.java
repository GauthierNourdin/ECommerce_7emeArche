package fr.pythie.webservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * Classe modèle pour les factures.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Facture {

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

	/**
	 * 
	 */
	public Facture() {
		super();
	}
	
	/**
	 * 
	 * @param status
	 * @param commande
	 */
	public Facture(@NonNull String status, @NonNull Commande commande) {
		super();
		this.status = status;
		this.commande = commande;
	}

	/**
	 * Constructeur complet mis à part l'ID
	 * @param numero
	 * @param status
	 * @param commande
	 */
	public Facture(String numero, @NonNull String status, @NonNull Commande commande) {
		super();
		this.numero = numero;
		this.status = status;
		this.commande = commande;
	}
	
	/**
	 * 
	 * @param id
	 * @param numero
	 * @param status
	 * @param commande
	 */
	public Facture(long id, String numero, @NonNull String status, @NonNull Commande commande) {
		super();
		this.id = id;
		this.numero = numero;
		this.status = status;
		this.commande = commande;
	}

	/**
	 * 
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * 
	 * @param numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * 
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return
	 */
	public Commande getCommande() {
		return commande;
	}

	/**
	 * 
	 * @param commande
	 */
	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	/**
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

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commande == null) ? 0 : commande.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Facture other = (Facture) obj;
		if (commande == null) {
			if (other.commande != null)
				return false;
		} else if (!commande.equals(other.commande))
			return false;
		if (id != other.id)
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Facture [id=" + id + ", numero=" + numero + ", status=" + status + "]";
	}
	
}
