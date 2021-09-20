package fr.pythie.webservice.communication;

import fr.pythie.webservice.model.Consultation;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * Classe contenant une consultation et l'id du client auquel elle est associée. 
 * Permet de transmettre simplement une consultation d'un client identifié.
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConsultationAvecIdClient {

	@NonNull
	Consultation consultation;
	@NonNull
	Long idClient;
	
	/**
	 * 
	 */
	public ConsultationAvecIdClient() {
		super();
	}

	/**
	 * 
	 * @param consultation
	 * @param idClient
	 */
	public ConsultationAvecIdClient(@NonNull Consultation consultation, @NonNull Long idClient) {
		super();
		this.consultation = consultation;
		this.idClient = idClient;
	}

	/**
	 * 
	 * @return
	 */
	public Consultation getConsultation() {
		return consultation;
	}

	/**
	 * 
	 * @param consultation
	 */
	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
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
		result = prime * result + ((consultation == null) ? 0 : consultation.hashCode());
		result = prime * result + ((idClient == null) ? 0 : idClient.hashCode());
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
		ConsultationAvecIdClient other = (ConsultationAvecIdClient) obj;
		if (consultation == null) {
			if (other.consultation != null)
				return false;
		} else if (!consultation.equals(other.consultation))
			return false;
		if (idClient == null) {
			if (other.idClient != null)
				return false;
		} else if (!idClient.equals(other.idClient))
			return false;
		return true;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "ConsultationAvecIdClient [consultation=" + consultation + ", idClient=" + idClient + "]";
	}
	
}
