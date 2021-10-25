package fr.pythie.webservice.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/** 
 * Classe modèle pour les commandes.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String numero;
	@NonNull
	String status;
	LocalDateTime date = LocalDateTime.now();
	@JsonIgnoreProperties( "commande" )
	@Fetch(value = FetchMode.SUBSELECT)
	//@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "commande")
	List<LigneCommande> lignesCommande;
	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Client client = new Client();
	@JsonIgnoreProperties( "commande" )
	@Fetch(value = FetchMode.SUBSELECT)
	//@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "commande")
	List<Facture> factures;

	/**
	 * Constructeur permettant de construire une commande vide.
	 * 
	 * @since 1.0
	 */
	public Commande() {
		super();
	}
	
	/**
	 * Constructeur permettant de construire une commande avec les informations obligatoires.
	 * 
	 * @param status Le status actuel.
	 * 
	 * @since 1.0
	 */
	public Commande(@NonNull String status) {
		super();
		this.status = status;
	}
	
	/**
	 * Constructeur permettant de construire une commande complète mais sans identifiant.
	 * 
	 * @param numero Le numéro commercial.
	 * @param status Le status actuel.
	 * @param date La date de passage de commande.
	 * @param lignesCommande La liste des lignes de commande.
	 * @param client Le client ayant passé cette commande.
	 * @param factures La liste des factures associées à cette commande.
	 * 
	 * @since 1.0
	 */
	public Commande(String numero, @NonNull String status, LocalDateTime date,
			List<LigneCommande> lignesCommande, Client client, List<Facture> factures) {
		super();
		this.numero = numero;
		this.status = status;
		this.date = date;
		this.lignesCommande = lignesCommande;
		this.client = client;
		this.factures = factures;
	}

	/**
	 * Constructeur permettant de construire une commande complète.
	 * 
	 * @param id L'identifiant.
	 * @param numero Le numéro commercial.
	 * @param status Le status actuel.
	 * @param date La date de passage de commande.
	 * @param lignesCommande La liste des lignes de commande.
	 * @param client Le client ayant passé cette commande.
	 * @param factures La liste des factures associées à cette commande.
	 * 
	 * @since 1.0
	 */
	public Commande(long id, String numero, @NonNull String status, LocalDateTime date,
			List<LigneCommande> lignesCommande, Client client, List<Facture> factures) {
		super();
		this.id = id;
		this.numero = numero;
		this.status = status;
		this.date = date;
		this.lignesCommande = lignesCommande;
		this.client = client;
		this.factures = factures;
	}

	/**
	 * Retourne l'identifiant.
	 * 
	 * @return id L'identifiant.
	 * 
	 * @see Commande#setId(long)
	 * 
	 * @since 1.0
	 */
	public long getId() {
		return id;
	}

	/**
	 * Modifie l'identifiant.
	 * 
	 * @param id Le nouvel identifiant.
	 * 
	 * @see Commande#getId()
	 * 
	 * @since 1.0
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Retourne le numéro commercial.
	 * 
	 * @return numero Le numéro commercial.
	 * 
	 * @see Commande#setNumero(String)
	 * 
	 * @since 1.0
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Modifie le numéro commercial.
	 * 
	 * @param numero Le nouveau numéro commercial.
	 * 
	 * @see Commande#getNumero()
	 * 
	 * @since 1.0
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Retourne le status actuel.
	 * 
	 * @return status Le status actuel.
	 * 
	 * @see Commande#setStatus(String)
	 * 
	 * @since 1.0
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Modifie le status actuel.
	 * 
	 * @param status Le nouveau status actuel.
	 * 
	 * @see Commande#getStatus()
	 * 
	 * @since 1.0
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Retourne la date de passage de commande.
	 * 
	 * @return date La date de passage de commande.
	 * 
	 * @see Commande#setDate(LocalDateTime)
	 * 
	 * @since 1.0
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * Modifie la date de passage de commande.
	 * 
	 * @param date La nouvelle date de passage de commande.
	 * 
	 * @see Commande#getDate()
	 * 
	 * @since 1.0
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	/**
	 * Retourne la liste des lignes de commande.
	 *
	 * @return lignesCommande La liste des lignes de commande.
	 * 
	 * @see Commande#setLignesCommande(List)
	 * 
	 * @since 1.0
	 */
	public List<LigneCommande> getLignesCommande() {
		return lignesCommande;
	}

	/**
	 * Modifie la liste des lignes de commande.
	 * 
	 * @param lignesCommande La nouvelle liste des lignes de commande.
	 * 
	 * @see Commande#getLignesCommande()
	 * 
	 * @since 1.0
	 */
	public void setLignesCommande(List<LigneCommande> lignesCommande) {
		this.lignesCommande = lignesCommande;
	}

	/**
	 * Retourne le client ayant passé cette commande.
	 * 
	 * @return client Le client ayant passé cette commande.
	 * 
	 * @see Commande#setClient(Client)
	 * 
	 * @since 1.0
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Modifie le client ayant passé cette commande.
	 * 
	 * @param client Le nouveau client ayant passé cette commande.
	 * 
	 * @see Commande#getClient()
	 * 
	 * @since 1.0
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Retourne la liste des factures associées à cette commande.
	 * 
	 * @return factures La liste des factures associées à cette commande.
	 * 
	 * @see Commande#setFactures(List)
	 * 
	 * @since 1.0
	 */
	public List<Facture> getFactures() {
		return factures;
	}

	/**
	 * Modifie la liste des factures associées à cette commande.
	 * 
	 * @param factures La nouvelle liste des factures associées à cette commande.
	 * 
	 * @see Commande#getFactures()
	 * 
	 * @since 1.0
	 */
	public void setFactures(List<Facture> factures) {
		this.factures = factures;
	}

	/**
	 * Méthode pour construire le numéro de commande. On veut que le numéro contienne douze chiffres permettant de
	 * reconstruire la date et l'heure du passage ainsi que 8 chiffres pour l'id du
	 * client. Les deux groupes sont séparés par un tiret. S'établit avant la sauvegarde
	 * en base de données.
	 * 
	 * @since 1.0
	 */
	public void definirNumero() {
		String stringNumero;
		DateTimeFormatter formatNumero = DateTimeFormatter.ofPattern("yyMMddHHmmss");
		stringNumero = date.format(formatNumero) + "-"
				+ String.format("%8s", String.valueOf(client.getId())).replace(' ', '0');
		this.setNumero(stringNumero);
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
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((factures == null) ? 0 : factures.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lignesCommande == null) ? 0 : lignesCommande.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	/**
	 * Définit les conditions dans lesquelles un autre objet est égal à cette commande.
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
		Commande other = (Commande) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (factures == null) {
			if (other.factures != null)
				return false;
		} else if (!factures.equals(other.factures))
			return false;
		if (id != other.id)
			return false;
		if (lignesCommande == null) {
			if (other.lignesCommande != null)
				return false;
		} else if (!lignesCommande.equals(other.lignesCommande))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	/**
	 * Produit la chaîne de caractères réprésentant la commande.
	 * On n'y intègre pas le client, les factures et les lignes de commandes associés.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "Commande [id=" + id + ", numero=" + numero + ", status=" + status + ", date=" + date + "]";
	}
	
}
