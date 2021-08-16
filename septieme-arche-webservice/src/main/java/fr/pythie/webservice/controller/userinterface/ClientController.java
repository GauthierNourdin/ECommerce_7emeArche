package fr.pythie.webservice.controller.userinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.pythie.webservice.interfaces.service.userinterface.ClientService;

@Component
@RestController
@ResponseBody
@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
@RequestMapping("/userinterface/client")
public class ClientController {
	/**
	 * Classe définissant toutes les requêtes possibles, ainsi que les réponses
	 * appropriées, concernant les clients, venant de l'interface utilisateur web.
	 * Distribue les requêtes aux classes services implémentant l'interface dédiée
	 * via Spring.
	 */
	
	@Autowired
	ClientService clientService;
	
	
	// Enregistre un nouveau compte client
	
	
	
	// Authentifie un client et retourne ses informations
	
	
	
	// Modifie les informations d'un compte client
	
}
