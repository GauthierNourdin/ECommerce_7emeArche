package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Adresse;

/**
 * Interface permettant de manipuler les adresses en base de données.
 * Schéma utilisé par Spring Data JPA pour créer automatiquement les méthodes et requêtes nécessaires.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public interface AdresseRepository extends JpaRepository<Adresse, Long>{

	/** 
	 * Fonction pour rechercher si une adresse exacte est déja présente.
	 * 
	 * @param numeroRue Le numéro de rue.
	 * @param nomRue Le nom de la rue.
	 * @param codePostal Le code postal.
	 * @param ville La ville.
	 * @param pays Le pays.
	 * @param complement Le complément d'adresse.
	 * 
	 * @return adresse Adresse correspondante le cas échéant, sinon rien.
	 * 
	 * @since 1.0
	 */
	Adresse findByNumeroRueAndNomRueAndCodePostalAndVilleAndPaysAndComplement(String numeroRue, String nomRue, String codePostal, String ville, String pays, String complement);
	
}
