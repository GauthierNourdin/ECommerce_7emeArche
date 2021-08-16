package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long>{

}
