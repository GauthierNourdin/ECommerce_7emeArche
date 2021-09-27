package fr.pythie.webservice.communication;

import fr.pythie.webservice.model.Commande;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * Classe contenant une commande et l'id du client auquel elle est associée. 
 * Permet de transmettre simplement une commande d'un client identifié.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommandeAvecIdClient {

	@NonNull
	Commande commande;
	@NonNull
	Long idClient;
	
	/**
	 * Constructeur permettant de construire un objet vide.
	 * 
	 * @since 1.0
	 */
	public CommandeAvecIdClient() {
		super();
	}

	/**
	 * Constructeur permettant de construire un objet complet.
	 * 
	 * @param commande La commande à enregistrer.
	 * @param idClient L'identifiant du client passant la commande.
	 * 
	 * @since 1.0
	 */
	public CommandeAvecIdClient(@NonNull Commande commande, @NonNull Long idClient) {
		super();
		this.commande = commande;
		this.idClient = idClient;
	}

	/**
	 * Retourne la commande à enregistrer.
	 * 
	 * @return commande La commande à enregistrer.
	 * 
	 * @see CommandeAvecIdClient#setCommande(Commande)
	 * 
	 * @since 1.0
	 */
	public Commande getCommande() {
		return commande;
	}

	/**
	 * Modifie la commande à enregistrer.
	 * 
	 * @param commande La nouvelle commande à enregistrer.
	 * 
	 * @see CommandeAvecIdClient#getCommande()
	 * 
	 * @since 1.0
	 */
	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	/**
	 * Retourne l'identifiant du client passant la commande.
	 * 
	 * @return idClient L'identifiant du client passant la commande.
	 * 
	 * @see CommandeAvecIdClient#setIdClient(Long)
	 * 
	 * @since 1.0
	 */
	public Long getIdClient() {
		return idClient;
	}

	/**
	 * Modifie l'identifiant du client passant la commande.
	 * 
	 * @param idClient Le nouvel identifiant du client passant la commande.
	 * 
	 * @see CommandeAvecIdClient#getIdClient()
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
		result = prime * result + ((commande == null) ? 0 : commande.hashCode());
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
     * Produit la chaîne de caractères réprésentant cet objet.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "CommandeAvecIdClient [commande=" + commande + ", idClient=" + idClient + "]";
	}
	
}
