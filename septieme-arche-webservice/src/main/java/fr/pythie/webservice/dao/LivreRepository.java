package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Livre;

/**
 * Interface permettant de manipuler les livres en base de données.
 * Schéma utilisé par Spring Data JPA pour créer automatiquement les méthodes et requêtes nécessaires.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public interface LivreRepository extends JpaRepository<Livre, Long> {

}
