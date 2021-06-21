package org.eclipse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.config.MyConnection;
import org.eclipse.model.LigneCommande;

/**
 * Classe permet d'interagir avec les lignes de commande sauvegardées dans la base de données.
 * @author Gauthier NOURDIN
 */
public class LigneCommandeDao implements TemplateDao<LigneCommande> {

	/**
	 * Fonction classique de sauvegarde de ligne de commande
	 */
	@Override
	public LigneCommande save(LigneCommande ligneCommande) {
		return null;
	}

	/** 
	 * Fonction classique pour supprimer une ligne de commande de la base de données
	 */
	@Override
	public void remove(LigneCommande ligneCommande) {
		
	}

	/** 
	 * Fonction classique pour mettre à jour une ligne de commande dans la base de données
	 */
	@Override
	public LigneCommande update(LigneCommande ligneCommande) {
		return null;
	}

	/** 
	 * Fonction classique pour retrouver une ligne de commande à partir de son identifiant dans la base de données
	 */
	@Override
	public LigneCommande findById(int id) {
		return null;
	}

	/** 
	 * Fonction classique pour retourner toutes les lignes de commande dans la base de données
	 */
	@Override
	public List<LigneCommande> findAll() {
		return null;
	}

}
