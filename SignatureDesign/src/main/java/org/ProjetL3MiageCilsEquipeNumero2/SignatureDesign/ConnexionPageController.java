package org.ProjetL3MiageCilsEquipeNumero2.SignatureDesign;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

import javafx.scene.layout.StackPane;

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
	public void initialize() {
		idtxt.setFocusTraversable(false);
		txtPass.setFocusTraversable(false);
		logIn.setFocusTraversable(false);
	}
}
