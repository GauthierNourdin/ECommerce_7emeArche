package fr.pythie.webservice.tests.service.userinterface;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/*
 * Teste l'ensemble des services traîtant des clients.
 */
@SpringBootTest
public class TestClientServiceImpl {

	// Test de la méthode creationCompteClient dans le cas standard.
	@Test
	void testCreationCompteClient() {
			
	}
	
	// Test de la méthode creationCompteClient avec des identifiants déjà utilisés.
	@Test
	void testCreationCompteClientExceptionIdentifiantsUtilises() {
			
	}
	
	// Test de la méthode authentificationClient dans le cas standard.
	@Test
	void testAuthentificationClient() {
			
	}
	
	// Test de la méthode authentificationClient avec des identifiants inconnus.
	@Test
	void testAuthentificationClientExceptionClientInconnu() {
			
	}
	
	// Test de la méthode modificationCompteClient dans le cas standard.
	@Test
	void testModificationCompteClient() {
			
	}
	
	// Test de la méthode modificationCompteClient avec un client inconnu.
	@Test
	void testModificationCompteClientExceptionClientInconnu() {
			
	}
	
	// Test de la méthode modificationCompteClient avec des identifiants déjà utilisés.
	@Test
	void testModificationCompteClientExceptionIdentifiantsUtilises() {
			
	}
	
}
