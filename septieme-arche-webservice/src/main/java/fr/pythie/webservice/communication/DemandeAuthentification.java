package fr.pythie.webservice.communication;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * Classe contenant un email et un mot de passe. 
 * Permet de déposer simplement une demande d'authentification d'un client.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DemandeAuthentification {
	
	@NonNull
	String email;
	@NonNull
	String motDePasse;
	
	/**
	 * Constructeur permettant de construire un objet vide.
	 * 
	 * @since 1.0
	 */
	public DemandeAuthentification() {
		super();
	}

	/**
	 * Constructeur permettant de construire un objet complet.
	 * 
	 * @param email L'email envoyé.
	 * @param motDePasse Le mot de passe envoyé.
	 * 
	 * @since 1.0
	 */
	public DemandeAuthentification(@NonNull String email, @NonNull String motDePasse) {
		super();
		this.email = email;
		this.motDePasse = motDePasse;
	}

	/**
	 * Retourne l'email envoyé.
	 * 
	 * @return email L'email envoyé.
	 * 
	 * @see DemandeAuthentification#setEmail(String)
	 * 
	 * @since 1.0
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Modifie l'email envoyé.
	 * 
	 * @param email Le nouvel email envoyé.
	 * 
	 * @see DemandeAuthentification#getEmail()
	 * 
	 * @since 1.0
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retourne le mot de passe envoyé.
	 *  
	 * @return motDePasse Le mot de passe envoyé.
	 * 
	 * @see DemandeAuthentification#setMotDePasse(String)
	 * 
	 * @since 1.0
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * Modifie le mot de passe envoyé.
	 * 
	 * @param motDePasse Le nouveau mot de passe envoyé.
	 * 
	 * @see DemandeAuthentification#getMotDePasse()
	 * 
	 * @since 1.0
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((motDePasse == null) ? 0 : motDePasse.hashCode());
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
		DemandeAuthentification other = (DemandeAuthentification) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (motDePasse == null) {
			if (other.motDePasse != null)
				return false;
		} else if (!motDePasse.equals(other.motDePasse))
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
		return "DemandeAuthentification [email=" + email + ", motDePasse=" + motDePasse + "]";
	}

}
