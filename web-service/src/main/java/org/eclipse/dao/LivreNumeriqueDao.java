package org.eclipse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.config.MyConnection;
import org.eclipse.model.Auteur;
import org.eclipse.model.Genre;
import org.eclipse.model.LivreNumerique;

/**
 * Classe permet d'interagir avec les livres numériques sauvegardés dans la base de données.
 * @author Gauthier NOURDIN
 */
public class LivreNumeriqueDao implements TemplateLivreDao<LivreNumerique> {

	/**
	 * Fonction pour supprimer un livre imprimé de la base de données, la suppression dans les tables de jointure est incluse
	 */
	@Override
	public LivreNumerique save(LivreNumerique livreNumerique) {
		return null;
	}

	/** 
	 * Fonction  pour supprimer un livre numérique de la base de données, la suppression dans les tables de jointure est incluse
	 */
	@Override
	public void remove(LivreNumerique livreNumerique) {
		
	}

	/** 
	 * Fonction classique pour mettre à jour un livre numérique dans la base de données
	 */
	@Override
	public LivreNumerique update(LivreNumerique livreNumerique) {
		return null;
	}

	/** 
	 * Fonction pour retrouver un livre numérique à partir de son identifiant dans la base de données, on retourne dans le livre les identifiants des auteurs et des genres
	 */
	@Override
	public LivreNumerique findById(int id) {
		return null;
	}

	/** 
	 * Fonction pour retourner tous les livres numériques dans la base de données, on retourne dans les livres les identifiants des auteurs et des genres
	 */
	@Override
	public List<LivreNumerique> findAll() {
		return null;
	}
	
	/**
	 * Fonction pour ajouter un auteur existant à un livre numérique dans la base de données
	 */
	public LivreNumerique ajouterAuteur (LivreNumerique livreNumerique, Auteur auteur) {
		return null;
	}
	
	/**
	 * Fonction pour ajouter un genre existant à un livre numérique dans la base de données
	 */
	public LivreNumerique ajouterGenre (LivreNumerique livreNumerique, Genre genre) {
		return null;
	}
	
	/**
	 * Fonction pour retirer un auteur existant à un livre numérique dans la base de données
	 */
	public LivreNumerique retirerAuteur (LivreNumerique livreNumerique, Auteur auteur) {
		return null;
	}
	
	/**
	 * Fonction pour retirer un genre existant à un livre numérique dans la base de données
	 */
	public LivreNumerique retirerGenre (LivreNumerique livreNumerique, Genre genre) {
		return null;
	}
	
	/**
	 * Fonction pour trouver dans la base de données les livres numériques associés à un auteur
	 */
	public List<LivreNumerique> trouverParAuteur (LivreNumerique livreNumerique, Auteur auteur) {
		return null;
	}
	
	/**
	 * Fonction pour trouver dans la base de données les livres numériques associés à un genre
	 */
	public List<LivreNumerique> trouverParGenre (LivreNumerique livreNumerique, Genre genre) {
		return null;
	}

	
}