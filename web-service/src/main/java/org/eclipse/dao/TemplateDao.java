package org.eclipse.dao;

import java.util.List;

/**
 * Interface servant de base à toutes les classes Dao.
 * @author Gauthier NOURDIN
 */
public interface TemplateDao<T> {
	
	/** 
	 * Fonction classique pour sauvegarder un objet
	 */
	T save(T t);

	/** 
	 * Fonction classique pour supprimer un objet
	 */
	void remove(T t);
	
	/** 
	 * Fonction classique pour mettre à jour un objet
	 */
	T update(T t);

	/** 
	 * Fonction classique pour retrouver un objet à partir de son identifiant
	 */
	T findById(int id);

	/** 
	 * Fonction classique pour retourner tous les objets
	 */
	List<T> findAll();
}
