package org.ProjetL3MiageCilsEquipeNumero2.Magasin;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.ProjetL3MiageCilsEquipeNumero2.SignatureDesign.App;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Client {

    private static ObservableList<Client> clients = FXCollections.observableArrayList();

    private SimpleIntegerProperty id;
    private SimpleStringProperty nom;
    private SimpleStringProperty prenom;
    private SimpleIntegerProperty num_tel;
    private SimpleStringProperty adresse;
    private SimpleStringProperty email;

    public static ObservableList<Client> getClients(){
        return clients;
    }

    public Client(int id, String prenom, String nom, int num_tel, String adresse, String email){
    	this.id =  new SimpleIntegerProperty(id);
        this.prenom = new SimpleStringProperty(prenom);
        this.nom = new SimpleStringProperty(nom);
        this.num_tel = new SimpleIntegerProperty(num_tel);
        this.adresse = new SimpleStringProperty(adresse);
        this.email = new SimpleStringProperty(email);
    }
    
    /*
     * met à jour les clients
     */

	public static void clientsUpdate() {
		clients.clear();
		ResultSet tableClients = Client.getTableClient();
		try {
			while (tableClients.next()) {
				clients.add(new Client(tableClients.getInt("Id_Client"), tableClients.getString("Prenom_Client") , 
						tableClients.getString("Nom_Client"), tableClients.getInt("NTel_Client"),
						tableClients.getString("Adresse_Client"), tableClients.getString("Email_Client")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    /**
	 * retourne la table Client
	 */
	public static ResultSet getTableClient() {
		ResultSet rs = null;
		try {
			CallableStatement cs = App.db.getConnection().prepareCall("{call GET_CLIENTS}");
			rs = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/*
	 * retourne les IDclients
	 */
	public static ResultSet getClientsId(int id) {
		ResultSet rs = null;
		try {
			CallableStatement cs = App.db.getConnection().prepareCall("{call GET_CLIENTS_ID(?)}");
			cs.setInt(1, id);
			rs = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
    
    public static void addClient(String nom, String prenom, int tel, String adr, String mail){
    	CallableStatement cs;
		try {
			cs = App.db.getConnection().prepareCall("{call AJOUT_CLIENT(?,?,?,?,?)}");
			cs.setString(1, nom);
			cs.setString(2, prenom);
			cs.setInt(3, tel);
			cs.setString(4, adr);
			cs.setString(5, mail);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public void modifierClient(String nom, String prenom, int portable, String adr, String mail){
    	
    	try {
			PreparedStatement ps = App.db.getConnection().prepareStatement("UPDATE CLIENTS SET Nom_Client = ?,"
					+ " Prenom_Client = ?, Adresse_Client = ? , NTel_Client = ?, Email_Client = ?  WHERE Id_Client = ?");
			ps.setString(1, nom);
			ps.setString(2,prenom);
			ps.setString(3,adr);
			ps.setInt(4, portable);
			ps.setString(5, mail);
			ps.setInt(6,this.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*
	 * supprime le client
	 */
	public void delete() {
		CallableStatement cs;
		try {
			cs = App.db.getConnection().prepareCall("{call DELETE_CLIENT(?)}");
			cs.setInt(1, this.getId());
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

    public final SimpleIntegerProperty idProperty(){
        return this.id;
    }

    public final int getId(){
        return this.idProperty().get();
    }

    public final void setId(final int id){
        this.idProperty().set(id);
    }
    
    public final SimpleStringProperty nomProperty(){
        return this.nom;
    }
    
    public final String getNom(){
        return this.nomProperty().get();
    }

    public final void setNom(final String nom){
        return;
    }

    public final SimpleStringProperty prenomProperty(){
        return this.prenom;
    }

    public final String getPrenom(){
        return this.prenomProperty().get();
    }

    public final void setPrenom(final String prenom){
        return;
    }

    public final SimpleIntegerProperty num_telProperty(){
        return this.num_tel;
    }
    
    public final int getNum_tel(){
        return this.num_telProperty().get();
    }

    public final void setNum_tel(final int num_tel){
        return;
    }
    
    public final SimpleStringProperty adresseProperty() {
    return this.adresse;
    }
    public final String getAdresse() {
		return this.adresseProperty().get();
	}

	public final void setId(final String adresse) {
		this.adresseProperty().set(adresse);
	}

    public final SimpleStringProperty emailProperty(){
        return this.email;
    }

    public final String getEmail() {
        return this.emailProperty().get();
    }

    public final void setEmail(final String email){
        this.emailProperty().set(email);
    }

}
