package org.ProjetL3MiageCilsEquipeNumero2.Magasin;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.ProjetL3MiageCilsEquipeNumero2.SQLcommunication.DataBase;
import org.ProjetL3MiageCilsEquipeNumero2.SignatureDesign.App;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Fournisseur {
   
    private static ObservableList<Fournisseur> fournisseurs = FXCollections.observableArrayList();

    private SimpleIntegerProperty id;
    private SimpleStringProperty nom;
    private SimpleIntegerProperty num_tel;
    private SimpleStringProperty email;


    public static ObservableList<Fournisseur> getFournisseurs(){
        return fournisseurs;
    }

    public Fournisseur(int id, String nom, int num_tel, String email){
        this.id = new SimpleIntegerProperty(id);
        this.email = new SimpleStringProperty(email);
        this.nom = new SimpleStringProperty(nom);
        this.num_tel = new SimpleIntegerProperty(num_tel);
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

    public final SimpleIntegerProperty num_telProperty() {
		return this.num_tel;
	}

	public final int getNum_tel() {
		return this.num_telProperty().get();
	}

	public final void setNum_tel(final int num_tel) {
		this.num_telProperty().set(num_tel);
	}

    public final SimpleStringProperty nomProperty() {
		return this.id;
	}

	public final String getNom() {
		return this.nomProperty().get();
	}

	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}

    public final SimpleStringProperty emailProperty(){
        return this.email;
    }

    public final String getEmail() {
        return this.emailProperty().get();
    }

    public final void setEmail(final String email){
        this.emailProperty.set(email);
    }

}
