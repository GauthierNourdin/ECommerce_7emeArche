package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.LivreNumerique;

/**
 * 
 * @author Gauthier Nourdin.
 *
 */
public interface LivreNumeriqueRepository extends JpaRepository<LivreNumerique, Long>{

}
