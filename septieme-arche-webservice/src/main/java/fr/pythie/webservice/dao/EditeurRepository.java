package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Editeur;

public interface EditeurRepository extends JpaRepository<Editeur, Long>{

}
