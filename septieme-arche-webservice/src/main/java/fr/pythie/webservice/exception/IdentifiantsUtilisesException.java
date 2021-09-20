package fr.pythie.webservice.exception;

/**
 * Classe d'exception indicant que les identifiants (email + mot de passe) sont
 * déjà utilisés par un utilisateur enregistré
 */
public class IdentifiantsUtilisesException extends Exception {

	// On crée un numéro de série pour l'exception.
	private static final long serialVersionUID = 5860437133013076134L;

}
