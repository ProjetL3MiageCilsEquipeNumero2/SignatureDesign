package org.ProjetL3MiageCilsEquipeNumero2.SignatureDesign;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.ProjetL3MiageCilsEquipeNumero2.Magasin.Article;
import org.ProjetL3MiageCilsEquipeNumero2.Magasin.Quantite;
import org.ProjetL3MiageCilsEquipeNumero2.Magasin.Vente;
import org.ProjetL3MiageCilsEquipeNumero2.Magasin.VenteArticle;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class Ventes_uiController {
	@FXML
	private Button stocks;
	@FXML
	private Button rh;
	@FXML
	private Button sales;
	@FXML
	private Button stats;
	@FXML
	private VBox affichage;
	@FXML
	private TextField search_bar;
	@FXML
	private Button filtres_button;
	@FXML
	private TableView<Vente> main_table;
	@FXML
	private TableColumn<Vente, Integer> id_col;
	@FXML
	private TableColumn<Vente, Integer> client_col;
	@FXML
	private TableColumn<Vente, Integer> vendeur_col;
	@FXML
	private TableColumn<Vente, Date> date_col;
	@FXML
	private Label v_id;
	@FXML
	private Label v_client;
	@FXML
	private Label v_vendeur;
	@FXML
	private Label v_date;
	@FXML
	private TableView<VenteArticle> sec_table;
	@FXML
	private TableColumn<VenteArticle, Integer> id_article_col;
	@FXML
	private TableColumn<VenteArticle, String> taille_col;
	@FXML
	private TableColumn<VenteArticle, String> coleur_col;
	@FXML
	private TableColumn<VenteArticle, Integer> qt_col;
	@FXML
	private AnchorPane rhpanel;
	@FXML
	private Button clientsbutton;
	@FXML
	private Button vendeursbutton;

	TableViewSelectionModel<Vente> selectionModel = null;
	ObservableList<Vente> selection = null;

	// initialisation
	@FXML
	public void initialize() {
		// afficahge tables
		id_col.setCellValueFactory(new PropertyValueFactory<Vente, Integer>("id"));
		client_col.setCellValueFactory(new PropertyValueFactory<Vente, Integer>("id_client"));
		vendeur_col.setCellValueFactory(new PropertyValueFactory<Vente, Integer>("id_vendeur"));
		date_col.setCellValueFactory(new PropertyValueFactory<Vente, Date>("date"));
		id_article_col.setCellValueFactory(new PropertyValueFactory<VenteArticle, Integer>("id"));
		taille_col.setCellValueFactory(new PropertyValueFactory<VenteArticle, String>("taille"));
		coleur_col.setCellValueFactory(new PropertyValueFactory<VenteArticle, String>("couleur"));
		qt_col.setCellValueFactory(new PropertyValueFactory<VenteArticle, Integer>("qte"));
		Vente.ventesUpdate();
		main_table.setItems(Vente.getVentes());
		// lorsque l'utilisateur clique sur une vente le card lateral s'actualise
		selectionModel = main_table.getSelectionModel();
		selection = selectionModel.getSelectedItems();
		selection.addListener(new ListChangeListener<Vente>() {
			@Override
			public void onChanged(Change<? extends Vente> change) {
				// si un article est selectionne
				if (!selection.isEmpty()) {
					Vente focus = selection.get(0);
					panelUpdate(focus);
				}
			}
		});
	}

	/*
	 * actualise les valeur du panel à partir d'une vente
	 * 
	 * @param
	 */
	public void panelUpdate(Vente focus) {
		v_id.setText(Integer.toString(focus.getId()));
		v_client.setText(Integer.toString(focus.getId_client()));
		v_vendeur.setText(Integer.toString(focus.getId_vendeur()));
		v_date.setText(focus.getDate().toString());
		sec_table.setItems(focus.getArticles());
	}

	// Event Listener on Button[#stocks].onAction
	@FXML
	public void afficheStock(ActionEvent event) throws IOException {
		App.setRoot("Articles_ui");
	}

	// Event Listener on Button[#rh].onAction
	@FXML
	public void afficheRH(ActionEvent event) {
		if (rhpanel.isVisible()) {
			rhpanel.setVisible(false);
			affichage.setDisable(false);
			affichage.setEffect(null);
			affichage.setVisible(true);
		} else {
			rhpanel.setVisible(true);
			affichage.setEffect(new GaussianBlur());
			affichage.setDisable(true);

		}
	}

	// Event Listener on Button[#sales].onAction
	@FXML
	public void afficheSales(ActionEvent event) throws IOException {
		App.setRoot("Ventes_ui");
	}

	// Event Listener on Button[#stats].onAction
	@FXML
	public void afficheStats(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on Button[#clientsbutton].onAction
	@FXML
	public void afficheClients(ActionEvent event) throws IOException {
		App.setRoot("Clients_ui");
	}

	// Event Listener on Button[#vendeursbutton].onAction
	@FXML
	public void afficheVendeurs(ActionEvent event) throws IOException {
		App.setRoot("Vendeurs_ui");
	}
}
