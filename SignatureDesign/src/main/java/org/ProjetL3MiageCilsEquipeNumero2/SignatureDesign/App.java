package org.ProjetL3MiageCilsEquipeNumero2.SignatureDesign;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver trouve");
		} catch (ClassNotFoundException e) {
			System.err.println("Driver non present dans classpath");
		}
        scene = new Scene(loadFXML("connexionPage/ConnexionPage"), 640, 480);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    //met à jour la racine de scene
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    //charge les UIs
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}