package org.eclipse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.config.MyConnection;
import org.eclipse.model.Adresse;

/**
 * Classe permet d'interagir avec les adresses sauvegardées dans la base de
 * données.
 * 
 * @author Gauthier NOURDIN
 */
public class AdresseDao implements TemplateAdresseDao<Adresse> {

	/**
	 * Fonction classique de sauvegarde d'adresse
	 */
	@Override
	public Adresse save(Adresse adresse) {
		Connection c = MyConnection.getConnection();
		
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(
						"INSERT INTO Adresse (numero_rue,nom_rue,ville,code_postal,pays,complement) VALUES (?,?,?,?,?,?);",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, adresse.getNumeroRue());
				ps.setString(2, adresse.getNomRue());
				ps.setString(3, adresse.getVille());
				ps.setString(4, adresse.getCodePostal());
				ps.setString(5, adresse.getPays());
				ps.setString(6, adresse.getComplement());
				ps.executeUpdate();
				
				ResultSet resultat = ps.getGeneratedKeys();
				if (resultat.next()) {
					adresse.setId(resultat.getInt(1));
					return adresse;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Fonction classique pour supprimer une adresse de la base de données
	 */
	@Override
	public void remove(Adresse adresse) {
		Connection c = MyConnection.getConnection();
		
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement("DELETE FROM Adresse WHERE id=?;");
				ps.setInt(1, adresse.getId());
				int nbr = ps.executeUpdate();
				if (nbr != 1) {
					System.err.println("Erreur : Absence de suppression");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Fonction classique pour mettre à jour une adresse dans la base de données
	 */
	@Override
	public Adresse update(Adresse adresse) {
		Connection c = MyConnection.getConnection();
		
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(
						"UPDATE Adresse SET numero_rue=?, nom_rue=?, ville=?, code_postal=?, pays=?, complement=? WHERE id=?;");
				ps.setString(1, adresse.getNumeroRue());
				ps.setString(2, adresse.getNomRue());
				ps.setString(3, adresse.getVille());
				ps.setString(4, adresse.getCodePostal());
				ps.setString(5, adresse.getPays());
				ps.setString(6, adresse.getComplement());
				ps.setInt(7, adresse.getId());
				
				int nbr = ps.executeUpdate();
				if (nbr == 1) {
					return adresse;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Fonction classique pour retrouver une adresse à partir de son identifiant
	 * dans la base de données
	 */
	@Override
	public Adresse findById(int id) {
		Connection c = MyConnection.getConnection();
		
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(
						"SELECT numero_rue, nom_rue, ville, code_postal, pays, complement FROM Adresse WHERE id=?;");
				ps.setInt(1, id);
				
				ResultSet resultat = ps.executeQuery();
				if (resultat.next()) {
					String numeroRue = resultat.getString("numero_rue");
					String nomRue = resultat.getString("nom_rue");
					String ville = resultat.getString("ville");
					String codePostal = resultat.getString("code_postal");
					String pays = resultat.getString("pays");
					String complement = resultat.getString("complement");
					
					Adresse adresse = new Adresse(id, numeroRue, nomRue, ville, codePostal, pays, complement);
					return adresse;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Fonction classique pour retourner toutes les adresses dans la base de données
	 */
	@Override
	public List<Adresse> findAll() {
		Connection c = MyConnection.getConnection();
		
		if (c != null) {
			try {
				ArrayList<Adresse> adresses = new ArrayList<Adresse>();
				Statement statement = c.createStatement();
				String request = "SELECT id, numero_rue, nom_rue, ville, code_postal, pays, complement FROM Adresse;";
				
				ResultSet resultat = statement.executeQuery(request);
				while (resultat.next()) {
					int id = resultat.getInt("id");
					String numeroRue = resultat.getString("numero_rue");
					String nomRue = resultat.getString("nom_rue");
					String ville = resultat.getString("ville");
					String codePostal = resultat.getString("code_postal");
					String pays = resultat.getString("pays");
					String complement = resultat.getString("complement");
					Adresse adresse = new Adresse(id, numeroRue, nomRue, ville, codePostal, pays, complement);
					adresses.add(adresse);
				}
				return adresses;

			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			
		}
		return null;

	}
	
	/**
	 * Fonction pour vérifier qu'une adresse que l'on souhaite enregistrer ne soit pas déjà présente dans la base de données. Retourne un identifiant si un doublon est trouvé.
	 */
	public int verifierDoublon(Adresse adresse) {
		Connection c = MyConnection.getConnection();
		
		if (c != null) {
			try {
				PreparedStatement ps = c.prepareStatement(
						"SELECT id FROM Adresse WHERE numero_rue=? AND nom_rue=? AND ville=? AND code_postal=? AND pays=? AND complement=?;");
				ps.setString(1, adresse.getNumeroRue());
				ps.setString(2, adresse.getNomRue());
				ps.setString(3, adresse.getVille());
				ps.setString(4, adresse.getCodePostal());
				ps.setString(5, adresse.getPays());
				ps.setString(6, adresse.getComplement());
				
				ResultSet resultat = ps.executeQuery();
				if (resultat.next()) {
					return resultat.getInt("id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
		
	}

}
