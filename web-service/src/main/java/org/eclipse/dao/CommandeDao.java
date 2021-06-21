package org.eclipse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.config.MyConnection;
import org.eclipse.model.Commande;

/**
 * Classe permet d'interagir avec les commandes sauvegardées dans la base de données.
 * @author Gauthier NOURDIN
 */
public class CommandeDao implements TemplateDao<Commande> {

	/**
	 * Fonction classique de sauvegarde de commande
	 */
	@Override
	public Commande save(Commande commande) {
		return null;
	}

	/** 
	 * Fonction classique pour supprimer une commande de la base de données
	 */
	@Override
	public void remove(Commande commande) {
		
	}

	/** 
	 * Fonction classique pour mettre à jour une commande dans la base de données
	 */
	@Override
	public Commande update(Commande commande) {
		return null;
	}

	/** 
	 * Fonction classique pour retrouver une commande à partir de son identifiant dans la base de données
	 */
	@Override
	public Commande findById(int id) {
		return null;
	}

	/** 
	 * Fonction classique pour retourner toutes les commandes dans la base de données
	 */
	@Override
	public List<Commande> findAll() {
		return null;
	}

}
