package org.ProjetL3MiageCilsEquipeNumero2.SignatureDesign;

import java.io.IOException;
import java.sql.SQLException;

import org.ProjetL3MiageCilsEquipeNumero2.Magasin.Article;
import org.ProjetL3MiageCilsEquipeNumero2.Magasin.DataMagasin;

import javafx.concurrent.Task;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class Articles_uiController {
	@FXML
	private TextField search_bar;
	@FXML
	private Button filtres_button;
	@FXML
	private TableView<Article> main_table;
	@FXML
	private TableColumn<Article, Integer> id_col;
	@FXML
	private TableColumn<Article, String> nom_col;
	@FXML
	private TableColumn<Article, Double> prix_col;
	@FXML
	private TableColumn qtmax_col;
	@FXML
	private Label id_label;
	@FXML
	private Label v_id;
	@FXML
	private Label nom_label;
	@FXML
	private Label v_nom;
	@FXML
	private Label prix_label;
	@FXML
	private Label v_prix;
	@FXML
	private Label marque_label;
	@FXML
	private Label cat_label;
	@FXML
	private Label v_marque;
	@FXML
	private Label v_cat;
	@FXML
	private TableView sec_table;
	@FXML
	private TableColumn taille_col;
	@FXML
	private TableColumn coleur_col;
	@FXML
	private TableColumn qt_col;
	
	
	@FXML
	public void initialize() {
		id_col.setCellValueFactory(new PropertyValueFactory<Article, Integer>("id"));
		nom_col.setCellValueFactory(new PropertyValueFactory<Article, String>("nom"));
		prix_col.setCellValueFactory(new PropertyValueFactory<Article, Double>("prix"));
		
		
	}
	
	

}
