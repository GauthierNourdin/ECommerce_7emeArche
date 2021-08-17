package fr.pythie.webservice.exception;

public class ConsultationNonAnonymeException extends Exception {
	/**
	 * Classe d'exception indicant que la consultation référence un client alors que
	 * ce ne devrait pas être le cas.
	 */

	// On crée un numéro de série pour l'exception.
	private static final long serialVersionUID = -6823178212610383411L;

}
