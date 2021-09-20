package fr.pythie.webservice.service.userinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pythie.webservice.communication.DemandeAuthentification;
import fr.pythie.webservice.dao.AdresseRepository;
import fr.pythie.webservice.dao.ClientRepository;
import fr.pythie.webservice.exception.ClientInconnuException;
import fr.pythie.webservice.exception.EcritureBaseDonneesException;
import fr.pythie.webservice.exception.IdentifiantsUtilisesException;
import fr.pythie.webservice.exception.LectureBaseDonneesException;
import fr.pythie.webservice.interfaces.service.userinterface.ClientService;
import fr.pythie.webservice.model.Adresse;
import fr.pythie.webservice.model.Client;

/**
 * Classe service implémentant les méthodes demandées par ClientService afin de
 * traîter les demandes concernant les clients faites par l'interface
 * utilisateur
 */
@Component
public class ClientServiceImpl implements ClientService {

	@Autowired
	AdresseRepository adresseRepository;
	
	@Autowired
	ClientRepository clientRepository;

	/**
	 * Permet d'enregistrer un nouveau client
	 */
	public Client creationCompteClient(Client nouveauClient)
			throws LectureBaseDonneesException, EcritureBaseDonneesException, IdentifiantsUtilisesException {

		// Tout d'abord il faut vérifier qu'un autre client n'utilise pas les mêmes
		// identifiants.

		Client clientAvecLesMemesIdentifiants;

		try {
			clientAvecLesMemesIdentifiants = clientRepository.findByEmailAndMotDePasse(nouveauClient.getEmail(),
					nouveauClient.getMotDePasse());
		} catch (Exception exception) {
			// En cas d'erreur à la lecture on lève une exception.
			throw new LectureBaseDonneesException();
		}

		// Si un client a été retourné on lève une exception.
		if (clientAvecLesMemesIdentifiants != null) {
			throw new IdentifiantsUtilisesException();
		}

		// On commence par enregistrer les adresses du client
		Adresse adresseFacturation = nouveauClient.getAdresseFacturation();
		
		// On vérifie que l'adresse de facturation reçue n'est pas identique à une adresse déjà enregistrée.
		Adresse adresseDejaEnregistree = adresseRepository.findByNumeroRueAndNomRueAndCodePostalAndVilleAndPaysAndComplement(adresseFacturation.getNumeroRue(), adresseFacturation.getNomRue(), adresseFacturation.getCodePostal(), adresseFacturation.getVille(), adresseFacturation.getPays(), adresseFacturation.getComplement());
		
		// Si c'est le cas on utilise celle déjà enregistrée.
		if (adresseDejaEnregistree != null) {
			nouveauClient.setAdresseFacturation(adresseDejaEnregistree);
		}
		
		// Sinon on enregistre l'adresse
		if (adresseDejaEnregistree == null) {
			adresseFacturation = adresseRepository.save(adresseFacturation);
			nouveauClient.setAdresseFacturation(adresseFacturation);
		}
		
		Adresse adresseLivraison = nouveauClient.getAdresseLivraison();
		
		// On vérifie que l'adresse de livraison reçue n'est pas identique à une adresse déjà enregistrée.
		adresseDejaEnregistree = adresseRepository.findByNumeroRueAndNomRueAndCodePostalAndVilleAndPaysAndComplement(adresseFacturation.getNumeroRue(), adresseFacturation.getNomRue(), adresseFacturation.getCodePostal(), adresseFacturation.getVille(), adresseFacturation.getPays(), adresseFacturation.getComplement());
		
		// Si c'est le cas on utilise celle déjà enregistrée.
		if (adresseDejaEnregistree != null) {
			nouveauClient.setAdresseLivraison(adresseDejaEnregistree);
		}
		
		// Sinon on enregistre l'adresse
		if (adresseDejaEnregistree == null) {
			adresseLivraison = adresseRepository.save(adresseLivraison);
			nouveauClient.setAdresseLivraison(adresseLivraison);
		}
		
		// On peut alors enregistrer le nouveau client dans la base de données.
		Client clientEnregistre;

		try {
			clientEnregistre = clientRepository.save(nouveauClient);
		} catch (Exception exception) {
			// En cas d'erreur à l'écriture on lève une exception.
			throw new EcritureBaseDonneesException();
		}

		return clientEnregistre;
	}

	/** 
	 * Permet d'authentifier un client à partir de son email et de son mot de passe
	 */
	public Client authentificationClient(DemandeAuthentification demandeAuthentification)
			throws LectureBaseDonneesException, ClientInconnuException {

		// On essaie d'obtenir les informations d'un client disposant de l'email et du
		// mot de passe fourni.
		Client clientIdentifie;

		try {
			clientIdentifie = clientRepository.findByEmailAndMotDePasse(demandeAuthentification.getEmail(),
					demandeAuthentification.getMotDePasse());
		} catch (Exception exception) {
			// En cas d'erreur à la lecture on lève une exception.
			throw new LectureBaseDonneesException();
		}

		// On vérifie qu'on a bien obtenu un client sinon on lève une exception.
		if (clientIdentifie == null) {
			throw new ClientInconnuException();
		}

		return clientIdentifie;

	}

	/** 
	 * Modifie les informations personnelles d'un client.
	 */
	public Client modificationCompteClient(Client clientAModifier) throws LectureBaseDonneesException,
			EcritureBaseDonneesException, ClientInconnuException, IdentifiantsUtilisesException {

		// Tout d'abord on vérifie que le client est connu dans la base de données avec
		// son identifiant.
		Client clientIdentifie;

		try {
			clientIdentifie = clientRepository.findById(clientAModifier.getId()).orElse(null);
		} catch (Exception exception) {
			// En cas d'erreur à la lecture on lève une exception.
			throw new LectureBaseDonneesException();
		}

		// Si le client n'est identifié on lève une exception.
		if (clientIdentifie == null) {
			throw new ClientInconnuException();
		}

		// Ensuite en cas de modification des identifiants on doit vérifier qu'ils ne
		// correspondent pas à un autre client

		Client clientAvecLesMemesIdentifiants;

		try {
			clientAvecLesMemesIdentifiants = clientRepository.findByEmailAndMotDePasse(clientAModifier.getEmail(),
					clientAModifier.getMotDePasse());
		} catch (Exception exception) {
			// En cas d'erreur à la lecture on lève une exception.
			throw new LectureBaseDonneesException();
		}

		// On doit lever une exception si on a retourné un client et que son identifiant est différent
		// de celui qu'on souhaite modifier.
		if (!(clientAvecLesMemesIdentifiants == null || clientAvecLesMemesIdentifiants.getId() == clientAModifier.getId())) {
			throw new IdentifiantsUtilisesException();
		}
		
		// On commence par enregistrer les adresses du client
		Adresse adresseFacturation = clientAModifier.getAdresseFacturation();
		
		// On vérifie que l'adresse de facturation reçue n'est pas identique à une adresse déjà enregistrée.
		Adresse adresseDejaEnregistree = adresseRepository.findByNumeroRueAndNomRueAndCodePostalAndVilleAndPaysAndComplement(adresseFacturation.getNumeroRue(), adresseFacturation.getNomRue(), adresseFacturation.getCodePostal(), adresseFacturation.getVille(), adresseFacturation.getPays(), adresseFacturation.getComplement());
		
		// Si c'est le cas on utilise celle déjà enregistrée.
		if (adresseDejaEnregistree != null) {
			clientAModifier.setAdresseFacturation(adresseDejaEnregistree);
		}
		
		// Sinon on enregistre l'adresse
		if (adresseDejaEnregistree == null) {
			adresseFacturation = adresseRepository.save(adresseFacturation);
			clientAModifier.setAdresseFacturation(adresseFacturation);
		}
		
		Adresse adresseLivraison = clientAModifier.getAdresseLivraison();
		
		// On vérifie que l'adresse de livraison reçue n'est pas identique à une adresse déjà enregistrée.
		adresseDejaEnregistree = adresseRepository.findByNumeroRueAndNomRueAndCodePostalAndVilleAndPaysAndComplement(adresseFacturation.getNumeroRue(), adresseFacturation.getNomRue(), adresseFacturation.getCodePostal(), adresseFacturation.getVille(), adresseFacturation.getPays(), adresseFacturation.getComplement());
		
		// Si c'est le cas on utilise celle déjà enregistrée.
		if (adresseDejaEnregistree != null) {
			clientAModifier.setAdresseLivraison(adresseDejaEnregistree);
		}
		
		// Sinon on enregistre l'adresse
		if (adresseDejaEnregistree == null) {
			adresseLivraison = adresseRepository.save(adresseLivraison);
			clientAModifier.setAdresseLivraison(adresseLivraison);
		}
		
		// On peut alors enregistrer les modifications en base de données
		Client clientModifie;

		try { 
			clientModifie = clientRepository.save(clientAModifier);
		} catch (Exception exception) { 
			// En cas d'erreur à l'écriture on lève une exception.
			throw new EcritureBaseDonneesException();
		}
		
		return clientModifie;
	}

}
