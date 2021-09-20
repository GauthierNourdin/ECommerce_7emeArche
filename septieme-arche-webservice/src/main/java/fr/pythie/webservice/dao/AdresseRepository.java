package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Adresse;

/**
 * 
 * @author Gauthier Nourdin.
 *
 */
public interface AdresseRepository extends JpaRepository<Adresse, Long>{

	Adresse findByNumeroRueAndNomRueAndCodePostalAndVilleAndPaysAndComplement(String numeroRue, String nomRue, String codePostal, String ville, String pays, String complement);
	
}
