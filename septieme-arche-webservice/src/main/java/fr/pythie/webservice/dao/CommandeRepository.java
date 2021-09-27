package fr.pythie.webservice.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Commande;

/**
 * Interface permettant de manipuler les commandes en base de données.
 * Schéma utilisé par Spring Data JPA pour créer automatiquement les méthodes et requêtes nécessaires.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public interface CommandeRepository extends JpaRepository<Commande, Long>{
	
	/**
	 * Fonction pour appeler toutes les commandes dont la date d'enregistrement est ultérieure à une date donnée.
	 * 
	 * @param date La date limite.
	 * 
	 * @return commandesPertinantes La liste des commandes enregistrées après la date limite.
	 * 
	 * @since 1.0
	 */
	List<Commande> findByDateAfter(LocalDateTime date);

}
