package org.ProjetL3MiageCilsEquipeNumero2.SignatureDesign;

import org.ProjetL3MiageCilsEquipeNumero2.Magasin.Article;
import org.ProjetL3MiageCilsEquipeNumero2.Magasin.Quantite;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TableColumn;

public class Articles_uiController {

	// recherche
	@FXML
	private TextField search_bar;
	@FXML
	private Button filtres_button;

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
	private MenuItem modifier;
	@FXML
	private MenuItem nouveau;

	TableViewSelectionModel<Article> selectionModel = null;
	ObservableList<Article> selection = null;

	@FXML
	public void initialize() {
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
	
	public void panelDefault() {
		v_id.setText("N/A");
		v_nom.setText("N/A");
		v_prix.setText("N/A");
		v_marque.setText("N/A");
		v_cat.setText("N/A");
		sec_table.getItems().clear();
	}
	
	
	
	@FXML
	public void supprimer() {
		if (!selection.isEmpty()) {
			// focus = article selectionne
			Article focus = selection.get(0);
			// suppression de l'article selectionne
			focus.delete();
			// actualisation de la table articles
			Article.articlesUpdate();
			//on vide la selection
			selectionModel.clearSelection();
			//on met à jour le panel avec des valeurs N/A car aucun article est selectionne
			panelDefault();
		}
	}

	/*
	 * ouvre un panel de création d'article
	 */
	public void createPanel() {

	}
}
