package org.ProjetL3MiageCilsEquipeNumero2.SignatureDesign;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class ConnexionPageController {
	@FXML
	private StackPane innercenter;
	@FXML
	private Label idLabel;
	@FXML
	private TextField idtxt;
	@FXML
	private Label idPass;
	@FXML
	private PasswordField txtPass;
	@FXML
	private Button logIn;
	@FXML
	private Button retry;
	@FXML
	private VBox vboxInput;
	@FXML
	private VBox vboxOutput;
	@FXML
	private HBox anim;
	@FXML
	private Label load;

	@FXML
	public void initialize() {
		idtxt.setFocusTraversable(false);
		txtPass.setFocusTraversable(false);
		logIn.setFocusTraversable(false);
	}
	
	@FXML
	public void logIn() {
		vboxInput.setEffect(new GaussianBlur());
		vboxInput.setDisable(true);
		anim.setVisible(true);
		loader.play();
		// lancement de la connexion dans un nouveau thread
		new Thread(new Task<Void>() {
			@Override
			public Void call() throws IOException {
				boolean succes = App.connexion.init(idtxt.getText(), txtPass.getText());
				if (succes) {
					loader.stop();
					// changemnt d'UI
					App.setRoot("ongletArticles/Articles_ui");
				} else {
					loader.stop();
					anim.setVisible(false);
					vboxOutput.setVisible(true);
					vboxOutput.setDisable(false);
				}
				return null;
			}
		}).start();
	}

	@FXML
	public void retry() {
		vboxOutput.setDisable(true);
		vboxOutput.setVisible(false);
		vboxInput.setDisable(false);
		vboxInput.setEffect(null);
		vboxInput.setVisible(true);
	}
	

	public FadeTransition loader(Node anim) {
		FadeTransition loading = new FadeTransition(Duration.millis(1000.0), anim);
		loading.setFromValue(1.0);
		loading.setToValue(0.1);
		loading.setCycleCount(Timeline.INDEFINITE);
		loading.setAutoReverse(true);
		return loading;
	}
	
	private FadeTransition loader = loader(anim);
}
