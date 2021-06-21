package org.eclipse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.config.MyConnection;
import org.eclipse.model.Editeur;

/**
 * Classe permet d'interagir avec les éditeurs sauvegardés dans la base de données.
 * @author Gauthier NOURDIN
 */
public class EditeurDao implements TemplateDao<Editeur> {

	/**
	 * Fonction classique de sauvegarde d'éditeur
	 */
	@Override
	public Editeur save(Editeur editeur) {
		return null;
	}

	/** 
	 * Fonction classique pour supprimer un éditeur de la base de données
	 */
	@Override
	public void remove(Editeur editeur) {
		
	}

	/** 
	 * Fonction classique pour mettre à jour un éditeur dans la base de données
	 */
	@Override
	public Editeur update(Editeur editeur) {
		return null;
	}

	/** 
	 * Fonction classique pour retrouver un éditeur à partir de son identifiant dans la base de données
	 */
	@Override
	public Editeur findById(int id) {
		return null;
	}

	/** 
	 * Fonction classique pour retourner tous les éditeurs dans la base de données
	 */
	@Override
	public List<Editeur> findAll() {
		return null;
	}

}
