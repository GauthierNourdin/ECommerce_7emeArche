package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Facture;

public interface FactureRepository extends JpaRepository<Facture, Long>{

}
