package fr.pythie.webservice.communication;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * Classe contenant les informations requises pour le paiement.
 * Contient l'identifiant du client, son numéro de carte bancaire, sa date de
 * validité et son cvc.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InformationsPaiement {

	@NonNull
	String numeroCarte;
	@NonNull
	String dateDeValidite;
	@NonNull
	String cvc;
	@NonNull
	Long idClient;
	
	/**
	 * Constructeur permettant de construire un objet vide.
	 * 
	 * @since 1.0
	 */
	public InformationsPaiement() {
		super();
	}

	/**
	 * Constructeur permettant de construire un objet complet.
	 * 
	 * @param numeroCarte Le numéro de carte du client.
	 * @param dateDeValidite La date de validité de la carte du client.
	 * @param cvc Le cvc de la carte du client.
	 * @param idClient L'identifiant du client.
	 * 
	 * @since 1.0
	 */
	public InformationsPaiement(@NonNull String numeroCarte, @NonNull String dateDeValidite, @NonNull String cvc,
			@NonNull Long idClient) {
		super();
		this.numeroCarte = numeroCarte;
		this.dateDeValidite = dateDeValidite;
		this.cvc = cvc;
		this.idClient = idClient;
	}

	/**
	 * Retourne le numéro de carte du client.
	 *
	 * @return numeroCarte Le numéro de carte du client.
	 * 
	 * @see InformationsPaiement#setNumeroCarte(String)
	 * 
	 * @since 1.0
	 */
	public String getNumeroCarte() {
		return numeroCarte;
	}

	/**
	 * Modifie le numéro de carte du client.
	 * 
	 * @param numeroCarte Le nouveau numéro de carte du client.
	 * 
	 * @see InformationsPaiement#getNumeroCarte()
	 * 
	 * @since 1.0
	 */
	public void setNumeroCarte(String numeroCarte) {
		this.numeroCarte = numeroCarte;
	}

	/**
	 * Retourne la date de validité de la carte du client.
	 * 
	 * @return dateDeValidite La date de validité de la carte du client.
	 * 
	 * @see InformationsPaiement#setDateDeValidite(String)
	 * 
	 * @since 1.0
	 */
	public String getDateDeValidite() {
		return dateDeValidite;
	}

	/**
	 * Modifie la date de validité de la carte du client.
	 * 
	 * @param dateDeValidite La nouvelle date de validité de la carte du client.
	 * 
	 * @see InformationsPaiement#getDateDeValidite()
	 * 
	 * @since 1.0
	 */
	public void setDateDeValidite(String dateDeValidite) {
		this.dateDeValidite = dateDeValidite;
	}

	/**
	 * Retourne le cvc de la carte du client.
	 * 
	 * @return cvc Le cvc de la carte du client.
	 * 
	 * @see InformationsPaiement#setCvc(String)
	 * 
	 * @since 1.0
	 */
	public String getCvc() {
		return cvc;
	}

	/**
	 * Modifie le cvc de la carte du client.
	 * 
	 * @param cvc Le nouveau cvc de la carte du client.
	 * 
	 * @see InformationsPaiement#getCvc()
	 * 
	 * @since 1.0
	 */
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	/**
	 * Retourne l'identifiant du client.
	 *  
	 * @return idClient L'identifiant du client.
	 * 
	 * @see InformationsPaiement#setIdClient(Long)
	 * 
	 * @since 1.0
	 */
	public Long getIdClient() {
		return idClient;
	}

	/**
	 * Modifie l'identifiant du client.
	 * 
	 * @param idClient Le nouvel identifiant du client.
	 * 
	 * @see InformationsPaiement#getIdClient()
	 * 
	 * @since 1.0
	 */
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
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
		result = prime * result + ((cvc == null) ? 0 : cvc.hashCode());
		result = prime * result + ((dateDeValidite == null) ? 0 : dateDeValidite.hashCode());
		result = prime * result + ((idClient == null) ? 0 : idClient.hashCode());
		result = prime * result + ((numeroCarte == null) ? 0 : numeroCarte.hashCode());
		return result;
	}

	/**
	 * Définit les conditions dans lesquelles un autre objet est égal à celui-ci.
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
		InformationsPaiement other = (InformationsPaiement) obj;
		if (cvc == null) {
			if (other.cvc != null)
				return false;
		} else if (!cvc.equals(other.cvc))
			return false;
		if (dateDeValidite == null) {
			if (other.dateDeValidite != null)
				return false;
		} else if (!dateDeValidite.equals(other.dateDeValidite))
			return false;
		if (idClient == null) {
			if (other.idClient != null)
				return false;
		} else if (!idClient.equals(other.idClient))
			return false;
		if (numeroCarte == null) {
			if (other.numeroCarte != null)
				return false;
		} else if (!numeroCarte.equals(other.numeroCarte))
			return false;
		return true;
	}

	/**
     * Produit la chaîne de caractères réprésentant cet objet.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "InformationsPaiement [numeroCarte=" + numeroCarte + ", dateDeValidite=" + dateDeValidite + ", cvc="
				+ cvc + ", idClient=" + idClient + "]";
	}
	
}
