package org.eclipse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.config.MyConnection;
import org.eclipse.model.Auteur;
import org.eclipse.model.Livre;
import org.eclipse.model.LivreImprime;
import org.eclipse.model.LivreNumerique;

/**
 * Classe permet d'interagir avec les auteurs sauvegardés dans la base de données.
 * @author Gauthier NOURDIN
 */
public class AuteurDao implements TemplateDao<Auteur> {

	/**
	 * Fonction classique de sauvegarde d'auteur
	 */
	@Override
	public Auteur save(Auteur auteur) {
		Connection c = MyConnection.getConnection();
		
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(
						"INSERT INTO Personne (nom,prenom) VALUES (?,?);",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, auteur.getNom());
				ps.setString(2, auteur.getPrenom());			
				ps.executeUpdate();
				
				ResultSet resultat = ps.getGeneratedKeys();
				if (resultat.next()) {
					int personneId = resultat.getInt(1);
					
					ps = c.prepareStatement(
							"INSERT INTO Auteur (personne_id) VALUES (?);",
							PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setInt(1, personneId);
					ps.executeUpdate();
					
					resultat = ps.getGeneratedKeys();
					if (resultat.next()) {
						auteur.setId(resultat.getInt(1));
						return auteur;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/** 
	 * Fonction classique pour supprimer un auteur de la base de données
	 */
	@Override
	public void remove(Auteur auteur) {
		Connection c = MyConnection.getConnection();
		
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT personne_id FROM Auteur WHERE id=?;");
				ps.setInt(1, auteur.getId());
				
				ResultSet resultat = ps.executeQuery();
				if (resultat.next()) {
					
					int personneId = resultat.getInt("personne_id");
					ps = c.prepareStatement("DELETE FROM Auteur WHERE id=?;");
					ps.setInt(1, auteur.getId());
					
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
	 * Fonction classique pour mettre à jour un auteur dans la base de données
	 */
	@Override
	public Auteur update(Auteur auteur) {
		Connection c = MyConnection.getConnection();
		
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT personne_id FROM Personne WHERE id=?;");
				ps.setInt(1, auteur.getId());
				
				ResultSet resultat = ps.executeQuery();
				if (resultat.next()) {
					int personneId = resultat.getInt("personne_id");
					
					ps = c.prepareStatement("UPDATE Personne SET nom=?, prenom=? WHERE id=?;");
					ps.setString(1, auteur.getNom());
					ps.setString(2, auteur.getPrenom());
					ps.setInt(3, personneId);
					
					int nbr = ps.executeUpdate();
					if (nbr == 1) {
						return auteur;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/** 
	 * Fonction classique pour retrouver un auteur à partir de son identifiant dans la base de données
	 */
	@Override
	public Auteur findById(int id) {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				ArrayList<Livre> livres = new ArrayList<Livre>();
				
				PreparedStatement ps = c.prepareStatement("SELECT livre_isbn13 FROM Auteur_livre WHERE auteur_id=?;");
				ps.setInt(1, id);
				
				ResultSet resultat = ps.executeQuery();
				while (resultat.next()) {
					String livre_isbn13 = resultat.getString("livre_isbn13");
					
					ps = c.prepareStatement("SELECT id FROM Livre_numerique WHERE livre_isbn13=?");
					ps.setString(1, livre_isbn13);
					
					ResultSet resultat2 = ps.executeQuery();
					if (resultat2.next()) {
						int livreNumeriqueId = resultat2.getInt("id");
						
						LivreNumerique livreNumerique = new LivreNumerique(livreNumeriqueId);
						livres.add(livreNumerique);
					}
					
					ps = c.prepareStatement("SELECT id FROM Livre_imprime WHERE livre_isbn13=?");
					ps.setString(1, livre_isbn13);
					
					resultat2 = ps.executeQuery();
					if (resultat2.next()) {
						int livreImprimeId = resultat2.getInt("id");
						
						LivreImprime livreImprime = new LivreImprime(livreImprimeId);
						livres.add(livreImprime);
					}
				}
				
				ps = c.prepareStatement("SELECT personne_id FROM Auteur WHERE id=?;");
				ps.setInt(1, id);
				
				resultat = ps.executeQuery();
				if (resultat.next()) {
					int personneId = resultat.getInt("personne_id");
					
					ps = c.prepareStatement("SELECT nom, prenom FROM Personne WHERE id=?;");
					ps.setInt(1, personneId);
					
					resultat = ps.executeQuery();
					if (resultat.next()) {
						String nom = resultat.getString("nom");
						String prenom = resultat.getString("prenom");
						
						Auteur auteur = new Auteur(nom, prenom, id, livres);
						return auteur;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/** 
	 * Fonction classique pour retourner tous les auteurs dans la base de données
	 */
	@Override
	public List<Auteur> findAll() {
		Connection c = MyConnection.getConnection();
		if (c != null) {
			try {
				ArrayList<Auteur> auteurs = new ArrayList<Auteur>();
				
				PreparedStatement ps = c.prepareStatement("SELECT id, personne_id FROM Auteur");
				
				ResultSet resultat = ps.executeQuery();
				while (resultat.next()) {
					ArrayList<Livre> livres = new ArrayList<Livre>();
					
					int id = resultat.getInt("id");
					int personneId = resultat.getInt("personne_id");
					
					ps = c.prepareStatement("SELECT livre_isbn13 FROM Auteur_livre WHERE auteur_id=?;");
					ps.setInt(1, id);
					
					ResultSet resultat2 = ps.executeQuery();
					while (resultat2.next()) {
						String livre_isbn13 = resultat.getString("livre_isbn13");
						
						ps = c.prepareStatement("SELECT id FROM Livre_numerique WHERE livre_isbn13=?");
						ps.setString(1, livre_isbn13);
						
						ResultSet resultat3 = ps.executeQuery();
						if (resultat3.next()) {
							int livreNumeriqueId = resultat3.getInt("id");
							
							LivreNumerique livreNumerique = new LivreNumerique(livreNumeriqueId);
							livres.add(livreNumerique);
						}
						
						ps = c.prepareStatement("SELECT id FROM Livre_imprime WHERE livre_isbn13=?");
						ps.setString(1, livre_isbn13);
						
						resultat3 = ps.executeQuery();
						if (resultat3.next()) {
							int livreImprimeId = resultat3.getInt("id");
							
							LivreImprime livreImprime = new LivreImprime(livreImprimeId);
							livres.add(livreImprime);
						}
					}
						
					ps = c.prepareStatement("SELECT nom, prenom FROM Personne WHERE id=?;");
					ps.setInt(1, personneId);
					
					resultat2 = ps.executeQuery();
					if (resultat2.next()) {
						String nom = resultat.getString("nom");
						String prenom = resultat.getString("prenom");
						
						Auteur auteur = new Auteur(nom, prenom, id, livres);
						auteurs.add(auteur);
					}
					
				}
				return auteurs;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}

}
