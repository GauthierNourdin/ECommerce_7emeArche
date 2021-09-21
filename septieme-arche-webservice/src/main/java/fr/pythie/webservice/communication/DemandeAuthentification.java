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
	 * 
	 */
	public DemandeAuthentification() {
		super();
	}

	/**
	 * 
	 * @param email
	 * @param motDePasse
	 */
	public DemandeAuthentification(@NonNull String email, @NonNull String motDePasse) {
		super();
		this.email = email;
		this.motDePasse = motDePasse;
	}

	/**
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * 
	 * @param motDePasse
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/**
	 * 
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
	 * 
	 */
	@Override
	public String toString() {
		return "DemandeAuthentification [email=" + email + ", motDePasse=" + motDePasse + "]";
	}

}
