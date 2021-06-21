package org.eclipse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.config.MyConnection;
import org.eclipse.model.Consultation;

/**
 * Classe permet d'interagir avec les consultations sauvegardées dans la base de données.
 * @author Gauthier NOURDIN
 */
public class ConsultationDao implements TemplateDao<Consultation> {

	/**
	 * Fonction classique de sauvegarde de consultation
	 */
	@Override
	public Consultation save(Consultation consultation) {
		return null;
	}

	/** 
	 * Fonction classique pour supprimer une consultation de la base de données
	 */
	@Override
	public void remove(Consultation consultation) {
		
	}

	/** 
	 * Fonction classique pour mettre à jour une consultation dans la base de données
	 */
	@Override
	public Consultation update(Consultation consultation) {
		return null;
	}

	/** 
	 * Fonction classique pour retrouver une consultation à partir de son identifiant dans la base de données
	 */
	@Override
	public Consultation findById(int id) {
		return null;
	}

	/** 
	 * Fonction classique pour retourner toutes les consultations dans la base de données
	 */
	@Override
	public List<Consultation> findAll() {
		return null;
	}

}
