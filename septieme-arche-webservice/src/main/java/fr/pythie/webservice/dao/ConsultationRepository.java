package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Consultation;

/**
 * 
 * @author Gauthier Nourdin.
 *
 */
public interface ConsultationRepository extends JpaRepository<Consultation, Long>{

}
