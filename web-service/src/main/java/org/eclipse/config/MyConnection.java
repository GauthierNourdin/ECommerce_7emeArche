package org.eclipse.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe singleton pour paramétrer et lancer la connexion vers la base de
 * données
 * 
 * @author Illithian29
 */
public class MyConnection {
	private static String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=UTC";
	private static String utilisateur = "root";
	private static String motDePasse = "Mekkah!PCX637?Yggdrasil";
	private static Connection connexion = null;

	/**
	 * Constructeur pour créer une nouvelle connexion
	 */
	private MyConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fonction pour générer une nouvelle connexion ou pour récupérer celle en cours
	 * le cas échéant
	 */
	public static Connection getConnection() {
		if (connexion == null) {
			new MyConnection();
		}
		return connexion;
	}

	/**
	 * Fonction pour arrêter la connexion
	 */
	public static void stop() {
		if (connexion != null) {
			try {
				connexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}