package fr.pythie.webservice.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Commande {
	/** 
	 * Classe pour les commandes.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String numero;
	@NonNull
	String status;
	LocalDateTime date = LocalDateTime.now();
	@JsonIgnoreProperties( "commande" )
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "commande")
	List<LigneCommande> lignesCommande;
	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Client client = new Client();
	@JsonIgnoreProperties( "commande" )
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "commande")
	List<Facture> factures;

	/**
	 * 
	 */
	public Commande() {
		super();
	}
	
	/**
	 * 
	 * @param status
	 */
	public Commande(@NonNull String status) {
		super();
		this.status = status;
	}
	
	/**
	 * Constructeur complet mis à part l'ID
	 * @param numero
	 * @param status
	 * @param date
	 * @param lignesCommande
	 * @param client
	 * @param factures
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
	 * 
	 * @param id
	 * @param numero
	 * @param status
	 * @param date
	 * @param lignesCommande
	 * @param client
	 * @param factures
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
	 * 
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * 
	 * @param numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * 
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	/**
	 * 
	 * @return
	 */
	public List<LigneCommande> getLignesCommande() {
		return lignesCommande;
	}

	/**
	 * 
	 * @param lignesCommande
	 */
	public void setLignesCommande(List<LigneCommande> lignesCommande) {
		this.lignesCommande = lignesCommande;
	}

	/**
	 * 
	 * @return
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * 
	 * @param client
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * 
	 * @return
	 */
	public List<Facture> getFactures() {
		return factures;
	}

	/**
	 * 
	 * @param factures
	 */
	public void setFactures(List<Facture> factures) {
		this.factures = factures;
	}

	/**
	 * Méthode pour construire le numéro de commande à partir des autres
	 * informations. On veut que le numéro contienne douze chiffres permettant de
	 * reconstruire la date et l'heure du passage ainsi que 8 chiffres pour l'id du
	 * client. Les deux groupes sont séparés par un tiret. S'établit avant la sauvegarde
	 * en base de données.
	 */
	public void definirNumero() {
		String stringNumero;
		DateTimeFormatter formatNumero = DateTimeFormatter.ofPattern("yyMMddHHmmss");
		stringNumero = date.format(formatNumero) + "-"
				+ String.format("%8s", String.valueOf(client.getId())).replace(' ', '0');
		this.setNumero(stringNumero);
	}
	
	/**
	 * 
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
	 * 
	 */
	@Override
	public String toString() {
		return "Commande [id=" + id + ", numero=" + numero + ", status=" + status + ", date=" + date + "]";
	}
	
}
