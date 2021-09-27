package fr.pythie.webservice.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Cette classe spéciale définit quels packages et classes Spring doit prendre en compte.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@Configuration
@ComponentScan("fr.pythie.webservice.controller.pilotage, fr.pythie.webservice.controller.userinterface,"
		+ "fr.pythie.webservice.dao, fr.pythie.webservice.interfaces.service.pilotage,"
		+ "fr.pythie.webservice.interfaces.service.userinterface, fr.pythie.webservice.model,"
		+ "fr.pythie.webservice.service.financier, fr.pythie.webservice.service.pilotage,"
		+ "fr.pythie.webservice.service.userinterface")
public class ApplicationConfig {

}