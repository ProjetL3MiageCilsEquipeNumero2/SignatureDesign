package org.ProjetL3MiageCilsEquipeNumero2.Magasin;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.ProjetL3MiageCilsEquipeNumero2.SQLcommunication.DataBase;
import org.ProjetL3MiageCilsEquipeNumero2.SignatureDesign.App;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Vendeur {

	private static ObservableList<Vendeur> vendeurs = FXCollections.observableArrayList();

	private SimpleIntegerProperty id;
	private SimpleStringProperty nom;
	private SimpleStringProperty prenom;
	private SimpleDoubleProperty salaire;

	public static ObservableList<Vendeur> getVendeurs() {
		return vendeurs;
	}

	public Vendeur(int id, String prenom, String nom, double salaire) {
		this.id = new SimpleIntegerProperty(id);
		this.prenom = new SimpleStringProperty(prenom);
		this.nom = new SimpleStringProperty(nom);
		this.salaire = new SimpleDoubleProperty(salaire);
	}

	/*
	 * met à jour les vendeurs
	 */
	public static void vendeursUpdate() {
		vendeurs.clear();
		ResultSet tableVendeur = Vendeur.getTableVendeur();
		try {
			while (tableVendeur.next()) {
				vendeurs.add(new Vendeur(tableVendeur.getInt("Id_Vendeur"), tableVendeur.getString("Prenom_Vendeur"),
						tableVendeur.getString("Nom_Vendeur"), tableVendeur.getDouble("salaire_vendeur")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * retourne la table Vendeur
	 */
	public static ResultSet getTableVendeur() {
		ResultSet rs = null;
		try {
			CallableStatement cs = App.db.getConnection().prepareCall("{call GET_VENDEURS}", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/*
	 * retourne les IDclients
	 */
	public static ResultSet getVendeursId(int id) {
		ResultSet rs = null;
		try {
			CallableStatement cs = App.db.getConnection().prepareCall("{call GET_VENDEURS_ID(?)}");
			cs.setInt(1, id);
			rs = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void addVendeur(String nom, String prenom, Double salaire) {
		CallableStatement cs;
		try {
			cs = App.db.getConnection().prepareCall("{call AJOUT_Vendeur(?,?,?)}");
			cs.setString(1, nom);
			cs.setString(2, prenom);
			cs.setDouble(3, salaire);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * supprime le client
	 */
	public void delete() {
		CallableStatement cs;
		try {
			cs = App.db.getConnection().prepareCall("{call DELETE_VENDEUR(?)}");
			cs.setInt(1, this.getId());
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void modifierVendeur(String nom, String prenom, Double salaire) {

		try {
			PreparedStatement ps = App.db.getConnection().prepareStatement("UPDATE VENDEURS SET Nom_Vendeur = ?,"
					+ " Prenom_Vendeur = ?, Salaire_Vendeur = ?  WHERE Id_Vendeur = ?");
			ps.setString(1, nom);
			ps.setString(2, prenom);
			ps.setDouble(3, salaire);
			ps.setInt(4, this.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public final SimpleIntegerProperty idProperty() {
		return this.id;
	}

	public final int getId() {
		return this.idProperty().get();
	}

	public final void setId(final int id) {
		this.idProperty().set(id);
	}

	public final SimpleStringProperty nomProperty() {
		return this.nom;
	}

	public final String getNom() {
		return this.nomProperty().get();
	}

	public final void setNom(final String nom) {
		return;
	}

	public final SimpleStringProperty prenomProperty() {
		return this.prenom;
	}

	public final String getPrenom() {
		return this.prenomProperty().get();
	}

	public final void setPrenom(final String prenom) {
		return;
	}

	public final SimpleDoubleProperty salaireProperty() {
		return this.salaire;
	}

	public final double getSalaire() {
		return this.salaireProperty().get();
	}

	public final void setSalaire(final double salaire) {
		return;
	}

}
