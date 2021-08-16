package fr.pythie.webservice.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long>{
	
	// Fonction pour appeler toutes les commandes dont la date de création est après une certaine date.
	List<Commande> findByDateAfter(LocalDateTime date);

}
