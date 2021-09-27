package fr.pythie.webservice.communication;

import fr.pythie.webservice.model.Consultation;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * Classe contenant une consultation et l'id du client auquel elle est associée. 
 * Permet de transmettre simplement une consultation d'un client identifié.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConsultationAvecIdClient {

	@NonNull
	Consultation consultation;
	@NonNull
	Long idClient;
	
	/**
	 * Constructeur permettant de construire un objet vide.
	 * 
	 * @since 1.0
	 */
	public ConsultationAvecIdClient() {
		super();
	}

	/**
	 * Constructeur permettant de construire un objet complet.
	 * 
	 * @param consultation La consultation à sauvegarder.
	 * @param idClient L'identifiant du client ayant fait cette consultation.
	 * 
	 * @since 1.0
	 */
	public ConsultationAvecIdClient(@NonNull Consultation consultation, @NonNull Long idClient) {
		super();
		this.consultation = consultation;
		this.idClient = idClient;
	}

	/**
	 * Retourne la consultation à sauvegarder.
	 * 
	 * @return consultation La consultation à sauvegarder.
	 * 
	 * @see ConsultationAvecIdClient#setConsultation(Consultation)
	 * 
	 * @since 1.0
	 */
	public Consultation getConsultation() {
		return consultation;
	}

	/**
	 * Modifie la consultation à sauvegarder.
	 * 
	 * @param consultation La nouvelle consultation à sauvegarder.
	 * 
	 * @see ConsultationAvecIdClient#getConsultation()
	 * 
	 * @since 1.0
	 */
	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	/**
	 * Retourne l'identifiant du client ayant fait cette consultation.
	 * 
	 * @return idClient L'identifiant du client ayant fait cette consultation.
	 * 
	 * @see ConsultationAvecIdClient#setIdClient(Long)
	 * 
	 * @since 1.0
	 */
	public Long getIdClient() {
		return idClient;
	}

	/**
	 * Modifie l'identifiant du client ayant fait cette consultation.
	 * 
	 * @param idClient Le nouvel identifiant du client ayant fait cette consultation.
	 *  
	 * @see ConsultationAvecIdClient#getIdClient()
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
		result = prime * result + ((consultation == null) ? 0 : consultation.hashCode());
		result = prime * result + ((idClient == null) ? 0 : idClient.hashCode());
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
     * Produit la chaîne de caractères réprésentant cet objet.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "ConsultationAvecIdClient [consultation=" + consultation + ", idClient=" + idClient + "]";
	}
	
}
