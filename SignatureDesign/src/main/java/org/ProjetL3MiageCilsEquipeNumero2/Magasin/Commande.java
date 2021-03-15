package org.ProjetL3MiageCilsEquipeNumero2.Magasin;

import java.sql.CallableStatement;
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

public class Commande {
    private static ObservableList<Article> commandes = FXCollections.observableArrayList();

    private SimpleIntegerProperty id;
    private SimpleStringProperty nom_client;
    private SimpleIntegerProperty id_vente;
//    private SimpleDateProperty date_commande;
//    private SimpleDateProperty date_vente_prevue;

    public Commande(int id, String nom_client, int id_vente){
        this.id = new SimpleIntegerProperty(id);
        this.nom_client = new SimpleStringProperty(nom_client);
        this.id_vente = new SimpleIntegerProperty(id_vente);
    }
}
