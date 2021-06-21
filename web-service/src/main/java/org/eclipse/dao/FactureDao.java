package org.eclipse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.config.MyConnection;
import org.eclipse.model.Facture;

/**
 * Classe permet d'interagir avec les factures sauvegardées dans la base de données.
 * @author Gauthier NOURDIN
 */
public class FactureDao implements TemplateDao<Facture> {

	/**
	 * Fonction classique de sauvegarde de facture
	 */
	@Override
	public Facture save(Facture facture) {
		return null;
	}

	/** 
	 * Fonction classique pour supprimer une facture de la base de données
	 */
	@Override
	public void remove(Facture facture) {
		
	}

	/** 
	 * Fonction classique pour mettre à jour une facture dans la base de données
	 */
	@Override
	public Facture update(Facture facture) {
		return null;
	}

	/** 
	 * Fonction classique pour retrouver une facture à partir de son identifiant dans la base de données
	 */
	@Override
	public Facture findById(int id) {
		return null;
	}

	/** 
	 * Fonction classique pour retourner toutes les factures dans la base de données
	 */
	@Override
	public List<Facture> findAll() {
		return null;
	}

}
