package org.ProjetL3MiageCilsEquipeNumero2.SignatureDesign;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.ProjetL3MiageCilsEquipeNumero2.Magasin.Article;
import org.ProjetL3MiageCilsEquipeNumero2.SQLcommunication.DataBase;
import org.ProjetL3MiageCilsEquipeNumero2.SQLcommunication.SQLcomm;

/**
 * JavaFX App
 */
public class App extends Application {

	public static SQLcomm connexion = new SQLcomm();
	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver trouve");
		} catch (ClassNotFoundException e) {
			System.err.println("Driver non present dans classpath");
		}
		scene = new Scene(loadFXML("ConnexionPage"), 640, 480);
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.show();
	}

	/**
	 * ferme la connexion avec la bdd lors de l'arret de l'application
	 */
	@Override
	public void stop() throws Exception {
		boolean succes = connexion.close();
		if (succes)
			System.out.println("Connexion fermee avec succes");
		super.stop();
	}

	/**
	 * met à jour la racine de scene
	 * 
	 * @param fxml
	 * @throws IOException
	 */
	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	/**
	 * charge les UIs
	 * 
	 * @param fxml
	 * @return
	 * @throws IOException
	 */
	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		//launch();
		//test creation bdd
		Scanner input = new Scanner(System.in);
		System.out.println("Nom?");
		String nom = input.nextLine();
		Scanner input2 = new Scanner(System.in);
		System.out.println("Password?");
		String pass = input2.nextLine();
		input.close();
		input2.close();
		DataBase db = new DataBase(nom, pass);
		/*ResultSet rs = Article.getTableArticles();
		try {
			while (rs.next()) {
			    String id = rs.getString("Id_Article");
			    System.out.println(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("FIN");*/
	}

}