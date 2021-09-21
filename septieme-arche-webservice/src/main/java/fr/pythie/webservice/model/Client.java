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
	 * Constructeur permettant de construire un client vide.
	 * 
	 * @since 1.0
	 */
	public Client() {
		super();
	}
	
	/**
	 * Constructeur permettant de construire un client avec les informations obligatoires.
	 * 
	 * @param nom Le nom.
	 * @param email L'adresse email.
	 * @param motDePasse Le mot de passe.
	 * @param adresseFacturation L'adresse de facturation.
	 * @param adresseLivraison L'adresse de livraison.
	 * 
	 * @since 1.0
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
	 * Constructeur permettant de construire un client complet mais sans identifiant.
	 * 
	 * @param civilite La civilité.
	 * @param nom Le nom.
	 * @param prenom Le ou les prénoms.
	 * @param email L'adresse email.
	 * @param motDePasse Le mot de passe.
	 * @param numeroCarte Le numéro de la carte bancaire du client.
	 * @param dateDeValidite La date de validité de la carte bancaire du client.
	 * @param cvc Le cvc de la carte bancaire du client.
	 * @param adresseFacturation L'adresse de facturation.
	 * @param adresseLivraison L'adresse de livraison.
	 * @param consultations La liste des consultations faites par ce client.
	 * @param commandes La liste des commandes passées par ce client.
	 * 
	 * @since 1.0
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
	 * Constructeur permettant de construire un client complet.
	 * 
	 * @param id L'identifiant.
	 * @param civilite La civilité.
	 * @param nom Le nom.
	 * @param prenom Le ou les prénoms.
	 * @param email L'adresse email.
	 * @param motDePasse Le mot de passe.
	 * @param numeroCarte Le numéro de la carte bancaire du client.
	 * @param dateDeValidite La date de validité de la carte bancaire du client.
	 * @param cvc Le cvc de la carte bancaire du client.
	 * @param adresseFacturation L'adresse de facturation.
	 * @param adresseLivraison L'adresse de livraison.
	 * @param consultations La liste des consultations faites par ce client.
	 * @param commandes La liste des commandes passées par ce client.
	 * 
	 * @since 1.0
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
	 * Retourne l'adresse email.
	 * 
	 * @return email L'adresse email.
	 * 
	 * @see Client#setEmail(String)
	 * 
	 * @since 1.0
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Modifie l'adresse email.
	 * 
	 * @param email La nouvelle adresse email.
	 * 
	 * @see Client#getEmail()
	 * 
	 * @since 1.0
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retourne le mot de passe.
	 * 
	 * @return motDePasse Le mot de passe.
	 * 
	 * @see Client#setMotDePasse(String)
	 * 
	 * @since 1.0
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * Modifie le mot de passe.
	 * 
	 * @param motDePasse Le nouveau mot de passe.
	 * 
	 * @see Client#getMotDePasse()
	 * 
	 * @since 1.0
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/**
	 * Retourne le numéro de la carte bancaire du client.
	 * 
	 * @return numeroCarte Le numéro de la carte bancaire du client.
	 * 
	 * @see Client#setNumeroCarte(String)
	 * 
	 * @since 1.0
	 */
	public String getNumeroCarte() {
		return numeroCarte;
	}

	/**
	 * Modifie le numéro de la carte bancaire du client.
	 * 
	 * @param numeroCarte Le nouveau numéro de la carte bancaire du client.
	 * 
	 * @see Client#getNumeroCarte()
	 * 
	 * @since 1.0
	 */
	public void setNumeroCarte(String numeroCarte) {
		this.numeroCarte = numeroCarte;
	}

	/**
	 * Retourne la date de validité de la carte bancaire du client.
	 * 
	 * @return dateDeValidite La date de validité de la carte bancaire du client.
	 * 
	 * @see Client#setDateDeValidite(String)
	 * 
	 * @since 1.0
	 */
	public String getDateDeValidite() {
		return dateDeValidite;
	}

	/**
	 * Modifie la date de validité de la carte bancaire du client.
	 * 
	 * @param dateDeValidite La nouvelle date de validité de la carte bancaire du client.
	 * 
	 * @see Client#getDateDeValidite()
	 * 
	 * @since 1.0	 
	 */
	public void setDateDeValidite(String dateDeValidite) {
		this.dateDeValidite = dateDeValidite;
	}

	/**
	 * Retourne le cvc de la carte bancaire du client.
	 * 
	 * @return cvc Le cvc de la carte bancaire du client.
	 * 
	 * @see Client#setCvc(String)
	 * 
	 * @since 1.0
	 */
	public String getCvc() {
		return cvc;
	}

	/**
	 * Modifie le cvc de la carte bancaire du client.
	 * 
	 * @param cvc Le nouveau cvc de la carte bancaire du client.
	 * 
	 * @see Client#getCvc()
	 * 
	 * @since 1.0
	 */
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	/**
	 * Retourne l'adresse de facturation.
	 * 
	 * @return adresseFacturation L'adresse de facturation.
	 * 
	 * @see Client#setAdresseFacturation(Adresse)
	 * 
	 * @since 1.0
	 */
	public Adresse getAdresseFacturation() {
		return adresseFacturation;
	}

	/**
	 * Modifie l'adresse de facturation.
	 * 
	 * @param adresseFacturation La nouvelle adresse de facturation.
	 * 
	 * @see Client#getAdresseFacturation()
	 * 
	 * @since 1.0
	 */
	public void setAdresseFacturation(Adresse adresseFacturation) {
		this.adresseFacturation = adresseFacturation;
	}

	/**
	 * Retourne l'adresse de livraison.
	 * 
	 * @return adresseLivraison L'adresse de livraison.
	 * 
	 * @see Client#setAdresseLivraison(Adresse)
	 * 
	 * @since 1.0
	 */
	public Adresse getAdresseLivraison() {
		return adresseLivraison;
	}

	/**
	 * Modifie l'adresse de livraison.
	 * 
	 * @param adresseLivraison La nouvelle adresse de livraison.
	 * 
	 * @see Client#getAdresseLivraison()
	 * 
	 * @since 1.0
	 */
	public void setAdresseLivraison(Adresse adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}

	/**
	 * Retourne la liste des consultations faites par ce client.
	 * 
	 * @return consultations La liste des consultations faites par ce client.
	 * 
	 * @see Client#setConsultations(List)
	 * 
	 * @since 1.0
	 */
	public List<Consultation> getConsultations() {
		return consultations;
	}

	/**
	 * Modifie la liste des consultations faites par ce client.
	 * 
	 * @param consultations La nouvelle liste des consultations faites par ce client.
	 * 
	 * @see Client#getConsultations()
	 * 
	 * @since 1.0
	 */
	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}

	/**
	 * Retourne la liste des commandes passées par ce client.
	 * 
	 * @return commandes La liste des commandes passées par ce client.
	 * 
	 * @see Client#setCommandes(List)
	 * 
	 * @since 1.0
	 */
	public List<Commande> getCommandes() {
		return commandes;
	}

	/**
	 * Modifie la liste des commandes passées par ce client.
	 * 
	 * @param commandes La nouvelle liste des commandes passées par ce client.
	 * 
	 * @see Client#getCommandes()
	 * 
	 * @since 1.0
	 */
	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
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
	 * Définit les conditions dans lesquelles un autre objet est égal à ce client.
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
	 * Produit la chaîne de caractères réprésentant le client.
	 * On n'y intègre pas les adresses, les consultations et les commandes associées.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "Client [email=" + email + ", motDePasse=" + motDePasse + ", numeroCarte=" + numeroCarte
				+ ", dateDeValidite=" + dateDeValidite + ", cvc=" + cvc + ", toString()=" + super.toString() + "]";
	}
	
}
