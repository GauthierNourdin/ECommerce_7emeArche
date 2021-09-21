package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Personne;

/**
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public interface PersonneRepository extends JpaRepository<Personne, Long>{

}
