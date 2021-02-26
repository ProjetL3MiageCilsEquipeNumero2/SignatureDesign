package org.ProjetL3MiageCilsEquipeNumero2.SQLcommunication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLcomm {
	private static Connection connexion;

	/**
	 * initialise une connexion
	 * 
	 * @param nom  = le nom de l'user
	 * @param pass = le mot de passe de l'user
	 * @return true si connexion etablie, faux sinon
	 */
	public boolean init(String nom, String pass) {
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/signaturedesign", nom, pass);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @return true si connexion fermée avec succes
	 */
	public boolean close() {
		if(connexion == null) return false;
		try {
			connexion.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * retourne une table de la bdd
	 * 
	 * @param nomTable = nom de la table souhaitée
	 * @return reponse = la table sous la forme ResultSet, null si echec
	 */
	public static ResultSet table(String nomTable) {
		ResultSet reponse = null;
		try {
			Statement requete = connexion.createStatement();
			reponse = requete.executeQuery("SELECT * FROM " + nomTable + " ;");
			requete.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reponse;
	}

	/**
	 * ajoute une ligne à une table et retourne la cle autogenerée s'il y en a
	 * 
	 * @param nomTable = nom de la table souhaitée
	 * @param nomCol   = nom des colonnes (n1, n2, n3,...)
	 * @param Val      = Valeurs correspondant aux colonnes (v1, v2,...)
	 * @return cle = la cle s'il y en a, 0 sinon, -1 si echec
	 */
	public static int ajout(String nomTable, String nomCol, String val) {
		ResultSet reponse;
		int cle = 0;
		try {
			Statement requete = connexion.createStatement();
			requete.executeUpdate("INSERT INTO nomTable(" + nomCol + ") VALUES (" + val + ") ;",
					Statement.RETURN_GENERATED_KEYS);
			reponse = requete.getGeneratedKeys();
			if (reponse != null) {
				cle = reponse.getInt(1);
			}
			requete.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return cle;
	}

	/**
	 * supprime une ou plusieures lignes d'une table selon un condition
	 * 
	 * @param nomTable  = nom de la table souhaitée
	 * @param nomCol    = nom des colonnes (n1, n2, n3,...)
	 * @param condition = la condition de suppression
	 * @return reponse = le nombre de lignes supprimées, -1 si echec
	 */
	public static int delete(String nomTable, String condition) {
		int reponse = 0;
		try {
			Statement requete = connexion.createStatement();
			reponse = requete.executeUpdate("DELETE FROM " + nomTable + " WHERE " + condition + " ;");
			requete.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return reponse;
	}

}
