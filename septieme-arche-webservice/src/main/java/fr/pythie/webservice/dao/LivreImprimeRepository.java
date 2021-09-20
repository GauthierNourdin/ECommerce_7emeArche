package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.LivreImprime;

/**
 * 
 * @author Gauthier Nourdin.
 *
 */
public interface LivreImprimeRepository extends JpaRepository<LivreImprime, Long>{
	
}
