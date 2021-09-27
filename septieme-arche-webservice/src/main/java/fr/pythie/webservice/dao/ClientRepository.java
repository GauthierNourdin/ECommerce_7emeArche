package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Client;
import lombok.NonNull;

/**
 * Interface permettant de manipuler les clients en base de données.
 * Schéma utilisé par Spring Data JPA pour créer automatiquement les méthodes et requêtes nécessaires.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public interface ClientRepository extends JpaRepository<Client, Long>{

	/**
	 * Fonction pour rechercher si un client dispose d'une combinaison email et mot de passe donnée.
	 * 
	 * @param email L'adresse email.
	 * @param motDePasse Le mot de passe.
	 * 
	 * @return clientIdentifie Le client ayant la bonne combinaison email et mot de passe, sinon rien.
	 * 
	 * @since 1.0
	 */
	Client findByEmailAndMotDePasse(@NonNull String email, @NonNull String motDePasse);

}
