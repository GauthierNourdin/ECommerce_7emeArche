package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Client;
import lombok.NonNull;

public interface ClientRepository extends JpaRepository<Client, Long>{

	Client findByEmailAndMotDePasse(@NonNull String email, @NonNull String motDePasse);

}
