package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
