package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Auteur;

/**
 * 
 * @author Gauthier Nourdin.
 *
 */
public interface AuteurRepository extends JpaRepository<Auteur, Long>{

}
