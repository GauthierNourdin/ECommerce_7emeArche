package org.eclipse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.config.MyConnection;
import org.eclipse.model.Adresse;
import org.eclipse.model.Client;
import org.eclipse.model.Commande;
import org.eclipse.model.Consultation;
import org.eclipse.model.Panier;

/**
 * Classe permet d'interagir avec les clients sauvegardés dans la base de données.
 * @author Gauthier NOURDIN
 */
public class ClientDao implements TemplateClientDao<Client> {

	/**
	 * Fonction classique de sauvegarde de client
	 */
	@Override
	public Client save(Client client) {
		Connection c = MyConnection.getConnection();
		
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(
						"INSERT INTO Personne (nom,prenom) VALUES (?,?);",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, client.getNom());
				ps.setString(2, client.getPrenom());			
				ps.executeUpdate();
				
				ResultSet resultat = ps.getGeneratedKeys();
				if (resultat.next()) {
					int personneId = resultat.getInt(1);
					
					ps = c.prepareStatement(
							"INSERT INTO Client (personne_id,email,mot_de_passe,numero_carte,date_validite,cvc,adresse_facturation_id,adresse_livraison_id) VALUES (?,?,?,?,?,?,?,?);",
							PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setInt(1, personneId);
					ps.setString(2, client.getEmail());
					ps.setString(3, client.getMotDePasse());
					ps.setString(4, client.getNumeroCarte());
					ps.setString(5, client.getDateDeValidite());
					ps.setString(6, client.getCvc());
					ps.setInt(7, client.getAdresseFacturation().getId());
					ps.setInt(8, client.getAdresseLivraison().getId());
					ps.executeUpdate();
					
					resultat = ps.getGeneratedKeys();
					if (resultat.next()) {
						client.setId(resultat.getInt(1));
						return client;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/** 
	 * Fonction classique pour supprimer un client de la base de données
	 */
	@Override
	public void remove(Client client) {
		Connection c = MyConnection.getConnection();
		
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT personne_id FROM Client WHERE id=?;");
				ps.setInt(1, client.getId());
				
				ResultSet resultat = ps.executeQuery();
				if (resultat.next()) {
					
					int personneId = resultat.getInt("personne_id");
					ps = c.prepareStatement("DELETE FROM Client WHERE id=?;");
					ps.setInt(1, client.getId());
					
					int nbr = ps.executeUpdate();
					if (nbr != 1) {
						System.err.println("Erreur : Absence de suppression");
					} else {
						ps = c.prepareStatement("DELETE FROM Personne WHERE id=?;");
						ps.setInt(1, personneId);
						
						nbr = ps.executeUpdate();
						if (nbr != 1) {
							System.err.println("Erreur : Absence de suppression");
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/** 
	 * Fonction classique pour mettre à jour un client dans la base de données
	 */
	@Override
	public Client update(Client client) {
		Connection c = MyConnection.getConnection();
		
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT personne_id FROM Client WHERE id=?;");
				ps.setInt(1, client.getId());
				
				ResultSet resultat = ps.executeQuery();
				if (resultat.next()) {
					int personneId = resultat.getInt("personne_id");
					
					ps = c.prepareStatement("UPDATE Personne SET nom=?, prenom=? WHERE id=?;");
					ps.setString(1, client.getNom());
					ps.setString(2, client.getPrenom());
					ps.setInt(3, personneId);
					
					int nbr = ps.executeUpdate();
					if (nbr == 1) {
						ps = c.prepareStatement("UPDATE Client SET email=?, mot_de_passe=?, numero_carte=?, date_validite=?, cvc=?;");
						ps.setString(1, client.getEmail());
						ps.setString(2, client.getMotDePasse());
						ps.setString(3, client.getNumeroCarte());
						ps.setString(4, client.getDateDeValidite());
						ps.setString(5, client.getCvc());
						
						nbr = ps.executeUpdate();
						if (nbr == 1) {
							return client;
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/** 
	 * Fonction classique pour retrouver un client à partir de son identifiant dans la base de données
	 */
	@Override
	public Client findById(int id) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				ArrayList<Consultation> consultations = new ArrayList<Consultation>();
				ArrayList<Commande> commandes = new ArrayList<Commande>();
							
				PreparedStatement ps = c.prepareStatement("SELECT id FROM Consultation WHERE client_id=?;");
				ps.setInt(1, id);
				
				ResultSet resultat = ps.executeQuery();
				while (resultat.next()) {
					int ConsultationId = resultat.getInt("id");
					
					Consultation consultation = new Consultation(ConsultationId);
					consultations.add(consultation);
				}
				
				ps = c.prepareStatement("SELECT numero FROM Commande WHERE client_id=?;");
				ps.setInt(1, id);
				
				resultat = ps.executeQuery();
				while (resultat.next()) {
					String commandeNumero = resultat.getString("numero");
					
					Commande commande = new Commande(commandeNumero);
					commandes.add(commande);
				}
				
				ps = c.prepareStatement("SELECT personne_id, email, mot_de_passe, numero_carte, date_validite, cvc, adresse_facturation_id, adresse_livraison_id FROM Client WHERE id=?;");
				ps.setInt(1, id);
				
				resultat = ps.executeQuery();
				if (resultat.next()) {
					int personneId = resultat.getInt("personne_id");
					String email = resultat.getString("email");
					String motDePasse = resultat.getString("mot_de_passe");
					String numeroCarte = resultat.getString("numero_carte");
					String dateDeValidite = resultat.getString("date_validite");
					String cvc = resultat.getString("cvc");
					int adresseFacturationId = resultat.getInt("adresse_facturation_id");
					int adresseLivraisonId = resultat.getInt("adresse_livraison_id");
					
					Adresse adresseFacturation = new Adresse(adresseFacturationId);
					Adresse adresseLivraison = new Adresse(adresseLivraisonId);
					
					Panier panier = new Panier();
					
					ps = c.prepareStatement("SELECT nom, prenom FROM Personne WHERE id=?;");
					ps.setInt(1, personneId);
					
					resultat = ps.executeQuery();
					if (resultat.next()) {
						String nom = resultat.getString("nom");
						String prenom = resultat.getString("prenom");
						
						Client client = new Client(nom, prenom, id, email, motDePasse, numeroCarte, dateDeValidite, cvc, adresseFacturation, adresseLivraison, panier, consultations, commandes);
						client.getPanier().setClient(client);
							
						return client;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/** 
	 * Fonction classique pour retourner tous les clients dans la base de données
	 */
	@Override
	public List<Client> findAll() {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				ArrayList<Client> clients = new ArrayList<Client>();
				
				PreparedStatement ps = c.prepareStatement("SELECT id, personne_id, email, mot_de_passe, numero_carte, date_validite, cvc, adresse_facturation_id, adresse_livraison_id FROM Client");
				
				ResultSet resultat = ps.executeQuery();
				while (resultat.next()) {
					ArrayList<Consultation> consultations = new ArrayList<Consultation>();
					ArrayList<Commande> commandes = new ArrayList<Commande>();
					
					int id = resultat.getInt("id");
					int personneId = resultat.getInt("personne_id");
					String email = resultat.getString("email");
					String motDePasse = resultat.getString("mot_de_passe");
					String numeroCarte = resultat.getString("numero_carte");
					String dateDeValidite = resultat.getString("date_validite");
					String cvc = resultat.getString("cvc");
					int adresseFacturationId = resultat.getInt("adresse_facturation_id");
					int adresseLivraisonId = resultat.getInt("adresse_livraison_id");
					
					Adresse adresseFacturation = new Adresse(adresseFacturationId);
					Adresse adresseLivraison = new Adresse(adresseLivraisonId);
					
					Panier panier = new Panier();
					
					ps = c.prepareStatement("SELECT id FROM Consultation WHERE client_id=?;");
					ps.setInt(1, id);
					
					ResultSet resultat2 = ps.executeQuery();
					while (resultat2.next()) {
						int ConsultationId = resultat2.getInt("id");
						
						Consultation consultation = new Consultation(ConsultationId);
						consultations.add(consultation);
					}
					
					ps = c.prepareStatement("SELECT numero FROM Commande WHERE client_id=?;");
					ps.setInt(1, id);
					
					resultat2 = ps.executeQuery();
					while (resultat2.next()) {
						String commandeNumero = resultat2.getString("numero");
						
						Commande commande = new Commande(commandeNumero);
						commandes.add(commande);
					}
					
					ps = c.prepareStatement("SELECT nom, prenom FROM Personne WHERE id=?;");
					ps.setInt(1, personneId);
					
					resultat2 = ps.executeQuery();
					if (resultat2.next()) {
						String nom = resultat2.getString("nom");
						String prenom = resultat2.getString("prenom");
						
						Client client = new Client(nom, prenom, id, email, motDePasse, numeroCarte, dateDeValidite, cvc, adresseFacturation, adresseLivraison, panier, consultations, commandes);
						client.getPanier().setClient(client);
							
						clients.add(client);
					}
					
				}
				return clients;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Fonction pour identifier et retourner un client enregistré dans la base de données (sans mot de passe, CVC et panier)
	 */
	public Client identifierClient(String email, String motDePasse) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT id, personne_id, numero_carte, date_validite adresse_facturation_id, adresse_livraison_id FROM Client WHERE email='?' AND mot_de_passe='?'");
				ps.setString(1, email);
				ps.setString(2, motDePasse);
				
				ResultSet resultat = ps.executeQuery();
				if (resultat.next()) {
					ArrayList<Consultation> consultations = new ArrayList<Consultation>();
					ArrayList<Commande> commandes = new ArrayList<Commande>();
					
					int id = resultat.getInt("id");
					int personneId = resultat.getInt("personne_id");
					String numeroCarte = resultat.getString("numero_carte");
					String dateDeValidite = resultat.getString("date_validite");
					int adresseFacturationId = resultat.getInt("adresse_facturation_id");
					int adresseLivraisonId = resultat.getInt("adresse_livraison_id");
					
					Adresse adresseFacturation = new Adresse(adresseFacturationId);
					Adresse adresseLivraison = new Adresse(adresseLivraisonId);
					
					Panier panier = new Panier();
					
					ps = c.prepareStatement("SELECT id FROM Consultation WHERE client_id=?;");
					ps.setInt(1, id);
					
					ResultSet resultat2 = ps.executeQuery();
					while (resultat2.next()) {
						int ConsultationId = resultat2.getInt("id");
						
						Consultation consultation = new Consultation(ConsultationId);
						consultations.add(consultation);
					}
					
					ps = c.prepareStatement("SELECT numero FROM Commande WHERE client_id=?;");
					ps.setInt(1, id);
					
					resultat2 = ps.executeQuery();
					while (resultat2.next()) {
						String commandeNumero = resultat2.getString("numero");
						
						Commande commande = new Commande(commandeNumero);
						commandes.add(commande);
					}
					
					ps = c.prepareStatement("SELECT nom, prenom FROM Personne WHERE id=?;");
					ps.setInt(1, personneId);
					
					resultat2 = ps.executeQuery();
					if (resultat2.next()) {
						String nom = resultat2.getString("nom");
						String prenom = resultat2.getString("prenom");
						
						Client client = new Client(nom, prenom, id, email, "", numeroCarte, dateDeValidite, "", adresseFacturation, adresseLivraison, panier, consultations, commandes);
						client.getPanier().setClient(client);
							
						return client;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Fonction pour modifier l'adresse de facturation d'un client dans la base de données
	 */
	public Client modifierAdresseFacturation(Adresse adresseFacturation, Client client) {
		Connection c = MyConnection.getConnection();
		
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("UPDATE Client SET adresse_facturation_id=? WHERE id=?;");
				ps.setInt(1, adresseFacturation.getId());
				ps.setInt(2, client.getId());
				
				int nbr = ps.executeUpdate();
				if (nbr == 1) {
					return client;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Fonction pour modifier l'adresse de livraison d'un client dans la base de données
	 */
	public Client modifierAdresseLivraison(Adresse adresseLivraison, Client client) {
		Connection c = MyConnection.getConnection();
		
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("UPDATE Client SET adresse_livraison_id=? WHERE id=?;");
				ps.setInt(1, adresseLivraison.getId());
				ps.setInt(2, client.getId());
				
				int nbr = ps.executeUpdate();
				if (nbr == 1) {
					return client;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
}
