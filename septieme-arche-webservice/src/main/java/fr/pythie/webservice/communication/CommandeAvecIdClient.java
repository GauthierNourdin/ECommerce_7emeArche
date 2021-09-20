package fr.pythie.webservice.communication;

import fr.pythie.webservice.model.Commande;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * Classe contenant une commande et l'id du client auquel elle est associée. 
 * Permet de transmettre simplement une commande d'un client identifié.
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommandeAvecIdClient {

	@NonNull
	Commande commande;
	@NonNull
	Long idClient;
	
	/**
	 * 
	 */
	public CommandeAvecIdClient() {
		super();
	}

	/**
	 * 
	 * @param commande
	 * @param idClient
	 */
	public CommandeAvecIdClient(@NonNull Commande commande, @NonNull Long idClient) {
		super();
		this.commande = commande;
		this.idClient = idClient;
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
		result = prime * result + ((commande == null) ? 0 : commande.hashCode());
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
		CommandeAvecIdClient other = (CommandeAvecIdClient) obj;
		if (commande == null) {
			if (other.commande != null)
				return false;
		} else if (!commande.equals(other.commande))
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
		return "CommandeAvecIdClient [commande=" + commande + ", idClient=" + idClient + "]";
	}
	
}
