package org.eclipse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.config.MyConnection;
import org.eclipse.model.Genre;

/**
 * Classe permet d'interagir avec les genres sauvegardés dans la base de données.
 * @author Gauthier NOURDIN
 */
public class GenreDao implements TemplateDao<Genre> {

	/**
	 * Fonction classique de sauvegarde de genre
	 */
	@Override
	public Genre save(Genre genre) {
		return null;
	}

	/** 
	 * Fonction classique pour supprimer un genre de la base de données
	 */
	@Override
	public void remove(Genre genre) {
		
	}

	/** 
	 * Fonction classique pour mettre à jour un genre dans la base de données
	 */
	@Override
	public Genre update(Genre genre) {
		return null;
	}

	/** 
	 * Fonction classique pour retrouver un genre à partir de son identifiant dans la base de données
	 */
	@Override
	public Genre findById(int id) {
		return null;
	}

	/** 
	 * Fonction classique pour retourner tous les genres dans la base de données
	 */
	@Override
	public List<Genre> findAll() {
		return null;
	}

}
