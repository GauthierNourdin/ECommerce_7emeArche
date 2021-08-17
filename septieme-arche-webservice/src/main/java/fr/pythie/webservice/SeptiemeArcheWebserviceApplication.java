package fr.pythie.webservice;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.pythie.webservice.configuration.DonneesInitiales;

@SpringBootApplication
public class SeptiemeArcheWebserviceApplication implements ApplicationRunner{

	public static void main(String[] args) {
		SpringApplication.run(SeptiemeArcheWebserviceApplication.class, args);
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		DonneesInitiales.InjectionDonneesInitiales();
	}

}
