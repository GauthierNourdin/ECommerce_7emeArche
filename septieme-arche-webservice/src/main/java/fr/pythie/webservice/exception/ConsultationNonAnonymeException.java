package fr.pythie.webservice.exception;

/**
 * Classe d'exception indicant que la consultation référence un client alors que
 * ce ne devrait pas être le cas.
 */
public class ConsultationNonAnonymeException extends Exception {

	// On crée un numéro de série pour l'exception.
	private static final long serialVersionUID = -6823178212610383411L;

}
