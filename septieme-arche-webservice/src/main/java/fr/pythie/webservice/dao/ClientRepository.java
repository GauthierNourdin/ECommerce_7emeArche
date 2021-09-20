package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Client;
import lombok.NonNull;

/**
 * 
 * @author Gauthier Nourdin.
 *
 */
public interface ClientRepository extends JpaRepository<Client, Long>{

	/**
	 * 
	 * @param email
	 * @param motDePasse
	 * @return
	 */
	Client findByEmailAndMotDePasse(@NonNull String email, @NonNull String motDePasse);

}
