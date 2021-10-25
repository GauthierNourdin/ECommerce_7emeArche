package fr.pythie.webservice.tests.service.userinterface;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/*
 * Teste l'ensemble des services traîtant des commandes.
 */
@SpringBootTest
public class CommandeServiceImplTest {

	// Test de la méthode enregistrementCommande dans le cas standard.
	@Test 
	void testEnregistrementCommande() {
		
	}
	
	// Test de la méthode enregistrementCommande avec un stock insuffisant.
	@Test 
	void testEnregistrementCommandeExceptionStockInsuffisant() {
		
	}
	
	// Test de la méthode enregistrementCommande avec un client inconnu.
	@Test 
	void testEnregistrementCommandeExceptionClientInconnu() {
		
	}
	
}
