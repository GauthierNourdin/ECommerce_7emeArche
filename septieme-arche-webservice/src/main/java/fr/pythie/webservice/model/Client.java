package fr.pythie.webservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * Classe modèle pour les clients.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Client extends Personne {

	@NonNull
	String email;
	@NonNull
	String motDePasse;
	String numeroCarte;
	String dateDeValidite;
	String cvc;
	@NonNull
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Adresse adresseFacturation = new Adresse();
	@NonNull
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Adresse adresseLivraison = new Adresse();
	@JsonIgnoreProperties( "client" )
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	List<Consultation> consultations;
	@JsonIgnoreProperties( "client" )
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	List<Commande> commandes;

	/**
	 * 
	 */
	public Client() {
		super();
	}
	
	/**
	 * Constructeur avec tous les paramètres obligatoires
	 * @param nom
	 * @param email
	 * @param motDePasse
	 * @param adresseFacturation
	 * @param adresseLivraison
	 */
	public Client(@NonNull String nom, @NonNull String email, @NonNull String motDePasse,
			@NonNull Adresse adresseFacturation, @NonNull Adresse adresseLivraison) {
		super(nom);
		this.email = email;
		this.motDePasse = motDePasse;
		this.adresseFacturation = adresseFacturation;
		this.adresseLivraison = adresseLivraison;
	}
	
	/**
	 * Constructeur complet mis à part l'ID
	 * @param civilite
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param motDePasse
	 * @param numeroCarte
	 * @param dateDeValidite
	 * @param cvc
	 * @param adresseFacturation
	 * @param adresseLivraison
	 * @param consultations
	 * @param commandes
	 */
	public Client(String civilite, @NonNull String nom, String prenom, @NonNull String email,
			@NonNull String motDePasse, String numeroCarte, String dateDeValidite, String cvc,
			@NonNull Adresse adresseFacturation, @NonNull Adresse adresseLivraison, List<Consultation> consultations,
			List<Commande> commandes) {
		super(civilite, nom, prenom);
		this.email = email;
		this.motDePasse = motDePasse;
		this.numeroCarte = numeroCarte;
		this.dateDeValidite = dateDeValidite;
		this.cvc = cvc;
		this.adresseFacturation = adresseFacturation;
		this.adresseLivraison = adresseLivraison;
		this.consultations = consultations;
		this.commandes = commandes;
	}

	/**
	 * Constructeur complet
	 * @param id
	 * @param civilite
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param motDePasse
	 * @param numeroCarte
	 * @param dateDeValidite
	 * @param cvc
	 * @param adresseFacturation
	 * @param adresseLivraison
	 * @param consultations
	 * @param commandes
	 */
	public Client(long id, String civilite, @NonNull String nom, String prenom, @NonNull String email,
			@NonNull String motDePasse, String numeroCarte, String dateDeValidite, String cvc,
			@NonNull Adresse adresseFacturation, @NonNull Adresse adresseLivraison, List<Consultation> consultations,
			List<Commande> commandes) {
		super(id, civilite, nom, prenom);
		this.email = email;
		this.motDePasse = motDePasse;
		this.numeroCarte = numeroCarte;
		this.dateDeValidite = dateDeValidite;
		this.cvc = cvc;
		this.adresseFacturation = adresseFacturation;
		this.adresseLivraison = adresseLivraison;
		this.consultations = consultations;
		this.commandes = commandes;
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
	public Adresse getAdresseFacturation() {
		return adresseFacturation;
	}

	/**
	 * 
	 * @param adresseFacturation
	 */
	public void setAdresseFacturation(Adresse adresseFacturation) {
		this.adresseFacturation = adresseFacturation;
	}

	/**
	 * 
	 * @return
	 */
	public Adresse getAdresseLivraison() {
		return adresseLivraison;
	}

	/**
	 * 
	 * @param adresseLivraison
	 */
	public void setAdresseLivraison(Adresse adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}

	/**
	 * 
	 * @return
	 */
	public List<Consultation> getConsultations() {
		return consultations;
	}

	/**
	 * 
	 * @param consultations
	 */
	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}

	/**
	 * 
	 * @return
	 */
	public List<Commande> getCommandes() {
		return commandes;
	}

	/**
	 * 
	 * @param commandes
	 */
	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((adresseFacturation == null) ? 0 : adresseFacturation.hashCode());
		result = prime * result + ((adresseLivraison == null) ? 0 : adresseLivraison.hashCode());
		result = prime * result + ((commandes == null) ? 0 : commandes.hashCode());
		result = prime * result + ((consultations == null) ? 0 : consultations.hashCode());
		result = prime * result + ((cvc == null) ? 0 : cvc.hashCode());
		result = prime * result + ((dateDeValidite == null) ? 0 : dateDeValidite.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((motDePasse == null) ? 0 : motDePasse.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (adresseFacturation == null) {
			if (other.adresseFacturation != null)
				return false;
		} else if (!adresseFacturation.equals(other.adresseFacturation))
			return false;
		if (adresseLivraison == null) {
			if (other.adresseLivraison != null)
				return false;
		} else if (!adresseLivraison.equals(other.adresseLivraison))
			return false;
		if (commandes == null) {
			if (other.commandes != null)
				return false;
		} else if (!commandes.equals(other.commandes))
			return false;
		if (consultations == null) {
			if (other.consultations != null)
				return false;
		} else if (!consultations.equals(other.consultations))
			return false;
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
		return "Client [email=" + email + ", motDePasse=" + motDePasse + ", numeroCarte=" + numeroCarte
				+ ", dateDeValidite=" + dateDeValidite + ", cvc=" + cvc + ", adresseFacturation=" + adresseFacturation
				+ ", adresseLivraison=" + adresseLivraison + ", consultations=" + consultations + ", commandes="
				+ commandes + ", toString()=" + super.toString() + "]";
	}
	
}
