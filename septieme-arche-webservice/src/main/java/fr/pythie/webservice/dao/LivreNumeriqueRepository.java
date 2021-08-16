package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.LivreNumerique;

public interface LivreNumeriqueRepository extends JpaRepository<LivreNumerique, Long>{

}
