package org.ProjetL3MiageCilsEquipeNumero2.SignatureDesign;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
	public void initialize() {
		idtxt.setFocusTraversable(false);
		txtPass.setFocusTraversable(false);
		logIn.setFocusTraversable(false);
	}
	
	@FXML
	public void logIn() {
		boolean succes = App.connexion.init(idtxt.getText(),txtPass.getText());
		if(succes) {
			
		}else {
			vboxInput.setEffect(new GaussianBlur());
			vboxInput.setDisable(true);
			vboxOutput.setVisible(true);
			vboxOutput.setDisable(false);
		}
	}
	@FXML
	public void retry() {
		vboxOutput.setDisable(true);
		vboxOutput.setVisible(false);
		vboxInput.setDisable(false);
		vboxInput.setEffect(null);
		vboxInput.setVisible(true);
	}
}
