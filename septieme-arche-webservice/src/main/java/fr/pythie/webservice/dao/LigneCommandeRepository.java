package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.LigneCommande;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long>{

}
