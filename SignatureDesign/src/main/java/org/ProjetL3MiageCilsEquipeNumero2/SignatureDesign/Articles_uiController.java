package org.ProjetL3MiageCilsEquipeNumero2.SignatureDesign;

import java.io.IOException;

import org.ProjetL3MiageCilsEquipeNumero2.Magasin.Article;
import org.ProjetL3MiageCilsEquipeNumero2.Magasin.Quantite;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TableColumn;

public class Articles_uiController {

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

	// recherche
	@FXML
	private TextField search_bar;
	@FXML
	private Button filtres_button;

	// VBox
	@FXML
	private VBox affichage;
	// table articles
	@FXML
	private TableView<Article> main_table;
	@FXML
	private TableColumn<Article, Integer> id_col;
	@FXML
	private TableColumn<Article, String> nom_col;
	@FXML
	private TableColumn<Article, Double> prix_col;

	// panel article
	@FXML
	private Label v_id;
	@FXML
	private Label v_nom;
	@FXML
	private Label v_prix;
	@FXML
	private Label v_marque;
	@FXML
	private Label v_cat;
	// table quantites
	@FXML
	private TableView<Quantite> sec_table;
	@FXML
	private TableColumn<Quantite, String> taille_col;
	@FXML
	private TableColumn<Quantite, String> coleur_col;
	@FXML
	private TableColumn<Quantite, Integer> qt_col;
	// options
	@FXML
	private MenuItem supprimer;
	@FXML
	private MenuItem modifierqte;
	@FXML
	private MenuItem modifier;
	@FXML
	private MenuItem nouveau;
	@FXML
	private MenuItem ajoutQT;

	// nouveau article / modif article
	@FXML
	private Label option;
	@FXML
	private TextField nom;
	@FXML
	private TextField prix;
	@FXML
	private TextField marque;
	@FXML
	private TextField categorie;
	@FXML
	private Button create;
	@FXML
	private Button annuler;
	@FXML
	private Label msgerreur;
	@FXML
	private Button modif;

	// ajout QTE
	@FXML
	private TextField taille;
	@FXML
	private TextField couleur;
	@FXML
	private TextField quantite;
	@FXML
	private Label msgerreurQTE;
	@FXML
	private Button validerAjoutQte;
	@FXML
	private Button annulerqte;
	@FXML
	private Button validerModifQte;

	// Panel Create/modif article
	@FXML
	private AnchorPane createpanel;
	// Panel ajoutQTE
	@FXML
	private AnchorPane qtepanel;
	@FXML
	private Label optionqte;

	TableViewSelectionModel<Article> selectionModel = null;
	ObservableList<Article> selection = null;
	TableViewSelectionModel<Quantite> selectionqteModel = null;
	ObservableList<Quantite> selectionqte = null;

	// initialisation
	@FXML
	public void initialize() {
		// formatters input double (prix)
		prix.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
		quantite.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

		// afficahge tables
		id_col.setCellValueFactory(new PropertyValueFactory<Article, Integer>("id"));
		nom_col.setCellValueFactory(new PropertyValueFactory<Article, String>("nom"));
		prix_col.setCellValueFactory(new PropertyValueFactory<Article, Double>("prix"));
		taille_col.setCellValueFactory(new PropertyValueFactory<Quantite, String>("taille"));
		coleur_col.setCellValueFactory(new PropertyValueFactory<Quantite, String>("couleur"));
		qt_col.setCellValueFactory(new PropertyValueFactory<Quantite, Integer>("quantite"));
		Article.articlesUpdate();
		main_table.setItems(Article.getArticles());
		// lorsque l'utilisateur clique sur un article le card lateral s'actualise
		selectionModel = main_table.getSelectionModel();
		selection = selectionModel.getSelectedItems();
		selectionqteModel = sec_table.getSelectionModel();
		selectionqte = selectionqteModel.getSelectedItems();
		selection.addListener(new ListChangeListener<Article>() {
			@Override
			public void onChanged(Change<? extends Article> change) {
				// si un article est selectionne
				if (!selection.isEmpty()) {
					Article focus = selection.get(0);
					panelUpdate(focus);
				}
			}
		});
	}

	/*
	 * actualise les valeur du panel à partir d'un article
	 * 
	 * @param
	 */
	public void panelUpdate(Article focus) {
		v_id.setText(Integer.toString(focus.getId()));
		v_nom.setText(focus.getNom());
		v_prix.setText(Double.toString(focus.getPrix()));
		v_marque.setText(focus.getMarque());
		v_cat.setText(focus.getCategorie());
		sec_table.setItems(focus.getQuantites());
	}

	// panel default
	public void cardDefault() {
		v_id.setText("N/A");
		v_nom.setText("N/A");
		v_prix.setText("N/A");
		v_marque.setText("N/A");
		v_cat.setText("N/A");
		sec_table.getItems().clear();
	}

	// supprssion article
	@FXML
	public void supprimer() {
		if (!selection.isEmpty()) {
			// focus = article selectionne
			Article focus = selection.get(0);
			// suppression de l'article selectionne
			focus.delete();
			// actualisation de la table articles
			Article.articlesUpdate();
			// on vide la selection
			selectionModel.clearSelection();
			// on met à jour le panel avec des valeurs N/A car aucun article est selectionne
			cardDefault();
		}
	}

	public void panelDefault() {
		nom.setText(null);
		prix.setText(null);
		categorie.setText(null);
		marque.setText(null);
		msgerreur.setVisible(false);
	}

	/*
	 * ouvre un panel de création d'article
	 */
	@FXML
	public void createPanel() {
		option.setText("Ajout Article");
		create.setVisible(true);
		modif.setVisible(false);
		affichage.setEffect(new GaussianBlur());
		affichage.setDisable(true);
		panelDefault();
		createpanel.setVisible(true);

	}

	/*
	 * cree un nouveau article
	 * 
	 */
	@FXML
	public void createArticle() {
		if (prix.getText() == "" || prix.getText() == null || nom.getText() == null || marque.getText() == null
				|| categorie.getText() == null) {
			// prix n'est pas un chiffre
			msgerreur.setVisible(true);
		} else {
			Article.createArticle(nom.getText(), Double.parseDouble(prix.getText()), marque.getText(),
					categorie.getText());
			Article.articlesUpdate();
			createpanel.setVisible(false);
			affichage.setDisable(false);
			affichage.setEffect(null);
			affichage.setVisible(true);
		}
	}

	// ouvre un panel modifier
	@FXML
	public void modifierPanel() {
		if (!selection.isEmpty()) {
			option.setText("Modifier Article");
			create.setVisible(false);
			modif.setVisible(true);
			affichage.setEffect(new GaussianBlur());
			affichage.setDisable(true);
			// focus = article selectionne
			Article focus = selection.get(0);
			nom.setText(focus.getNom());
			prix.setText(Double.toString(focus.getPrix()));
			marque.setText(focus.getMarque());
			categorie.setText(focus.getCategorie());
			createpanel.setVisible(true);

		}

	}

	/*
	 * modifie un article
	 */
	public void modifierArticle() {
		if (prix.getText() == "" || prix.getText() == null || nom.getText() == null || marque.getText() == null
				|| categorie.getText() == null) {
			// prix n'est pas un chiffre
			msgerreur.setVisible(true);
		} else {
			Article focus = selection.get(0);
			focus.modifierArticle(nom.getText(), Double.parseDouble(prix.getText()), marque.getText(),
					categorie.getText());
			Article.articlesUpdate();
			createpanel.setVisible(false);
			affichage.setDisable(false);
			affichage.setEffect(null);
			affichage.setVisible(true);

			// on met à jour le panel avec des valeurs N/A car aucun article est selectionne
			cardDefault();
		}
	}

	/*
	 * ouvre un panel de création de qte
	 */
	@FXML
	public void qtePanel() {
		if (!selection.isEmpty()) {
			optionqte.setText("Ajout de quantité");
			msgerreurQTE.setVisible(false);
			validerModifQte.setVisible(false);
			validerAjoutQte.setVisible(true);
			taille.setText(null);
			couleur.setText(null);
			quantite.setText(null);
			affichage.setEffect(new GaussianBlur());
			affichage.setDisable(true);
			qtepanel.setVisible(true);
		}

	}
	
	
	@FXML
	public void ajoutQte() {
		if (quantite.getText() == "" || quantite.getText() == null || taille.getText() == null
				|| couleur.getText() == null || quantite.getText() == null) {
			msgerreurQTE.setText("Les données introduites ne permettent pas d'ajouter une quantité à cet article.");
			msgerreurQTE.setVisible(true);
		} else {
			// focus = article selectionne
			Article focus = selection.get(0);
			for (Quantite q : focus.getQuantites()) {
				if (q.getCouleur().equals(couleur.getText()) && q.getTaille().equals(taille.getText())) {
					msgerreurQTE.setText("Cette association taille-couleur-quantité existe déjà.");
					msgerreurQTE.setVisible(true);
					return;
				}
			}
			focus.createQuantite(taille.getText(), couleur.getText(), Integer.parseInt(quantite.getText()));
			Article.articlesUpdate();
			qtepanel.setVisible(false);
			affichage.setDisable(false);
			affichage.setEffect(null);
			affichage.setVisible(true);

		}
	}

	/*
	 * ouvre un panel modifierqte
	 */
	@FXML
	public void modifierQtePanel() {
		if (!selectionqte.isEmpty()) {
			optionqte.setText("ModifierQte");
			msgerreurQTE.setVisible(false);
			validerModifQte.setVisible(true);
			validerAjoutQte.setVisible(false);
			taille.setText(null);
			couleur.setText(null);
			quantite.setText(null);
			Quantite focus = selectionqte.get(0);
			taille.setText(focus.getTaille());
			couleur.setText(focus.getCouleur());
			quantite.setText(Integer.toString(focus.getQuantite()));
			affichage.setEffect(new GaussianBlur());
			affichage.setDisable(true);
			qtepanel.setVisible(true);
		}
	}


	/*
	 * modifier quantité
	 */
	@FXML
	public void modifierQte() {
		if (quantite.getText() == "" || quantite.getText() == null || taille.getText() == null
				|| couleur.getText() == null || quantite.getText() == null) {
			msgerreurQTE.setText("Les données introduites ne permettent pas de modifier cette quantité .");
			msgerreurQTE.setVisible(true);
		} else {
			// focus = article selectionne
			Article focus = selection.get(0);
			focus.modifierQuantite(taille.getText(), couleur.getText(), Integer.parseInt(quantite.getText()));
			Article.articlesUpdate();
			qtepanel.setVisible(false);
			affichage.setDisable(false);
			affichage.setEffect(null);
			affichage.setVisible(true);
		}
		cardDefault();

	}


	@FXML
	public void annuler() {
		qtepanel.setVisible(false);
		createpanel.setVisible(false);
		affichage.setDisable(false);
		affichage.setEffect(null);
		affichage.setVisible(true);
	}

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

}
