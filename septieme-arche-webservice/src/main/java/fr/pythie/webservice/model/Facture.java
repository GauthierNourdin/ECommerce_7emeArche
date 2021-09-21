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
	 * Constructeur permettant de construire une facture vide.
	 * 
	 * @since 1.0
	 */
	public Facture() {
		super();
	}
	
	/**
	 * Constructeur permettant de construire une facture avec les informations obligatoires.
	 * 
	 * @param status Le status actuel.
	 * @param commande La commande à laquelle est attachée la facture.
	 * 
	 * @since 1.0
	 */
	public Facture(@NonNull String status, @NonNull Commande commande) {
		super();
		this.status = status;
		this.commande = commande;
	}

	/**
	 * Constructeur permettant de construire une facture complète mais sans identifiant.
	 * 
	 * @param numero Le numéro commercial.
	 * @param status Le status actuel.
	 * @param commande La commande à laquelle est attachée la facture.
	 * 
	 * @since 1.0
	 */
	public Facture(String numero, @NonNull String status, @NonNull Commande commande) {
		super();
		this.numero = numero;
		this.status = status;
		this.commande = commande;
	}
	
	/**
	 * Constructeur permettant de construire une facture complète.
	 * 
	 * @param id L'identifiant.
	 * @param numero Le numéro commercial.
	 * @param status Le status actuel.
	 * @param commande La commande à laquelle est attachée la facture.
	 * 
	 * @since 1.0
	 */
	public Facture(long id, String numero, @NonNull String status, @NonNull Commande commande) {
		super();
		this.id = id;
		this.numero = numero;
		this.status = status;
		this.commande = commande;
	}

	/**
	 * Retourne l'identifiant.
	 * 
	 * @return id L'identifiant.
	 * 
	 * @see Facture#setId(long)
	 * 
	 * @since 1.0
	 */
	public long getId() {
		return id;
	}

	/**
	 * Modifie l'identifiant.
	 * 
	 * @param id Le nouvel identifiant.
	 * 
	 * @see Facture#getId()
	 * 
	 * @since 1.0
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Retourne le numéro commercial.
	 * 
	 * @return numero Le numéro commercial.
	 * 
	 * @see Facture#setNumero(String)
	 * 
	 * @since 1.0
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Modifie le numéro commercial.
	 * 
	 * @param numero Le nouveau numéro commercial.
	 * 
	 * @see Facture#getNumero()
	 * 
	 * @since 1.0
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Retourne le status actuel.
	 * 
	 * @return status Le status actuel.
	 * 
	 * @see Facture#setStatus(String)
	 * 
	 * @since 1.0
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Modifie le status actuel.
	 * 
	 * @param status Le nouveau status actuel.
	 *  
	 * @see Facture#getStatus()
	 * 
	 * @since 1.0
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Retourne la commande à laquelle est attachée la facture.
	 * 
	 * @return commande La commande à laquelle est attachée la facture.
	 * 
	 * @see Facture#setCommande(Commande)
	 * 
	 * @since 1.0
	 */
	public Commande getCommande() {
		return commande;
	}

	/**
	 * Modifie la commande à laquelle est attachée la facture.
	 * 
	 * @param commande La nouvelle commande à laquelle est attachée la facture.
	 * 
	 * @see Facture#getCommande()
	 * 
	 * @since 1.0
	 */
	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	/**
	 * Méthode pour construire le numéro de facture. Ce numéro se compose du numéro de 
	 * commande, d'un tiret de séparation et du rang de la facture sur deux chiffres
	 * (établi à l'aide du nombre de factures actuelles associées à la commande.
	 * Se génère avant la sauvegarde de la facture en base de données.
	 * 
	 * @since 1.0
	 */
	public void definirNumero() {
		String stringNumero;
		stringNumero = commande.getNumero() + "-"
				+ String.format("%2s", String.valueOf(commande.getFactures().size())).replace(' ', '0');
		this.setNumero(stringNumero);
	}

	/**
	 * Calcule le code hash.
	 * 
	 * @return result Le code hash.
	 * 
	 * @since 1.0
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
	 * Définit les conditions dans lesquelles un autre objet est égal à cette facture.
	 *
	 * @param obj L'objet auquel on veut comparer.
	 *
	 * @return true Si les deux objets sont identiques, false sinon.
	 * 
	 * @since 1.0
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
	 * Produit la chaîne de caractères réprésentant la facture.
	 * On n'y intègre pas la commande associée.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "Facture [id=" + id + ", numero=" + numero + ", status=" + status + "]";
	}
	
}
