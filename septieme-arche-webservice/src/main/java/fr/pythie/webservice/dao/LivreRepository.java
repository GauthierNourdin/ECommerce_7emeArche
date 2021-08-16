package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Livre;

public interface LivreRepository extends JpaRepository<Livre, Long> {

}
