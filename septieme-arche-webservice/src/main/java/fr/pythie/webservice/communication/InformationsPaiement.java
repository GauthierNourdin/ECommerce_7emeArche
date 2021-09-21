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
	 * 
	 */
	public InformationsPaiement() {
		super();
	}

	/**
	 * 
	 * @param numeroCarte
	 * @param dateDeValidite
	 * @param cvc
	 * @param idClient
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
	 * 
	 * @return
	 */
	public String getNumeroCarte() {
		return numeroCarte;
	}

	/**
	 * 
	 * @param numeroCarte
	 */
	public void setNumeroCarte(String numeroCarte) {
		this.numeroCarte = numeroCarte;
	}

	/**
	 * 
	 * @return
	 */
	public String getDateDeValidite() {
		return dateDeValidite;
	}

	/**
	 * 
	 * @param dateDeValidite
	 */
	public void setDateDeValidite(String dateDeValidite) {
		this.dateDeValidite = dateDeValidite;
	}

	/**
	 * 
	 * @return
	 */
	public String getCvc() {
		return cvc;
	}

	/**
	 * 
	 * @param cvc
	 */
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	/**
	 * 
	 * @return
	 */
	public Long getIdClient() {
		return idClient;
	}

	/**
	 * 
	 * @param idClient
	 */
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	/**
	 * 
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
	 * 
	 */
	@Override
	public String toString() {
		return "InformationsPaiement [numeroCarte=" + numeroCarte + ", dateDeValidite=" + dateDeValidite + ", cvc="
				+ cvc + ", idClient=" + idClient + "]";
	}
	
}
