package org.ProjetL3MiageCilsEquipeNumero2.SignatureDesign;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;

import java.io.IOException;

import org.ProjetL3MiageCilsEquipeNumero2.Magasin.Client;
import org.ProjetL3MiageCilsEquipeNumero2.Magasin.Vendeur;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.MenuItem;

import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class Vendeurs_uiController {

	// barre
	@FXML
	private Button stocks;
	@FXML
	private Button rh;
	@FXML
	private AnchorPane rhpanel;
	@FXML
	private Button clientsbutton;
	@FXML
	private Button vendeursbutton;
	@FXML
	private Button sales;
	@FXML
	private Button stats;

	// affichage
	@FXML
	private VBox affichage;
	@FXML
	private TextField search_bar;
	@FXML
	private Button filtres_button;
	// table
	@FXML
	private TableView<Vendeur> main_table;
	@FXML
	private TableColumn<Vendeur, Integer> id_col;
	@FXML
	private TableColumn<Vendeur, String> nom_col;
	@FXML
	private TableColumn<Vendeur, String> prenom_col;
	// card
	@FXML
	private Label v_id;
	@FXML
	private Label v_nom;
	@FXML
	private Label v_prenom;
	@FXML
	private Label v_salaire;
	// options
	@FXML
	private MenuItem modifier;
	@FXML
	private MenuItem supprimer;
	@FXML
	private MenuItem nouveau;
	// panel
	@FXML
	private AnchorPane createpanel;
	@FXML
	private TextField nom;
	@FXML
	private TextField prenom;
	@FXML
	private TextField Salaire;
	@FXML
	private Button create;
	@FXML
	private Label msgerreur;
	@FXML
	private Button annuler;
	@FXML
	private Button modif;
	@FXML
	private Label option;

	TableViewSelectionModel<Vendeur> selectionModel = null;
	ObservableList<Vendeur> selection = null;

	// Event Listener on Button[#stocks].onAction
	@FXML
	public void afficheStock(ActionEvent event) throws IOException {
		App.setRoot("Articles_ui");
	}

	// Event Listener on Button[#rh].onAction
	@FXML
	public void afficheRH(ActionEvent event) throws IOException {
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

	@FXML
	public void afficheClients(ActionEvent event) throws IOException {
		App.setRoot("Clients_ui");
	}

	@FXML
	public void afficheVendeurs(ActionEvent event) throws IOException {
		App.setRoot("Vendeurs_ui");
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

	// initialisation
	@FXML
	public void initialize() {
		// formatters input int (portable)
		Salaire.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
		// affichage tables
		id_col.setCellValueFactory(new PropertyValueFactory<Vendeur, Integer>("id"));
		nom_col.setCellValueFactory(new PropertyValueFactory<Vendeur, String>("nom"));
		prenom_col.setCellValueFactory(new PropertyValueFactory<Vendeur, String>("prenom"));
		Vendeur.vendeursUpdate();
		main_table.setItems(Vendeur.getVendeurs());
		// lorsque l'utilisateur clique sur un client le card lateral s'actualise
		selectionModel = main_table.getSelectionModel();
		selection = selectionModel.getSelectedItems();
		selection.addListener(new ListChangeListener<Vendeur>() {
			@Override
			public void onChanged(Change<? extends Vendeur> change) {
				// si un article est selectionne
				if (!selection.isEmpty()) {
					Vendeur focus = selection.get(0);
					panelUpdate(focus);
				}
			}
		});
	}

	/*
	 * actualise les valeur du panel à partir d'un vendeur
	 * 
	 * @param
	 */
	public void panelUpdate(Vendeur focus) {
		v_id.setText(Integer.toString(focus.getId()));
		v_nom.setText(focus.getNom());
		v_prenom.setText(focus.getPrenom());
		v_salaire.setText(Double.toString(focus.getSalaire()));
	}

	// Event Listener on MenuItem[#modifier].onAction
	@FXML
	public void modifierPanel(ActionEvent event) {
		if (!selection.isEmpty()) {
			option.setText("Modifier Article");
			create.setVisible(false);
			modif.setVisible(true);
			affichage.setEffect(new GaussianBlur());
			affichage.setDisable(true);
			// focus = article selectionne
			Vendeur focus = selection.get(0);
			nom.setText(focus.getNom());
			Salaire.setText(Double.toString(focus.getSalaire()));
			prenom.setText(focus.getPrenom());
			createpanel.setVisible(true);
		}
	}

	// panel default
	public void cardDefault() {
		v_id.setText("N/A");
		v_nom.setText("N/A");
		v_prenom.setText("N/A");
		v_salaire.setText("N/A");
	}

	// Event Listener on MenuItem[#supprimer].onAction
	@FXML
	public void supprimer(ActionEvent event) {
		if (!selection.isEmpty()) {
			// focus = article selectionne
			Vendeur focus = selection.get(0);
			// suppression de l'article selectionne
			focus.delete();
			// actualisation de la table articles
			Vendeur.vendeursUpdate();
			// on vide la selection
			selectionModel.clearSelection();
			// on met à jour le panel avec des valeurs N/A car aucun Client est selectionne
			cardDefault();
		}
	}

	public void panelDefault() {
		nom.setText(null);
		prenom.setText(null);
		Salaire.setText(null);
		msgerreur.setVisible(false);
	}

	// Event Listener on MenuItem[#nouveau].onAction
	@FXML
	public void createPanel(ActionEvent event) {
		option.setText("Ajout Vendeur");
		create.setVisible(true);
		modif.setVisible(false);
		affichage.setEffect(new GaussianBlur());
		affichage.setDisable(true);
		panelDefault();
		createpanel.setVisible(true);
	}

	// Event Listener on Button[#create].onAction
	@FXML
	public void createVendeur(ActionEvent event) {
		if (Salaire.getText() == "" || Salaire.getText() == null || nom.getText() == null || prenom.getText() == null) {
			// salaire n'est pas un chiffre
			msgerreur.setVisible(true);
		} else {
			Vendeur.addVendeur(nom.getText(), prenom.getText(), Double.parseDouble(Salaire.getText()));
			Vendeur.vendeursUpdate();
			createpanel.setVisible(false);
			affichage.setDisable(false);
			affichage.setEffect(null);
			affichage.setVisible(true);
		}
	}

	// Event Listener on Button[#annuler].onAction
	@FXML
	public void annuler(ActionEvent event) {
		createpanel.setVisible(false);
		affichage.setDisable(false);
		affichage.setEffect(null);
		affichage.setVisible(true);
	}

	// Event Listener on Button[#modif].onAction
	@FXML
	public void modifierVendeur(ActionEvent event) {
		if (Salaire.getText() == "" || Salaire.getText() == null || nom.getText() == null || prenom.getText() == null) {
			// salaire n'est pas un chiffre
			msgerreur.setVisible(true);
		} else {
			Vendeur focus = selection.get(0);
			focus.modifierVendeur(nom.getText(), prenom.getText(), Double.parseDouble(Salaire.getText()));
			Vendeur.vendeursUpdate();
			createpanel.setVisible(false);
			affichage.setDisable(false);
			affichage.setEffect(null);
			affichage.setVisible(true);

			// on met à jour le panel avec des valeurs N/A car aucun vendeur est selectionne
			cardDefault();
		}
	}
}
